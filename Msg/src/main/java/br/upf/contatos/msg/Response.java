/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.msg;

import br.upf.contatos.msg.model.ContatoBean;
import br.upf.contatos.msg.model.Status;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author mauricley
 */
public interface Response extends Serializable {
    public Response toGetAll(List<ContatoBean> contatos);
    public Response toGetById(ContatoBean c);
    public Response toGetByCidade(List<ContatoBean> contatos);
    public Response toInsert(ContatoBean c);
    public Response toUpdate(ContatoBean c);
    public Response toDelete(ContatoBean c);
    public Response toDisconnect();
    public Response toBadRequest();
    public ContatoBean getContato();
    public List<ContatoBean> getContatos();
    public Status getStatus();
    public String getError();
}
