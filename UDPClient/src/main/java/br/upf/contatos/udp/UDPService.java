/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.udp;

import br.upf.contatos.msg.Request;
import br.upf.contatos.msg.RequestImpl;
import br.upf.contatos.msg.Response;
import br.upf.contatos.msg.model.ContatoBean;
import br.upf.contatos.msg.model.Status;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.List;

/**
 *
 * @author Mauricley
 */
public class UDPService {
    private final String host;
    private final int porta;
    private DatagramSocket socket;
    private final int TIMEOUT = 15000;

    public UDPService(String host, int porta) {
        this.host = host;
        this.porta = porta;
        try {
            socket = new DatagramSocket();
            socket.setSoTimeout(TIMEOUT);
        } catch (SocketException e) {
            throw new RuntimeException("Não foi possível obter um socket UDP.");
        }
    }
    
    public List<ContatoBean> getAll() throws RuntimeException {
        Response resp = sendAndReceive(new RequestImpl().getAll());
        return resp.getContatos();
    }
    
    public ContatoBean getById(Integer id) throws RuntimeException {
        Response resp = sendAndReceive(new RequestImpl().getById(id));
        return resp.getContato();
    }
    
    public List<ContatoBean> getByCidade(String cidade) throws RuntimeException {
        Response resp = sendAndReceive(new RequestImpl().getByCidade(cidade));
        return resp.getContatos();
    }
    
    public ContatoBean insert(ContatoBean c) throws RuntimeException {
        Response resp = sendAndReceive(new RequestImpl().insert(c));
        return resp.getContato();
    }
    
    public ContatoBean update(ContatoBean c) throws RuntimeException {
        Response resp = sendAndReceive(new RequestImpl().update(c));
        return resp.getContato();
    }
    
    public ContatoBean delete(Integer id) throws RuntimeException {
        Response resp = sendAndReceive(new RequestImpl().delete(id));
        return resp.getContato();
    }
    
    private Response byteArray2Response(byte[] data) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(data));
        return (Response) in.readObject();
    }
    
    private byte[] request2ByteArray(Request req) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(os);
        out.writeObject(req);
        return os.toByteArray();
    }
    
    private void sendRequest(byte[] byteReq) throws IOException {
            DatagramPacket reqPct = new DatagramPacket(byteReq, byteReq.length, InetAddress.getByName(host), porta);
            socket.send(reqPct);
    }
    
    private Response receiveResponse() throws IOException, ClassNotFoundException {
        byte[] buffer = new byte[8192];
        DatagramPacket pct = new DatagramPacket(buffer, buffer.length);
        socket.receive(pct);
        return byteArray2Response(pct.getData());
    }
    
    private Response sendAndReceive(Request req) throws RuntimeException {
        try {
            sendRequest(request2ByteArray(req));
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível enviar a requisição para o servidor em " + host + ":" + porta);
        }
        
        try {
            Response resp = receiveResponse();
            if (resp.getStatus() == Status.ERROR)
                throw new RuntimeException(resp.getError());
            return resp;
        } catch (SocketTimeoutException e) {
            throw new RuntimeException("O tempo aguardando a resposta do servidor expirou.");
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível receber a resposta do servidor em " + host + ":" + porta);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("A resposta do servidor é inválida!");
        }
    }
}
