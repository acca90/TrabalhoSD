/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.websocket;

import br.upf.contatos.dal.model.Contato;
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
public class contatoDecoder implements Decoder.Text<Contato> {

    @Override
    public Contato decode(String s) throws DecodeException {
        
        JsonObject json = Json.createReader(new StringReader(s)).readObject();
        System.out.println(json.getInt("codigo"));
        Contato c = new Contato();        
        c.setId(json.getInt("codigo"));
        c.setNome(json.getString("nome"));
        c.setCep(json.getInt("cep"));
        c.setCidade(json.getString("cidade"));
        c.setComplemento(json.getString("complemento"));
        c.setEmail(json.getString("email"));
        c.setEmailAlternativo(json.getString("email_alter"));
        c.setEstado(json.getString("estado"));
        
        
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
