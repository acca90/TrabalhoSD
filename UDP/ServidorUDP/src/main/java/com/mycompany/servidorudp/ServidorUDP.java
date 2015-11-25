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
import java.util.LinkedList;
import java.util.Queue;
import EnviaRecebe.Receptor;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
/**
 *
 * @author Usuario
 */
public class ServidorUDP {
    
    public static Queue<String>  mensagensQueue;
   
    
    
    public static void main (String[] args) throws SocketException, IOException{
        BlockingQueue bq = new ArrayBlockingQueue(50);
        
        
        
//            mensagensQueue =  new LinkedList<String>();
            
            //starta a thread
            
            
            // Define o host, é importante saber porque se n for localhost é obrigatorio usar
            InetAddress host = InetAddress.getByName("localhost");  
            // Cria uma String que vai ser enviada
            String resp = "recebi, valeu falou !";
            // cria o Vetor que vai receber as informações
            byte[] pacote = new byte[1024];
            
            // Cria um Socket UDP
            DatagramSocket socket = new DatagramSocket(2010);
            // Cria pacote que vai guardar as informações no vetor de bytes
            DatagramPacket pc = new DatagramPacket(pacote, pacote.length, host, 2010);
            // Aguarda até receber um pacote
            socket.receive(pc);
            // Log pra saber se enviou
            System.out.println("Recebeu");
            // quando receber ele armazena na string
            
            
            bq.add(pc);
            
            bq.remove(bq.);
            // CRIAR THREAD PARA INSERIR NA LISTA(QUEUE) mensagensQueue
            
            
            
            // CRIAR THREAD PARA TIRAR DA FILA E EXECUTAR
            
            String resposta = new String(pacote);
            // Printa na tela
            System.out.println(resposta);
            //seta o retorno no pacote
            pc.setData(resp.getBytes());
            // Envia o pacote pelo socket
            socket.send(pc);
            // Log pra saber se enviou
            System.out.println("Enviou");
        }
    
}
