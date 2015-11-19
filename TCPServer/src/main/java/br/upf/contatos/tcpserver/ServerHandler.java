/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.tcpserver;

import br.upf.contatos.dal.service.ContatoService;
import br.upf.contatos.tcpmsg.Request;
import br.upf.contatos.tcpmsg.Response;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.upf.contatos.tcpmsg.model.ContatoBean;
import java.net.SocketTimeoutException;

/**
 *
 * @author Mauricley
 */
public class ServerHandler implements Runnable {
    private static int threadId;
    private final Socket conexao;
    private final ObjectInputStream in;
    private final ObjectOutputStream out;
    private final ContatoService service;
    private final Logger logger;


    public ServerHandler(Socket conexao) throws IOException {
        this.conexao = conexao;
        in = new ObjectInputStream(conexao.getInputStream());
        out = new ObjectOutputStream(conexao.getOutputStream());
        service = new ContatoService();
        logger = Logger.getLogger(ServerHandler.class.getName());
        logger.log(Level.INFO, "Thread: " + (++threadId) + "\tCliente conectado: " + conexao.getInetAddress().getHostName());
    }
    
    @Override
    public void run() {
        boolean conectado = true;
        Request req;
        
        while(conectado) {
            try {
                req = (Request) in.readObject();
            } catch (SocketTimeoutException ex) {
                logger.log(Level.SEVERE, "O tempo de espera pela requisição expirou!", ex);
                break;
            } catch (IOException ex) {
                logger.log(Level.SEVERE, "Não foi possível receber a requisição do cliente!", ex);
                break;
            } catch (ClassNotFoundException ex) {
                logger.log(Level.WARNING, "A requisição é inválida!", ex);
                continue;
            }
            try {
                conectado = processaRequest(req);
            } catch (IOException ex) {
                logger.log(Level.WARNING, "Não foi possível enviar a resposta!", ex);
            }
        }
        try {
            logger.log(Level.INFO, "Thread: " + (++threadId) + "\tCliente " + conexao.getInetAddress().getHostName() + " será desconectado!");
            conexao.close();
        } catch (IOException ex) {
            logger.log(Level.WARNING, "Não foi possível fechar a conexão com o cliente", ex);
        }
    }

    private boolean processaRequest(Request req) throws IOException {
        boolean conectado = true;
        Response resp;
        
        switch(req.getOperacao()) {
            case DISCONECT:
                conectado = false;
                resp = disconnect();
                break;
            case GETALL:
                resp = getAll();
                break;
            case GETBYID:
                resp = getById(req.getId());
                break;
            case GETBYCIDADE:
                resp = getByCidade(req.getCidade());
                break;
            case INSERT:
                resp = insert(req.getContato());
                break;
            case UPDATE:
                resp = update(req.getContato());
                break;
            case DELETE:
                resp = delete(req.getId());
                break;
            default:
                resp = badRequest();
        }
        out.writeObject(resp);
        return conectado;
    }

    private Response disconnect() {
        return new ResponseImpl().toDisconnect();
    }

    private Response getAll() {
        return new ResponseImpl().toGetAll(ContatoWrapper.jpa2tcp(service.getAll()));
    }

    private Response getById(Integer idContato) {
        return new ResponseImpl().toGetById(ContatoWrapper.jpa2tcp(service.getById(idContato)));
    }

    private Response getByCidade(String cidade) {
        return new ResponseImpl().toGetByCidade(ContatoWrapper.jpa2tcp(service.getByCidade(cidade)));
    }

    private Response insert(ContatoBean contato) {
        return new ResponseImpl().toInsert(ContatoWrapper.jpa2tcp(service.add(ContatoWrapper.tcp2jpa(contato))));
    }

    private Response update(ContatoBean contato) {
        return new ResponseImpl().toUpdate(ContatoWrapper.jpa2tcp(service.update(ContatoWrapper.tcp2jpa(contato))));
    }

    private Response delete(Integer idContato) {
        return new ResponseImpl().toDelete(ContatoWrapper.jpa2tcp(service.delete(idContato)));
    }

    private Response badRequest() {
        return new ResponseImpl().toBadRequest();
    }
}
