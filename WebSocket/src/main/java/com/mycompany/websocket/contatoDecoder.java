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
public class contatoDecoder implements Decoder.Text<contato> {

    @Override
    public contato decode(String s) throws DecodeException {
        
        System.out.println(s);
        
        JsonObject json = Json.createReader(new StringReader(s)).readObject();
        contato c = new contato();      
        
        if(json.getInt("operacao") == 1){
            System.out.println(json.getInt("codigo"));
              
            c.contato.setId(json.getInt("codigo"));
            c.contato.setNome(json.getString("nome"));
            c.contato.setCep(json.getInt("cep"));
            c.contato.setCidade(json.getString("cidade"));
            c.contato.setComplemento(json.getString("complemento"));
            c.contato.setEmail(json.getString("email"));
            c.contato.setEmailAlternativo(json.getString("email_alter"));
            c.contato.setEstado(json.getString("estado"));
        }
        
        if(json.getInt("operacao") == 2){
            return c;
        }
        
        
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

    @Override
    public contato decode(String s) throws DecodeException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public contato decode(String s) throws DecodeException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
