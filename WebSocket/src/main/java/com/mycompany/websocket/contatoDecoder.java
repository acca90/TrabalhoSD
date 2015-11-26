/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.websocket;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.swing.JOptionPane;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author joao
 */
public class contatoDecoder implements Decoder.Text<contato> {

    @Override
    public contato decode(String s) throws DecodeException {
        
        JsonObject json = Json.createReader(new StringReader(s)).readObject();
        System.out.println(json.getInt("codigo"));
        contato c = new contato();        
        c.setId(json.getInt("codigo"));
        c.setNome(json.getString("nome"));
        
        return c;
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig config) {
        
    }

    @Override
    public void destroy() {
    
    }
    
}
