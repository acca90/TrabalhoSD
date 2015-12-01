/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.tcp;

import br.upf.contatos.msg.model.ContatoBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.json.JSONObject;

/**
 *
 * @author kaeff
 */
public class Client {
    private static final int PORTA = 2005;
    private static  String HOST = "localhost";
    public static void main(String []args) throws RuntimeException {
       
        String teste = Arrays.toString(args);
         if(args.length==0){
            Client.HOST = "localhost";
        }else{
            Client.HOST = args[0];            
            }
       
       String line;
       do{        
        System.out.println("Digite help, para o menu");
        Scanner scanner = new Scanner(System.in);   
         line = scanner.nextLine();
       }while(comando(line));
      
        
    } 
    

  public static boolean comando(String line){

      ClientConnector tcpService = new ClientConnector(Client.HOST, PORTA);
              
      if (line.equals("help")) {    
        System.out.println("op=[ listar, incluir, editar, deletar, parar, cidade ], label = valor");
        System.out.println("Para sair digite: parar");           
        

        return true;       

    } else { 
        
        tcpService.connect();
        String[] chamadas = line.split(",");       
        String[] op = chamadas[0].split("=");    
            
         switch(op[1]) {     
             case "listar":  
        
                for(ContatoBean cb: tcpService.getAll()) {
                    //System.out.println(new JSONObject(cb));
                    System.out.println("ID:" + cb.getId());
                    System.out.println("Nome:" + cb.getNome());
                    System.out.println("Email:" + cb.getEmail());
                    System.out.println("Endereco:" + cb.getEndereco());
                    System.out.println("Comp:" + cb.getComplemento());
                    System.out.println("CEP:" + cb.getCep());
                    System.out.println("Cidade:" + cb.getCidade());
                    System.out.println("Estado:" + cb.getEstado());
                    System.out.println("Email Alt.:" + cb.getEmailAlternativo());
                    System.out.println("------------");
                }
            
               
                
                      
            break;
              
            case "id":
                String idList;
                Scanner scan2 = new Scanner (System.in); 
                System.out.println("Informe o id do contato à listar:");
                idList = scan2.nextLine();
                int numIdList = Integer.parseInt(idList);
                ContatoBean conbean = new ContatoBean();
                conbean.setId(numIdList);
               
                 try {
                    conbean = tcpService.getById(numIdList);
                    System.out.println("O contato buscado é: ");
                    System.out.println(new JSONObject(conbean));
                 } catch(RuntimeException e) {
                    System.out.println(e.getMessage());
                }

                
                
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
            //System.out.printf("Digite o id:");   
            //id = scan.nextLine();                     *** Pegar ID Informado
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
            int numId = 0;    // ***  ID Sequencial automatico
            //int idsec = Integer.parseInt(id); *** Pegar ID Informado
            c.setId(numId);   //  ***  ID Sequencial automatico
            //c.setId(idsec);                   *** Pegar ID Informado
            c.setNome(nome);
            c.setEmail(email);
            c.setEndereco(end);
            c.setComplemento(comp);
            c.setCep(numCep);
            c.setCidade(cid);
            c.setEstado(est);
        try {
                 c = tcpService.insert(c);
                 System.out.println("O contato foi adicionado com Sucesso! Segue abaixo as campos informados: ");
                 System.out.println(new JSONObject(c));
             } catch(RuntimeException e) {
                 System.out.println(e.getMessage());
             }

       
                
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
                 
                do{
                String opEditar;

                System.out.print("Escolha os atributos à alterar (nome, email, endereco, complemento, cep, cidade, estado, parar): ");
                opEditar = sca.nextLine();      
                
                if(opEditar.equals("parar")){
                 break;   
                }else{
                String id2 = null;
                String nome2 = null;
                String email2 = null;
                String end2 = null;
                String comp2 = null;
                String cep2 = null;
                String cid2 = null;
                String est2;
                ContatoBean contact = new ContatoBean();
                
                try {
                 contact = tcpService.getById(numIdEdi);
             } catch(RuntimeException e) {
                 System.out.println(e.getMessage());
                 break;
             }
                
                
                switch(opEditar) {        
                     
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
                     case "estado":
                         System.out.printf("Digite o estado:");
                         est2 = sca.nextLine();
                         contact.setEstado(est2);
                         break;
                        
                }
            
                        try {
                           contact = tcpService.update(contact);
                           System.out.println("O contato foi alterado! Segue abaixo suas informações atualizadas: ");
                           System.out.println(new JSONObject(contact));
                       } catch(RuntimeException e) {
                           System.out.println(e.getMessage());
                       }
                }
            }while(true);
                
                }else{
                break;
                } 
                
            }
          break;
            case "cidade":
                String cidade;
                ArrayList cidade2 = new ArrayList();
                Scanner scanner = new Scanner (System.in);
                System.out.println("Informe a cidade à listar"); 
                cidade = scanner.nextLine();
                
               
                try {
                 cidade2 =  (ArrayList) tcpService.getByCidade(cidade);
                // System.out.println(new JSONObject(cidade2));
                System.out.println("Segue os contatos que moram na cidade de " + cidade + ":");
                  for(Object cb: cidade2) {
                    System.out.println(new JSONObject(cb));
                }
                } catch(RuntimeException e) {
                 System.out.println(e.getMessage());
                }

            
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
                 System.out.println("O contato com o id" + numIdDel + " foi deletado!");
                 System.out.println(new JSONObject(cb));
             } catch(RuntimeException e) {
                 System.out.println(e.getMessage());
             }
             
                break;
                
            case "parar":
                
                 tcpService.disconnect();
                 System.out.println("Encerrando Aplicação...");
                 return false;
         }   
         
    }
     
     return true;    
  }

       
    

  
    
    
}