/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.websocket;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import org.json.simple.JSONObject;

/**
 *
 * @author joao
 */
class MenssagemEncoders implements Encoder.TextStream<contato> {
    
    @Override
    public void encode(contato msg, Writer writer) throws EncodeException, IOException {        
        JsonObject json = Json.createObjectBuilder()
                .add("action", "add")
                .add("nome", msg.getContato().getNome() )
                .add("codigo", msg.getContato().getId())
                .build();
        try(JsonWriter jsonwrite = Json.createWriter(writer)){
            jsonwrite.writeObject(json);
        }
    }

    @Override
    public void init(EndpointConfig config) {
        
    }

    @Override
    public void destroy() {
        
    }
   
   
    
}
