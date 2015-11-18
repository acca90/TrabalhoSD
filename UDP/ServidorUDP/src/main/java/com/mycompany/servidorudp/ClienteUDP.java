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

/**
 *
 * @author Usuario
 */
public class ClienteUDP {
    
    public static void main (String[] args) throws SocketException, IOException{
            // Define o host, é importante saber porque se n for localhost é obrigatorio usar
            InetAddress host = InetAddress.getByName("localhost");
            // Cria uma String que vai ser enviada
            String nome = "Felipe Lanius";
            // Cria um Socket UDP, não precisa de porta porque é o cliente
            DatagramSocket socket = new DatagramSocket();
            // Cria um Pacote UDP passando a String em Bytes e a porta
            // getBytes é para transformar em vetor de bytes e poder ser transportado
            // host que vai ser enviado           
            // .length é para pegar o tamanho do vetor
            DatagramPacket pc = new DatagramPacket(nome.getBytes(), nome.getBytes().length, host, 2010);
            // Envia o pacote pelo socket
            socket.send(pc);
            // Aguarda até receber um pacote
            // quando receber ele armazena na string
            String resposta = new String(pc.getData());
            // Printa na tela
            System.out.println(resposta);
        }
    
}
