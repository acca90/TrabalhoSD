/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.tcpclient;

import br.upf.contatos.msg.model.ContatoBean;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonas
 */
public class ClientConsoleOperation {
    public String getCidade() {
        String cidade = "";
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\n=====================================");
        System.out.println("Buscar Contatos Por Cidade");
        System.out.println("=====================================");
        System.out.println("Digite o Nome da Cidade: ");
        try {
            cidade = bf.readLine();
        } catch (IOException ex) {
        }
        return cidade;
    }
    
    public Integer menuPrincipal(){
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\n==============Operações==============");
        System.out.println("                Adicionar Contato - 1");
        System.out.println("                  Alterar Contato - 2");
        System.out.println("                  Excluir Contato - 3");
        System.out.println("               Consultar Contatos - 4");
        System.out.println(" Consultar Contatos De Uma Cidade - 5");
        System.out.println("                Finalizar Cliente - 6");
        System.out.println("=====================================");
        System.out.println("Que operação você deseja realizar: ");
        System.out.println("=====================================");
        Integer opcao = 4;
        try{
            opcao = Integer.valueOf(bf.readLine());
        }catch(IOException | NumberFormatException e){
            System.out.println("Operação incorreta!");
        }
        return opcao;
    }
    public ContatoBean getContatoToInsertConsole(){
        ContatoBean contato = new ContatoBean();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\n=====================================");
        System.out.println("Adicionar Contato");
        System.out.println("=====================================");
        contato.setId(-1);
        while(contato.getId() < 0){
            try{
                System.out.print("Digite o Código do Contato: ");
                contato.setId(Integer.valueOf(bf.readLine()));
            }catch(IOException | NumberFormatException e){
                System.out.print("Código inválido! ");
            }
        }
        try {
            System.out.print("Digite o Nome do Contato: ");
            contato.setNome(bf.readLine());
            System.out.print("Digite o Email do Contato: ");
            contato.setEmail(bf.readLine());
            System.out.print("Digite o Endereço do Contato: ");
            contato.setEndereco(bf.readLine());
            System.out.print("Digite o Complemento do Contato: ");
            contato.setComplemento(bf.readLine());
            contato.setCep(-1);
        } catch (IOException ex) {
            Logger.getLogger(ClientConsoleOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(contato.getCep() < 0){
            try{
                System.out.print("Digite o Cep do Contato: ");
                contato.setCep(Integer.valueOf(bf.readLine()));
            }catch(IOException | NumberFormatException e){
                System.out.print("Cep inválido! ");
            }
        }
        try{
            System.out.print("Digite o Cidade do Contato: ");
            contato.setCidade(bf.readLine());
            System.out.print("Digite o Estado do Contato: ");
            contato.setEstado(bf.readLine());
            System.out.print("Digite o Email Alternativo do Contato: ");
            contato.setEmailAlternativo(bf.readLine());
        } catch (IOException ex) {
            Logger.getLogger(ClientConsoleOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("=====================================");
        return contato;
    }
    public ContatoBean getContatoToUpdateConsole(){
        ContatoBean contato = new ContatoBean();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\n=====================================");
        System.out.println("Atualizar Contato");
        System.out.println("=====================================");
        contato.setId(-1);
        while(contato.getId() < 0){
            try{
                System.out.print("Digite o Código do Contato a ser alterado: ");
                contato.setId(Integer.valueOf(bf.readLine()));
            }catch(IOException | NumberFormatException e){
                System.out.print("Código inválido! ");
            }
        }
        try{
            System.out.print("Digite o Novo Nome do Contato: ");
            contato.setNome(bf.readLine());
            System.out.print("Digite o Novo Email do Contato: ");
            contato.setEmail(bf.readLine());
            System.out.print("Digite o Novo Endereço do Contato: ");
            contato.setEndereco(bf.readLine());
            System.out.print("Digite o Novo Complemento do Contato: ");
            contato.setComplemento(bf.readLine());
        } catch (IOException ex) {
            Logger.getLogger(ClientConsoleOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
        contato.setCep(-1);
        while(contato.getCep() < 0){
            try{
                System.out.print("Digite o Novo Cep do Contato: ");
                contato.setCep(Integer.valueOf(bf.readLine()));
            }catch(IOException | NumberFormatException e){
                System.out.print("Cep inválido! ");
            }
        }
        try{
            System.out.print("Digite o Novo Cidade do Contato: ");
            contato.setCidade(bf.readLine());
            System.out.print("Digite o Novo Estado do Contato: ");
            contato.setEstado(bf.readLine());
            System.out.print("Digite o Novo Email Alternativo do Contato: ");
            contato.setEmailAlternativo(bf.readLine());
        } catch (IOException ex) {
            Logger.getLogger(ClientConsoleOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("=====================================");
        return contato;
    }
    
    public Integer getContatoToDeleteConsole() {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\n=====================================");
        System.out.println("Excluir Contato");
        System.out.println("=====================================");
        Integer codigo = -1;
        while(codigo < 0){
            try{
                System.out.print("Digite o Código do Contato a ser excluido: ");
                codigo = Integer.valueOf(bf.readLine());
            }catch(IOException | NumberFormatException e){
                System.out.print("Código inválido! ");
            }
        }
        return codigo;
    }
    
    public char getOpcaoListar(){
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Deseja listar os contatos(y/n)?");
        try {
            if("y".equals(bf.readLine())){
                return 'y';
            }
        } catch (IOException ex) {
        }
        return 'n';
    }
    
    public void mostraContatosConsole(List<ContatoBean> contatos){
        System.out.println("\n=====================================");
        System.out.println("Listagem de Contatos Encontrados:");
        System.out.println("=====================================");
        for(ContatoBean c: contatos) {
            System.out.println("Código: " + c.getId() + "\tNome: " + c.getNome() + "\tEmail: " + c.getEmail());
        }
        System.out.println("=====================================");
    }
}
