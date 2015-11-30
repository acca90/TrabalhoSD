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
        Contato dal = new Contato();
        
        
        JsonObject json = Json.createReader(new StringReader(s)).readObject();
        contato c = new contato();      
        
        // adicionar
        if(json.getInt("operacao") == 1){
            dal.setId(json.getInt("codigo"));
            dal.setNome(json.getString("nome"));
            dal.setCep(json.getInt("cep"));
            dal.setCidade(json.getString("cidade"));
            dal.setComplemento(json.getString("complemento"));
            dal.setEmail(json.getString("email"));
            dal.setEmailAlternativo(json.getString("email_alter"));
            dal.setEstado(json.getString("estado"));
            dal.setEndereco(json.getString("endereco"));
            c.setOperacao(json.getInt("operacao"));
            c.setContato(dal);
        }
        
        //busca cidade
        if(json.getInt("operacao") == 2){
            c.setOperacao(2);
            c.setMsg(json.getString("busca_cidade"));
        }
        
        //busca id
        if(json.getInt("operacao") == 3){
            c.setOperacao(3);
            c.setMsg(json.getString("busca_ID"));
        }
        
        if(json.getInt("operacao") == 4){
            c.setOperacao(4);
           
        }
        
        if(json.getInt("operacao") == 6){
            c.setOperacao(6);
            c.setMsg(json.getString("codigo"));
            
        }
        
        return c;
    }

    @Override
    public boolean willDecode(String s) {
         try {
            // Check if incoming message is valid JSON
            Json.createReader(new StringReader(s)).readObject();
            return true;
          } catch (Exception e) {
            return false;
          }
    }

    @Override
    public void init(EndpointConfig config) {
        
    }

    @Override
    public void destroy() {
    
    }
    
}
