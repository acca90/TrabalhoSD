/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mauricley
 */
public class Server {
    private final int PORTA;
    private final int FILA;
    private final int TIMEOUT;
    private final Logger logger;
    
    public Server(int porta, int fila, int timeout) {
        this.PORTA = porta;
        this.FILA = fila;
        this.TIMEOUT = timeout;
        logger = Logger.getLogger(Server.class.getName());
    }
    
    public void iniciar() {
        try {
            ServerSocket socket = new ServerSocket(PORTA, FILA);
            for(;;) {
                logger.log(Level.INFO, "Servidor (localhost:" + PORTA + "): aguardando conexão...");
                Socket conn = socket.accept();
                conn.setSoTimeout(TIMEOUT);
                new Thread(new ServerHandler(conn)).start();
            }
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Porta " + PORTA + " já está sendo utilizada!", ex);
            System.exit(0);
        }
    }
    
    public static void main(String args[]) {
        new Server(2006, 10, 300000).iniciar();
    }
}
