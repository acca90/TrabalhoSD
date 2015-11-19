/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import br.upf.contatos.dal.model.Contato;
import java.util.List;

/**
 *
 * @author joaov
 */
public class retorno {
    private int codigo;
    private String msg;
    private Contato contato;
    private List<Contato> lista;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public List<Contato> getLista() {
        return lista;
    }

    public void setLista(List<Contato> lista) {
        this.lista = lista;
    }
    
    

    

   
    
    
    
}
