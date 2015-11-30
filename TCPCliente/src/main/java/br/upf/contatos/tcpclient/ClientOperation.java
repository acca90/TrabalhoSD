/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.tcpclient;

import br.upf.contatos.msg.Request;
import br.upf.contatos.msg.RequestImpl;
import br.upf.contatos.msg.Response;
import br.upf.contatos.msg.model.ContatoBean;
import br.upf.contatos.msg.model.Status;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author jonas
 */
public class ClientOperation {
    private final ClientConsoleOperation console;
    private Socket s;
    private ObjectInputStream le;
    private ObjectOutputStream escreve;
    private final String host;
    private final Integer porta;
    
    public ClientOperation(String host, Integer porta){
        this.console = new ClientConsoleOperation();
        this.host = host;
        this.porta = porta;
    }
    
    public void conecta(){
        try {
            s = new Socket(host, porta);
            escreve = new ObjectOutputStream(s.getOutputStream());
            le = new ObjectInputStream(s.getInputStream());
        } catch (IOException ex) {
            System.out.println("Não foi possível se conectar ao servidor!");
        }
    }
    
    public void insert(){
        try {
            ContatoBean contato = console.getContatoToInsertConsole();
            Request request = new RequestImpl().insert(contato);
            Response response = sendRequest(request);
            if (response.getStatus() == Status.OK) {
                System.out.println("Contato inserido com sucesso!");
            } else {
                System.out.println("Contato não inserido! " + response.getError());
            }
            if (console.getOpcaoListar() == 'y') {
                search();
            }
        } catch (Exception ex) {
            System.out.println("Houve um problema! Não será possível inserir este contato!");
        }
    }
    
    public void update(){
        ContatoBean contato = console.getContatoToUpdateConsole();
        Request request = new RequestImpl().update(contato);
        Response response = sendRequest(request);
        if (response.getStatus() == Status.OK) {
            System.out.println("Contato alterado com sucesso!");
        } else {
            System.out.println("Contato não alterado! " + response.getError());
        }
        if (console.getOpcaoListar() == 'y') {
            search();
        }
    }

    public void delete() {
        Integer codigo = console.getContatoToDeleteConsole();
        Request request = new RequestImpl().delete(codigo);
        Response response = sendRequest(request);
        if (response.getStatus() == Status.OK) {
            System.out.println("Contato excluido com sucesso!");
        } else {
            System.out.println("Contato não excluido! " + response.getError());
        }
        if (console.getOpcaoListar() == 'y') {
            search();
        }
    }

    public void searchByCidade() {
        String cidade = console.getCidade();
        if(cidade.equals("")){
            System.out.println("Você não digitou o nome da cidade!");
        }else{
            Request request = new RequestImpl().getByCidade(cidade);
            Response response = sendRequest(request);
            if (response.getStatus() == Status.OK) {
                console.mostraContatosConsole(response.getContatos());
            } else {
                System.out.println(response.getError());
            }
        }
    }

    public void search() {
        Request request = new RequestImpl().getAll();
        Response response = sendRequest(request);
        if (response.getStatus() == Status.OK) {
            console.mostraContatosConsole(response.getContatos());
        } else {
            System.out.println(response.getError());
        }
    }
    
    public void desconecta() {
        Request request = new RequestImpl().disconnect();
        Response response = sendRequest(request);
        if (response.getStatus() == Status.OK) {
            System.out.println("Servidor desconectado!");
        } else {
            System.out.println(response.getError());
        }
        try {
            le.close();
            escreve.close();
            s.close();
        } catch (IOException ex) {
            System.out.println("Erro ao desconectar!");
        }
    }
    
    public Response sendRequest(Request request){
        Response response = null;
        try {
            escreve.writeObject(request);
            response = (Response) le.readObject();
        } catch (IOException ex) {
            System.out.println("Erro ao receber resposta do servidor TCP!");
        } catch (ClassNotFoundException ex) {
            System.out.println("Resposta inválida do servidor TCP!");
        }
        return response;
    }
}
