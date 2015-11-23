/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.msg;

import br.upf.contatos.msg.Response;
import br.upf.contatos.msg.model.ContatoBean;
import br.upf.contatos.msg.model.Operacao;
import static br.upf.contatos.msg.model.Operacao.DELETE;
import static br.upf.contatos.msg.model.Operacao.GETALL;
import static br.upf.contatos.msg.model.Operacao.GETBYCIDADE;
import static br.upf.contatos.msg.model.Operacao.GETBYID;
import static br.upf.contatos.msg.model.Operacao.INSERT;
import static br.upf.contatos.msg.model.Operacao.UPDATE;
import br.upf.contatos.msg.model.Status;
import java.util.List;
import static br.upf.contatos.msg.model.Operacao.DISCONNECT;

/**
 *
 * @author Mauricley
 */
public class ResponseImpl implements Response {
    private Operacao operacao;
    private ContatoBean contato;
    private List<ContatoBean> contatos;
    private Status status;
    private String errMsg;
    
    @Override
    public Response toGetAll(List<ContatoBean> contatos) {
        this.operacao = Operacao.GETALL;
        this.contatos = contatos;
        setStatus();
        return this;
    }

    @Override
    public Response toGetById(ContatoBean c) {
        this.operacao = Operacao.GETBYID;
        this.contato = c;
        setStatus();
        return this;
    }

    @Override
    public Response toGetByCidade(List<ContatoBean> contatos) {
        this.operacao = Operacao.GETBYCIDADE;
        this.contatos = contatos;
        setStatus();
        return this;
    }

    @Override
    public Response toInsert(ContatoBean c) {
        this.operacao = Operacao.INSERT;
        this.contato = c;
        setStatus();
        return this;
    }

    @Override
    public Response toUpdate(ContatoBean c) {
        this.operacao = Operacao.UPDATE;
        this.contato = c;
        setStatus();
        return this;
    }

    @Override
    public Response toDelete(ContatoBean c) {
        this.operacao = Operacao.DELETE;
        this.contato = c;
        setStatus();
        return this;
    }

    @Override
    public String getError() {
        return this.errMsg;
    }
    
    @Override
    public Status getStatus() {
        return status;
    }
    
    @Override
    public Response toDisconnect() {
        this.operacao = Operacao.DISCONNECT;
        setStatus();
        return this;
    }

    @Override
    public Response toBadRequest() {
        this.operacao = null;
        setStatus();
        return this;
    }

    @Override
    public ContatoBean getContato() {
        return this.contato;
    }

    @Override
    public List<ContatoBean> getContatos() {
        return this.contatos;
    }
    
    private void setStatus() {
        this.status = Status.OK;
        switch(operacao) {
            case GETALL:
                validaContatos("Não existe nenhum contado!");
                break;
            case GETBYCIDADE:
                validaContatos("Não foi encontrado nenhum contato com a cidade informada!");
                break;
            case GETBYID:
                validaContato("Não existe nenhum contato com o id informado!");
                break;
            case INSERT:
                validaContato("Ocorreu um problema ao tentar adicionar o contato!");
                break;
            case UPDATE:
                validaContato("Ocorreu um problema ao tentar alterar o contato!");
                break;
            case DELETE:
                validaContato("Ocorreu um problema ao tentar excluir o contato!");
                break;
            case DISCONNECT:
                break;
            default:
                this.errMsg = "Requisição inválida!";
            }
        }

    private void validaContato(String errMsg) {
        if (contato == null) {
            status = Status.ERROR;
            this.errMsg = errMsg;
        }
    }

    private void validaContatos(String errMsg) {
        if (contatos == null || contatos.isEmpty()) {
            this.status = Status.ERROR;
            this.errMsg = errMsg;
        }
    }
}
