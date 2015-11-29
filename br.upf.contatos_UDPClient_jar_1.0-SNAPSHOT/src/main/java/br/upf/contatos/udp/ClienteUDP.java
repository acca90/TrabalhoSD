
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

/**
 *
 * @author Gehrke
 */


public class ClienteUDP {
    private static final int PORTA = 2010;
    private static final String HOST = "localhost";
    
    
    public static void main(String []args) throws RuntimeException{
            
        while (true) {
        System.out.println("Digite help, para o menu");
        Scanner scanner = new Scanner(System.in);   
        String line = scanner.nextLine();
        comando(line);
   }   
        
    } 
  public static boolean comando(String line){
        return false;

         
}
  


       
    

  
    
    
}