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
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author Gehrke
 */

public class UDPConexao {
    private final String host;
    private final int porta;
    private Socket socket;
    private ObjectInputStream desempacotar;
    private ObjectOutputStream empacotar;

    public UDPConexao(String host, int porta) {
        this.host = host;
        this.porta = porta;
    }
    
    public void connect() throws RuntimeException {
        try {
            this.socket = new Socket(host, porta);
            DatagramSocket socket = new DatagramSocket(porta);
        } catch (IOException ex) {
            throw new RuntimeException("Não foi possível se conectar ao servidor em " + host + ":" + porta);
        }
    }
    
    
    public void disconnect() throws RuntimeException {
        try {
            send(new RequestImpl().disconnect());
        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                throw new RuntimeException("Não foi possível fechar a conexão com o servidor em " + host + ":" + porta);
            }
        }
    }
    
    /* Verificar
    
    private Response sendAndReceive(Request req) throws RuntimeException {
        try {
            empacotar.writeObject(req);
            Response resp = (Response) desempacotar.readObject();
            if (resp.getStatus() == Status.ERROR)
                throw new RuntimeException(resp.getError());
            return resp;
        } catch (IOException ex) {
            throw new RuntimeException("Não foi possível fazer a requisição com o servidor em " + host + ":" + porta);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("A resposta do servidor é inválida!");
        }
    }
       */
       
       	public Response Receive(byte[] data){	
                try {
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
                    ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                    Response resp = (Response) desempacotar.readObject();
                    return resp;
        } catch (IOException ex) {
            throw new RuntimeException("Não foi possível fazer a requisição com o servidor em " + host + ":" + porta);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("A resposta do servidor é inválida!"); 	
	}
        
        }
        
       	public byte[] send(Request req){
            byte[] bytes = null;
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                    empacotar.writeObject(req);
                    objectOutputStream.flush();
                    objectOutputStream.close();
                    byteArrayOutputStream.close();
                    bytes = byteArrayOutputStream.toByteArray();
             } catch (IOException ex) {
            throw new RuntimeException("Não foi possível fazer a requisição com o servidor em " + host + ":" + porta);
                }
            return bytes;	
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
    

    ContatoBean getByCidade(ContatoBean cob) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
