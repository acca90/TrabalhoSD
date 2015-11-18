/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.tcpmsg;

import br.upf.contatos.tcpmsg.model.ContatoBean;
import br.upf.contatos.tcpmsg.model.Operacao;
import br.upf.contatos.tcpmsg.model.Status;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Mauricley
 */
public class Response implements Serializable {
    private final Operacao operacao;
    private ContatoBean contato;
    private List<ContatoBean> contatos;
    private Status status;
    private String errMsg;
    
    public Response(Operacao operacao) {
        this.operacao = operacao;
    }

    public Response(Operacao operacao, ContatoBean contato) {
        this.operacao = operacao;
        this.contato = contato;
        setStatus();
    }

    public Response(Operacao operacao, List<ContatoBean> contatos) {
        this.operacao = operacao;
        this.contatos = contatos;
        setStatus();
    }

    public Response(Operacao operacao, String errMsg) {
        this.operacao = operacao;
        this.status = Status.ERROR;
        this.errMsg = errMsg;
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
            }
        }

    public Operacao getOperacao() {
        return operacao;
    }

    public ContatoBean getContato() {
        return contato;
    }

    public List<ContatoBean> getContatos() {
        return contatos;
    }

    public Status getStatus() {
        return status;
    }

    public String getErrMsg() {
        return errMsg;
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
