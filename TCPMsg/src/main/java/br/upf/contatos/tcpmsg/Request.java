/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.tcpmsg;

import br.upf.contatos.tcpmsg.model.ContatoBean;
import br.upf.contatos.tcpmsg.model.Operacao;
import java.io.Serializable;

/**
 *
 * @author Mauricley
 */
public class Request implements Serializable {
    private final Operacao operacao;
    private int idContato;
    private String cidade = null;
    private ContatoBean contato;
    
    public Request(Operacao operacao) {
        this.operacao = operacao;
    }

    public Request(Operacao operacao, int id) {
        this.operacao = operacao;
        this.idContato = id;
    }

    public Request(Operacao operacao, String cidade) {
        this.operacao = operacao;
        this.cidade = cidade;
    }

    public Request(Operacao operacao, ContatoBean contato) {
        this.operacao = operacao;
        this.contato = contato;
    }

    public Operacao getOperacao() {
        return operacao;
    }

    public int getIdContato() {
        return idContato;
    }

    public String getCidade() {
        return cidade;
    }

    public ContatoBean getContato() {
        return contato;
    }
    
    
}


