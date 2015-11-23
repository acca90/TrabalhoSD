/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author kaeff
 */
public class Receiver implements Runnable {
    private final DatagramSocket socket;
    private final BlockingQueue<DatagramPacket> fila;

    public Receiver(DatagramSocket socket, BlockingQueue fila) {
        this.socket = socket;
        this.fila = fila;
    }
    
    @Override
    public void run() {
        byte[] buffer = new byte[8192];
        for(;;) {
            DatagramPacket pct = new DatagramPacket(buffer, buffer.length);
            
            try {
                socket.receive(pct);
                fila.put(pct);
            } catch (IOException ex) {
                System.out.println("Ocorreu um erro ao receber o pacote!");
            } catch (InterruptedException ex) {
                System.out.println("Ocorreu uma interrupção enquanto a thread aguardava o atendimento da fila!");
            }
        }
    }

}
