/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.tcp;

import br.upf.contatos.msg.model.ContatoBean;
import org.json.JSONObject;

/**
 *
 * @author kaeff
 */
public class Client {
    private static final int PORTA = 2005;
    private static final String HOST = "localhost";
    public static void main(String []args) throws RuntimeException {
        
        ClientConnector tcpService = new ClientConnector(HOST, PORTA);
        tcpService.connect();
        System.out.println("Cliente conectado");
        System.out.println("GetAll");
        
        
        for(ContatoBean cb: tcpService.getAll()) {
            System.out.println(new JSONObject(cb));
        }
        
        ContatoBean c = new ContatoBean(1, "Mariazinha", "mariazinha@inbox.com", "Rua 7 de setembro, 2195", null, 88119210, "Passo Fundo", "RS", null);
        
        System.out.println("Inserindo contato #" + c.getId());
        
        try {
            c = tcpService.insert(c);
            System.out.println(new JSONObject(c));
        } catch(RuntimeException e) {
            System.out.println(e.getMessage());
        }
        
        tcpService.disconnect();
    }
    
}
