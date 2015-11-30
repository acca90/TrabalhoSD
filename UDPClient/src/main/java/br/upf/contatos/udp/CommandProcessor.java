/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.udp;

import br.upf.contatos.msg.model.ContatoBean;
import br.upf.contatos.udp.cli.Command;
import dnl.utils.text.table.TextTable;
import java.util.List;

/**
 *
 * @author Mauricley
 */
public class CommandProcessor {
    private final Command cmd;
    UDPService service;

    public CommandProcessor(Command cmd) {
        this.cmd = cmd;
        this.service = new UDPService(cmd.getHost(), cmd.getPorta());
    }
    
    public void processa() {
        switch(cmd.getOperacao()) {
            case GETALL:
                getAll(); break;
            case GETBYID:
                getById(); break;
            case GETBYCIDADE:
                getByCidade(); break;
            case INSERT:
                insert(); break;
            case UPDATE:
                update(); break;
            case DELETE:
                delete(); break;
        }
    }

    private void getAll() {
        exibeMsgInicial("Listar Todos");
        exibeContatos(service.getAll());
    }

    private void getById() {
        exibeMsgInicial("Buscar por Id");
        exibeContato(service.getById(cmd.getId()));
    }

    private void getByCidade() {
        exibeMsgInicial("Buscar por Cidade");
        exibeContatos(service.getByCidade(cmd.getCidade()));
    }

    private void insert() {
        exibeMsgInicial("Inserir Contato");
        exibeContato(service.insert(cmd.getContato()));
    }

    private void update() {
        exibeMsgInicial("Atualizar Contato");
        ContatoBean c = service.getById(cmd.getId());
        c.merge(cmd.getContato());
        exibeContato(service.update(c));
    }

    private void delete() {
        exibeMsgInicial("Deletar Contato");
        exibeContato(service.delete(cmd.getId()));
    }

    private void exibeContato(ContatoBean c) {
        StringBuilder saida = new StringBuilder();
        saida.append("Id: ").append(c.getId()).append("\n")
                .append("Nome: ").append(c.getNome()).append("\n")
                .append("Email: ").append(c.getEmail()).append("\n")
                .append("Endereço: ").append(c.getEndereco()).append("\n")
                .append("Complemento: ").append(c.getComplemento()).append("\n")
                .append("CEP: ").append(c.getCep()).append("\n")
                .append("Cidade: ").append(c.getCidade()).append("\n")
                .append("Estado: ").append(c.getEstado()).append("\n")
                .append("Email alternativo: ").append(c.getEmailAlternativo());
        System.out.println(saida);
    }
    
    private void exibeContatos(List<ContatoBean> contatos) {
        String[] colunas = {"Id", "Nome", "Email", "Cidade"};
        Object[][] dados = new Object[contatos.size()][];
        int i = 0;
        for(ContatoBean c: contatos) {
            dados[i] = new Object[4];
            dados[i][0] = c.getId();
            dados[i][1] = c.getNome();
            dados[i][2] = c.getEmail();
            dados[i][3] = c.getCidade();
            i++;
        }
        new TextTable(colunas, dados).printTable();
    }

    private void exibeMsgInicial(String msgInicial) {
        System.out.println("============ "+ msgInicial +" ============");
        System.out.println("Servidor: " + cmd.getHost() + ":" + cmd.getPorta());
    }
}
