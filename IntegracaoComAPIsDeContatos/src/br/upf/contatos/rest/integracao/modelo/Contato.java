/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.upf.contatos.rest.integracao.modelo;

public class Contato{
    private Integer codigo;
    private String nome;
    private String email;
    private String endereco;
    private String complemento;
    private Integer cep;
    private String cidade;
    private String estado;
    private String email_alter;

    public Contato() {
    }

    public Contato(Integer codigo, String nome, String email, String endereco, String complemento, Integer cep, String cidade, String estado, String email_alter) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.email_alter = email_alter;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
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

    public String getEmail_alter() {
        return email_alter;
    }

    public void setEmail_alter(String email_alter) {
        this.email_alter = email_alter;
    }

    @Override
    public String toString() {
        return nome;
    }
}
