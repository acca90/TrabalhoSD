/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.tcp;

import br.upf.contatos.msg.model.ContatoBean;
import java.util.Scanner;
import org.json.JSONObject;

/**
 *
 * @author kaeff
 */
public class Client {
    private static final int PORTA = 2005;
    private static final String HOST = "localhost";
    public static void main(String []args) throws RuntimeException {
        
        while (true) {
        System.out.println("Digite help, para o menu");
        Scanner scanner = new Scanner(System.in);   
        String line = scanner.nextLine();
        comando(line);
   }   
        
    } 
  public static boolean comando(String line){
       
        
      
      if (line.equals("help")) {    
        System.out.println("listar, incluir, editar, deletar, parar");
        System.out.println("Para sair digite: parar");           
        return true;
    } else if (line.equals("parar")) {       
        return false;
        
    } else { 
         ClientConnector tcpService = new ClientConnector(HOST, PORTA);
        tcpService.connect();
        String[] chamadas = line.split(",");       
        String[] op = chamadas[0].split("=");    
            
         switch(op[1]) {     
             case "listar":  
        
                for(ContatoBean cb: tcpService.getAll()) {
                    System.out.println(new JSONObject(cb));
                }
            
                tcpService.disconnect();
                
                      
            break;
              
            case "incluir":
                String id;
                String nome;
                String email;
                String end;
                String comp;
                String cep;
                String cid;
                String est;
        
       
            Scanner scan = new Scanner (System.in); 
           
            System.out.printf("Digite o id:");
            id = scan.nextLine();
            System.out.printf("Digite o nome:");
            nome = scan.nextLine();
            System.out.printf("Digite o email:");
            email = scan.nextLine();
            System.out.printf("Digite o endereco:");
            end = scan.nextLine();
            System.out.printf("Digite o complemento:");
            comp = scan.nextLine();
            System.out.printf("Digite o cep:");
            cep = scan.nextLine();
            System.out.printf("Digite a cidade:");
            cid = scan.nextLine();
            System.out.printf("Digite o estado:");
            est = scan.nextLine();
        
            ContatoBean c = new ContatoBean();
            int numCep = Integer.parseInt(cep);
            int numId = Integer.parseInt(id);
            c.setId(numId);
            c.setNome(nome);
            c.setEmail(email);
            c.setEndereco(end);
            c.setComplemento(comp);
            c.setCep(numCep);
            c.setCidade(cid);
            c.setEstado(est);
        try {
                 c = tcpService.insert(c);
                 System.out.println(new JSONObject(c));
             } catch(RuntimeException e) {
                 System.out.println(e.getMessage());
             }

             tcpService.disconnect();
                
             break;
                
            case "editar": 
                
                while (true){
                String resp;
                String nummId;
                Scanner sca = new Scanner (System.in);
                System.out.println("Deseja alterar um contato? responda sim ou não");
                resp = sca.nextLine();
                if(resp.equals("sim")){
                
                System.out.println("Informe o id do contato à editar");
                nummId = sca.nextLine();
                
                ContatoBean contB = new ContatoBean();
                int numIdEdi = Integer.parseInt(nummId);
                
                 
                String opEditar;

                System.out.print("Escolha os atributos à alterar (id, nome, email, endereco, complemento, cep, cidade, estado, parar): ");
                opEditar = sca.nextLine();      
                
                
                String id2 = null;
                String nome2 = null;
                String email2 = null;
                String end2 = null;
                String comp2 = null;
                String cep2 = null;
                String cid2 = null;
                String est2;
                ContatoBean contact = new ContatoBean();
                contact = tcpService.getById(numIdEdi);
                
                switch(opEditar) {        
                     case "id":
                       System.out.printf("Digite o id:");
                       id2 = sca.nextLine(); 
                       int numIdEdit = Integer.parseInt(id2);
                       contact.setId(numIdEdit);
                         break;
                     case "nome":
                         System.out.printf("Digite o nome:");
                         nome2 = sca.nextLine();
                         contact.setNome(nome2);
                         break;
                     case "email":
                        System.out.printf("Digite o email:");
                        email2 = sca.nextLine(); 
                        contact.setEmail(email2);
                         break;
                     case "endereco":
                         System.out.printf("Digite o endereco:");
                         end2 = sca.nextLine();
                         contact.setEndereco(end2);
                         break;
                     case "complemento":
                         System.out.printf("Digite o complemento:");
                          comp2 = sca.nextLine();
                         contact.setComplemento(comp2);
                         break;
                     case "cep":
                         System.out.printf("Digite o cep:");
                         cep2 = sca.nextLine();
                         int numCepEdit = Integer.parseInt(cep2);
                         contact.setCep(numCepEdit);
                         break;
                     case "cidade":
                         System.out.printf("Digite o cidade:");
                         cid2 = sca.nextLine();
                         contact.setCidade(cid2);
                         break;
                     case "parar":
                         return false;
                     case "estado":
                        System.out.printf("Digite o estado:");
                        est2 = sca.nextLine();
                        contact.setEstado(est2);
                         break;
                        
                }
            
            
           
                         
              try {
                 contact = tcpService.update(contact);
                 System.out.println(new JSONObject(contact));
             } catch(RuntimeException e) {
                 System.out.println(e.getMessage());
             }

             tcpService.disconnect();
                 
                }else{
                return false;
                } 
               
            }
          
            case "cidade":
                String cidade;
                Scanner scanner = new Scanner (System.in);
                System.out.println("Informe a cidade à listar"); 
                cidade = scanner.nextLine();
                
                ContatoBean cob = new ContatoBean();
                cob.setCidade(cidade);
                
                try {
                 cob = tcpService.getByCidade(cob);
                 System.out.println(new JSONObject(cob));
                } catch(RuntimeException e) {
                 System.out.println(e.getMessage());
                }

             tcpService.disconnect();
            break;
                
            case "deletar":
                String idDel;
                Scanner scann = new Scanner (System.in);
                System.out.println("Informe o id do contato à deletar"); 
                idDel = scann.next();
                int numIdDel = Integer.parseInt(idDel);
                ContatoBean cb = new ContatoBean();
                cb.setId(numIdDel);
            try {
                 cb = tcpService.delete(numIdDel);
                 System.out.println(new JSONObject(cb));
             } catch(RuntimeException e) {
                 System.out.println(e.getMessage());
             }

             tcpService.disconnect();
                break;
         }   
            }
        return false;
         
}

       
    

  
    
    
}