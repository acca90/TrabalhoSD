/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.msg.model;

import java.io.Serializable;

/**
 *
 * @author Mauricley
 */
public class ContatoBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nome;
    private String email;
    private String endereco;
    private String complemento;
    private Integer cep;
    private String cidade;
    private String estado;
    private String emailAlternativo;

    public ContatoBean() {
    }
    
    public ContatoBean(Integer id, String nome, String email, String endereco, String complemento, Integer cep, String cidade, String estado, String emailAlternativo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.emailAlternativo = emailAlternativo;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEmailAlternativo() {
        return emailAlternativo;
    }

    public void setEmailAlternativo(String emailAlternativo) {
        this.emailAlternativo = emailAlternativo;
    }
    
    public void merge(ContatoBean other) {
        this.nome = (other.getNome() == null ? this.nome : other.getNome());
        this.email = (other.getEmail() == null ? this.email : other.getEmail());
        this.endereco = (other.getEndereco() == null ? this.endereco : other.getEndereco());
        this.complemento = (other.getComplemento() == null ? this.complemento : other.getComplemento());
        this.cep = (other.getCep() == null ? this.cep : other.getCep());
        this.cidade = (other.getCidade() == null ? this.cidade : other.getCidade());
        this.estado = (other.getEstado() == null ? this.estado : other.getEstado());
        this.emailAlternativo = (other.getEmailAlternativo() == null ? this.emailAlternativo : other.getEmailAlternativo());
    }
}
