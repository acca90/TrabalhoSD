/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.tcpserver;

import br.upf.contatos.dal.service.ContatoService;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.upf.contatos.tcpmsg.Request;
import br.upf.contatos.tcpmsg.Response;
import br.upf.contatos.tcpmsg.model.ContatoBean;
import br.upf.contatos.tcpmsg.model.Operacao;
import br.upf.contatos.tcpmsg.model.Status;

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
            } catch (IOException ex) {
                logger.log(Level.SEVERE, "Não foi possível receber a requisição do cliente!", ex);
                break;
            } catch (ClassNotFoundException ex) {
                logger.log(Level.SEVERE, "A requisição é inválida!", ex);
                continue;
            }
            try {
                conectado = processaRequest(req);
            } catch (IOException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }
        try {
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
                resp = getById(req.getIdContato());
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
                resp = delete(req.getIdContato());
                break;
            default:
                resp = new Response(req.getOperacao(), "Operação inválida!");
        }
        out.writeObject(resp);
        return conectado;
    }

    private Response disconnect() {
        return new Response(Operacao.DISCONECT);
    }

    private Response getAll() {
        return new Response(Operacao.GETALL, ContatoWrapper.jpa2tcp(service.getAll()));
    }

    private Response getById(int idContato) {
        return new Response(Operacao.GETBYID, ContatoWrapper.jpa2tcp(service.getById(idContato)));
    }

    private Response getByCidade(String cidade) {
        return new Response(Operacao.GETBYCIDADE, ContatoWrapper.jpa2tcp(service.getByCidade(cidade)));
    }

    private Response insert(ContatoBean contato) {
        return new Response(Operacao.INSERT, ContatoWrapper.jpa2tcp(service.add(ContatoWrapper.tcp2jpa(contato))));
    }

    private Response update(ContatoBean contato) {
        return new Response(Operacao.UPDATE, ContatoWrapper.jpa2tcp(service.update(ContatoWrapper.tcp2jpa(contato))));
    }

    private Response delete(int idContato) {
        return new Response(Operacao.INSERT, ContatoWrapper.jpa2tcp(service.delete(idContato)));
    }
}
