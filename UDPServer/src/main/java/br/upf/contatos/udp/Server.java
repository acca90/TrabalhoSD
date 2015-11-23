/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author kaeff
 */
public class Server {
    private static final int PORTA = 2010;
    private static final int CAPACIDADE = 10;
    
    public static void main(String args[]) {
        iniciar();
    }
    
    private static void iniciar() {
        BlockingQueue<DatagramPacket> fila = new ArrayBlockingQueue(CAPACIDADE);
        try {
            DatagramSocket socket = new DatagramSocket(PORTA);
            System.out.println("Servidor UDP (" + PORTA + "): aguardando requisições...");
            Thread produtor = new Thread(new Receiver(socket, fila));
            Thread consumidor = new Thread(new RequestProcessor(socket, fila));
            produtor.start();
            consumidor.start();
            
        } catch (SocketException ex) {
            System.out.println("Não foi possível estabelecer o servidor na porta " + PORTA + "!");
            System.exit(1);
        }
    }
}
