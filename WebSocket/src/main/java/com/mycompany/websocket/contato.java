/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.websocket;

import br.upf.contatos.dal.model.Contato;

/**
 *
 * @author joao
 */
public class contato {
    
    private Integer operacao;
    Contato contato;
    

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Integer getOperacao() {
        return operacao;
    }

    public void setOperacao(Integer operacao) {
        this.operacao = operacao;
    }
    
    
    
    

    
    
}
