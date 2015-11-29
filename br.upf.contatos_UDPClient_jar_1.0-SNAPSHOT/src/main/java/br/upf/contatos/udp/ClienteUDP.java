/**
 *
 * @author Gehrke
 *
 * @author Barizon
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.upf.contatos.udp;

import br.upf.contatos.msg.model.ContatoBean;
import java.util.Scanner;
import br.upf.contatos.msg.Request;
import br.upf.contatos.msg.RequestImpl;
import br.upf.contatos.msg.Response;
import br.upf.contatos.msg.model.ContatoBean;
import br.upf.contatos.msg.model.Status;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import br.upf.contatos.msg.model.ContatoBean;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;
import org.json.JSONObject;

public class ClienteUDP {
    
    private static final int PORTA = 2010;
    private static final String HOST = "localhost";
    public static void main(String []args) throws RuntimeException, IOException {
        comando(args);
   }
    
    public static boolean comando (String[] args) throws SocketException, IOException{
        
            while (true) {
                Scanner scanner = new Scanner(System.in);   
                String line = scanner.nextLine();
                String[] comandos = line.split(" ");
                if (comandos[0].equals("help")) {    
                System.out.println("Listar = Lista todos os contatos"
                    + "incluir = Inclui um contato, incluir nome NOMEtudoJUNTO email EMAIL end ENDERECO comp COPM cep CEP cid CID est EST \n"
                    + "editar  = edita um contato, escrever editar numeroDoID label VALORaSERalterado\n"
                    + "deletar = deletar id numero \n"
                    + "parar   = finaliza conexao \n"
                    + "cidade  = lista por cidade escrever cidade nomeCidade\n"
                    + "Comandos seguidos de outras informacoes sempre separar por espaco, nomes tudo junto\n");
                        
                return true;
                } else if (comandos[0].equals("parar")) {       
                return false;
                } else {
                    UDPConexao udpConnection = new UDPConexao(HOST, PORTA);
                    udpConnection.connect();
                    switch(comandos[0]) {     
                        case "listar": { 
                            for(ContatoBean cb: udpConnection.getAll()) {
                            System.out.println(new JSONObject(cb));
                            }
                            udpConnection.disconnect();

                            break;
                        }// TERMINA LISTAR
                        case "incluir": {
                            ContatoBean cb = new ContatoBean();
                            if(comandos[1].equals("label=nome"))
                                cb.setNome(comandos[2]);
                            if(comandos[3].equals("label=email"))
                                cb.setEmail(comandos[4]);
                            if(comandos[5].equals("label=end"))
                                cb.setEndereco(comandos[6]);
                            if(comandos[7].equals("label=comp"))
                                cb.setComplemento(comandos[8]);
                            if(comandos[9].equals("label=cep"))
                                cb.setCep(Integer.parseInt(comandos[10]));
                            if(comandos[11].equals("label=cid"))
                                cb.setCidade(comandos[12]);
                            if(comandos[13].equals("label=est"))
                                cb.setEstado(comandos[14]);
                            try {
                                    cb = udpConnection.insert(cb);
                                } catch(RuntimeException e) {
                                    System.out.println(e.getMessage());
                                }
                                udpConnection.disconnect();
                                break;
                        }//TERMINA INCLUIR
                        
                        case "editar":{
                            
                            int numIdEdi = Integer.parseInt(args[1]);
                            ContatoBean contact = new ContatoBean();
                            contact = udpConnection.getById(numIdEdi);

                            switch(args[2]) {        
                                case "id":
                                    contact.setId(Integer.parseInt(args[3]));
                                    break;
                                case "nome":
                                    contact.setNome(args[3]);
                                    break;
                                case "email":
                                    contact.setEmail(args[3]);
                                    break;
                                case "end":
                                    contact.setEndereco(args[3]);
                                    break;
                                case "comp":
                                    contact.setComplemento(args[3]);
                                    break;
                                case "cep":
                                    contact.setCep(Integer.parseInt(args[3]));
                                    break;
                                case "cid":
                                    contact.setCidade(args[3]);
                                    break;
                                case "est":
                                    contact.setEstado(args[3]);
                                    break;

                            }// FINALIZA CASE DE TROCA DE DADOS

                            try {
                                contact = udpConnection.update(contact);
                                System.out.println(new JSONObject(contact));
                            }catch(RuntimeException e) {
                                System.out.println(e.getMessage());
                            }
                            udpConnection.disconnect();
                        }// TERMINA EDITAR 
                        
                        case "cidade":{
                            ContatoBean cob = new ContatoBean();
                            cob.setCidade(args[1]);
                            try {
                            cob = udpConnection.getByCidade(cob);
                            System.out.println(new JSONObject(cob));
                            } catch(RuntimeException e) {
                            System.out.println(e.getMessage());
                            }

                            udpConnection.disconnect();
                        break;
                        }//termina listar CIDADE
                        
                        case "deletar":{
                            ContatoBean cb = new ContatoBean();
                            cb.setId(Integer.parseInt(args[1]));
                            try {
                                cb = udpConnection.delete(cb.getId());
                                System.out.println(new JSONObject(cb));
                            } catch(RuntimeException e) {
                                System.out.println(e.getMessage());
                            }

                            udpConnection.disconnect();
                            break;
                        }//termina DELETAR
                        
                    }//TERMINA SWTICH
                }// TERMINA ELSE ANTES DO SWITCH    


        }//TERMINA WILHER TRUE
    }//TERMINA BOOLERAN MAIN
}//TERMINA CLASSE
