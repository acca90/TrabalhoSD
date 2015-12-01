/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.udp.cli;

import br.upf.contatos.msg.model.ContatoBean;
import br.upf.contatos.msg.model.Operacao;
import com.beust.jcommander.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mauricley
 */
public class Command {
    @Parameter
    private List<String> parametros = new ArrayList<>();
    
    @Parameter (names = {"-h", "--host"}, description = "Endereço do servidor UDP (nome ou IP)")
    private String host = "localhost";
    
    @Parameter (names = {"--porta"}, description = "Porta do servidor UDP.")
    private Integer porta = 2010;

    @Parameter (names = {"-o", "--operacao"}, required = true,
            description = "Operação a ser executada",
            converter = OperacaoConverter.class)
    private Operacao operacao;
    
    @Parameter (names = {"-i", "--id"}, description = "Id do contato. Obrigatório quando a operação for GETBYID, UPDATE ou DELETE.")
    private Integer id;
    
    @Parameter (names = {"-n", "--nome"}, description = "Nome do contato. Obrigatório quando a operação for INSERT.")
    private String nome;
    
    @Parameter (names = {"-e", "--email"}, description = "Email do contato. Obrigatório quando a operação for INSERT.")
    private String email;
    
    @Parameter (names = {"-r", "--endereco"}, description = "Endereço do contato.")
    private String endereco;
    
    @Parameter (names = {"-l", "--complemento"}, description = "Complemento do endereço do contato.")
    private String complemento;
    
    @Parameter (names = {"-p", "--cep"}, description = "CEP do contato.")
    private Integer cep;
    
    @Parameter (names = {"-c", "--cidade"}, description = "Cidade do contato. Obrigatório quando a operação for GETBYCIDADE.")
    private String cidade;
    
    @Parameter (names = {"-u", "--estado"}, description = "Estado do contato.")
    private String estado;
    
    @Parameter (names = {"-a", "--email_alt"}, description = "Email alternativo do contato.")
    private String emailAlternativo;
    
    public Operacao getOperacao() {
        return operacao;
    }

    public Integer getId() {
        return id;
    }

    public String getCidade() {
        return cidade;
    }
    
    public String getHost() {
        return host;
    }
    
    public Integer getPorta() {
        return porta;
    }
    
    public ContatoBean getContato() {
        return new ContatoBean(id, nome, email, endereco, complemento, cep, cidade, estado, emailAlternativo);
    }
    
    public boolean valida() {
        boolean ehValido = true;
        switch(this.operacao) {
            case GETBYID:
            case DELETE:
                if (id == null) {
                    System.out.println("A operação " + this.operacao + " exige que o id seja informado.");
                    ehValido = false;
                }
                break;
            case GETBYCIDADE:
                if (cidade == null) {
                    System.out.println("A operação GETBYCIDADE exige que a cidade seja informada.");
                    ehValido = false;
                }
                break;
            case UPDATE:
                if (id == null || (nome == null && email == null && endereco == null &&
                        complemento == null && cep == null && cidade == null &&
                        estado == null && emailAlternativo == null) ) {
                    System.out.println("A operação UPDATE exige que o id seja informado, além de algum algum outro atributo do contato.");
                    ehValido = false;
                }
                break;
            case INSERT:
                if (nome == null || email == null) {
                    System.out.println("A operação INSERT exige que pelo menos nome e email do contato sejam informados.");
                    ehValido = false;
                }
                break;
        }
        return ehValido;
    }
}