/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.servidorudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author Usuario
 */
public class Thread {
    
    public class Client extends Thread{

 @override
 public void run() throws UnknownHostException, SocketException, IOException{
      // Define o host, é importante saber porque se n for localhost é obrigatorio usar
            InetAddress host = InetAddress.getByName("localhost");  
            // Cria uma String que vai ser enviada
            String resp = "recebi, valeu falou !";
            // cria o Vetor que vai receber as informações
            byte[] pacote = new byte[1024];
            // Cria um Socket UDP
            DatagramSocket socket = new DatagramSocket(2010);
            // Cria pacote que vai guardar as informações no vetor de bytes
            DatagramPacket pc = new DatagramPacket(pacote, pacote.length, host, 1234);
            // Aguarda até receber um pacote
            socket.receive(pc);
            // Log pra saber se enviou
            System.out.println("LOGGER: Recebeu");
            // quando receber ele armazena na string
            String resposta = new String(pacote);
            // Printa na tela
            System.out.println(resposta);
            //seta o retorno no pacote
            pc.setData(resp.getBytes());
            // Envia o pacote pelo socket
            socket.send(pc);
            // Log pra saber se enviou
            System.out.println("LOGGER: Enviou");
 }
    }
}