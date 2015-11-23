/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.msg;

import br.upf.contatos.msg.model.ContatoBean;
import br.upf.contatos.msg.model.Operacao;
import java.io.Serializable;

/**
 *
 * @author mauricley
 */
public interface Request extends Serializable {
    public Request getAll();
    public Request getById(Integer id);
    public Request getByCidade(String cidade);
    public Request insert(ContatoBean c);
    public Request update(ContatoBean c);
    public Request delete(Integer id);
    public Request disconnect();
    public Operacao getOperacao();
    public Integer getId();
    public ContatoBean getContato();
    public String getCidade();
}
