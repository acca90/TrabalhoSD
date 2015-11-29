/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.tcp;

import br.upf.contatos.msg.Request;
import br.upf.contatos.msg.RequestImpl;
import br.upf.contatos.msg.Response;
import br.upf.contatos.msg.model.ContatoBean;
import br.upf.contatos.msg.model.Status;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author Mauricley
 */
public class ClientConnector {
    private final String host;
    private final int porta;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ClientConnector(String host, int porta) {
        this.host = host;
        this.porta = porta;
    }
    
    public void connect() throws RuntimeException {
        try {
            this.socket = new Socket(host, porta);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            throw new RuntimeException("Não foi possível se conectar ao servidor em " + host + ":" + porta);
        }
    }
    
    public void disconnect() throws RuntimeException {
        try {
            sendAndReceive(new RequestImpl().disconnect());
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
    
    private Response sendAndReceive(Request req) throws RuntimeException {
        try {
            out.writeObject(req);
            Response resp = (Response) in.readObject();
            if (resp.getStatus() == Status.ERROR)
                throw new RuntimeException(resp.getError());
            return resp;
        } catch (IOException ex) {
            throw new RuntimeException("Não foi possível fazer a requisição com o servidor em " + host + ":" + porta);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("A resposta do servidor é inválida!");
        }
    }

    ContatoBean getByCidade(ContatoBean cob) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
