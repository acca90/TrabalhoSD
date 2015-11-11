/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Matheus Sobucki
 */
public class Cliente {
    
     public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
        // Cria o Socket que vai Se conectar com um ServerSocket (Servidor)    
        Socket cliente = new Socket("localhost", 1236);
        // Aguarda resposta do Servidor e joga no object quando receber algo
        ObjectInputStream vem = new ObjectInputStream(cliente.getInputStream());
        // se recebeu, guarda a resposta na string
        String msg = (String)vem.readObject();
        // printa a resposta
        System.out.println("Recebido: " + msg);
   }
    
}
