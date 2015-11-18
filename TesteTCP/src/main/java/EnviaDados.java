/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import br.upf.contatos.dal.model.Contato;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Matheus Sobucki
 */
public class EnviaDados {
    
    public static void main(String[] args) throws IOException {
        
        String id;
        String nome;
        String email;
        String end;
        String comp;
        String cep;
        String cid;
        String est;
        
        
                                        
        Scanner sler = new Scanner (System.in); 
        Contato novo = new Contato();
        System.out.printf("Digite o id:");
        id = sler.next();
        System.out.printf("Digite o nome:");
        nome = sler.next();
        System.out.printf("Digite o email:");
        email = sler.next();
        System.out.printf("Digite o endereco:");
        end = sler.next();
        System.out.printf("Digite o complemento:");
        comp = sler.next();
        System.out.printf("Digite o cep:");
        cep = sler.next();
        System.out.printf("Digite a cidade:");
        cid = sler.next();
        System.out.printf("Digite o estado:");
        est = sler.next();
        
 
        int numCep = Integer.parseInt(cep);
        int numId = Integer.parseInt(id);
        novo.setId(numId);
        novo.setNome(nome);
        novo.setEmail(email);
        novo.setEndereco(end);
        novo.setComplemento(comp);
        novo.setCep(numCep);
        novo.setCidade(cid);
        novo.setEstado(est);
        
     
         try ( // Cria um ServerSocket que vai se conectar com sockets
                    
                ServerSocket servidor = new ServerSocket(1236)) {
                System.out.println("Aguardando conexÃ£o");
                // Cria um Socket que Ã© definido como a abertura de conexÃ£o com o Server
                Socket cliente = servidor.accept();
                //TratamentoClass tratamento = new TratamentoClass(cliente);
                    // cria a thread em cima deste objeto
                // Thread t = new Thread(tratamento);
                // inicia a thread
              //  t.start();
                System.out.println("Conectou");
                //Escreve a casca da saida, Ã© aqui que vocÃª define onde o que sera enviado vai ficar
                ObjectOutputStream sai = new ObjectOutputStream(cliente.getOutputStream());
                // String que vai ser colocada na casca
                
                // Envio da string encapsulada
                sai.writeObject(novo);
                System.out.println("Enviou");
                // Limpa o object
                sai.flush();
                sai.close();
                }
            }

    }
