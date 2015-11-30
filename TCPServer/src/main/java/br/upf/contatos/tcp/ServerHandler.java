/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.tcp;

import br.upf.contatos.dal.model.Contato;
import br.upf.contatos.dal.service.ContatoService;
import br.upf.contatos.msg.Request;
import br.upf.contatos.msg.Response;
import br.upf.contatos.msg.ResponseImpl;
import br.upf.contatos.msg.model.ContatoBean;
import static br.upf.contatos.msg.model.Operacao.DELETE;
import static br.upf.contatos.msg.model.Operacao.DISCONNECT;
import static br.upf.contatos.msg.model.Operacao.GETALL;
import static br.upf.contatos.msg.model.Operacao.GETBYCIDADE;
import static br.upf.contatos.msg.model.Operacao.GETBYID;
import static br.upf.contatos.msg.model.Operacao.INSERT;
import static br.upf.contatos.msg.model.Operacao.UPDATE;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.SocketTimeoutException;
import java.util.List;

/**
 *
 * @author Mauricley
 */
public class ServerHandler implements Runnable {
    private final long threadId;
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
        threadId = Thread.currentThread().getId();
        logger.log(Level.INFO, "Thread: " + (threadId) + "\tCliente conectado: " + conexao.getInetAddress().getHostName());
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
            logger.log(Level.INFO, "Thread: " + threadId + "\tCliente " + conexao.getInetAddress().getHostName() + " será desconectado!");
            conexao.close();
        } catch (IOException ex) {
            logger.log(Level.WARNING, "Não foi possível fechar a conexão com o cliente", ex);
        }
    }

    private boolean processaRequest(Request req) throws IOException {
        boolean conectado = true;
        Response resp;
        
        switch(req.getOperacao()) {
            case DISCONNECT:
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
        try {
            List<Contato> contatos = service.getAll();
            return new ResponseImpl().toGetAll(ContatoWrapper.jpa2tcp(contatos));
        } catch (RuntimeException e) {
            return new ResponseImpl().defineError(e.getMessage());
        }
    }

    private Response getById(Integer idContato) {
        try {
            Contato c = service.getById(idContato);
            return new ResponseImpl().toGetById(ContatoWrapper.jpa2tcp(c));
        } catch (RuntimeException e) {
            return new ResponseImpl().defineError(e.getMessage());
        }
    }

    private Response getByCidade(String cidade) {
        try {
            List<Contato> contatos = service.getByCidade(cidade);
            return new ResponseImpl().toGetByCidade(ContatoWrapper.jpa2tcp(contatos));
        } catch (RuntimeException e) {
            return new ResponseImpl().defineError(e.getMessage());
        }
    }

    private Response insert(ContatoBean contato) {
        try {
            Contato c = service.add(ContatoWrapper.tcp2jpa(contato));
            return new ResponseImpl().toInsert(ContatoWrapper.jpa2tcp(c));
        } catch (RuntimeException e) {
            return new ResponseImpl().defineError(e.getMessage());
        }
    }

    private Response update(ContatoBean contato) {
        try {
            Contato c = service.update(ContatoWrapper.tcp2jpa(contato));
            return new ResponseImpl().toUpdate(ContatoWrapper.jpa2tcp(c));
        } catch (RuntimeException e) {
            return new ResponseImpl().defineError(e.getMessage());
        }
    }

    private Response delete(Integer idContato) {
        try {
            Contato c = service.delete(idContato);
            return new ResponseImpl().toDelete(ContatoWrapper.jpa2tcp(c));
        } catch (RuntimeException e) {
            return new ResponseImpl().defineError(e.getMessage());
        }
    }

    private Response badRequest() {
        return new ResponseImpl().toBadRequest();
    }
}
