/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.servidorudp;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcio
 */
public class ThreadOB extends Thread{
    
    private Socket socket;
    
    @Override
        public void run(){
            try { 
                Socket cliente = socket;
                System.out.println("Conectou");
                ObjectOutputStream sai = new ObjectOutputStream(cliente.getOutputStream());
                String hell = "Conectado ao Servidor no IP:" + cliente.getInetAddress();
                sai.writeObject(hell);
                sai.flush();
                cliente.close();
            } catch (IOException ex) {
                Logger.getLogger(ThreadOB.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }  
           
    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    
}