/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.rest.integracao.webservicerestconect;

import br.upf.contatos.rest.integracao.modelo.Contato;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author jonas
 */
public class MediadorRest {
    public List<Contato> getContatos(){
        List<Contato> listaContatos = new ArrayList<>();
        Gson jsonContatos = new Gson();
        try {
            HttpURLConnection con = (HttpURLConnection) (new URL("http://localhost:8080/wsrest")).openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            if (con.getResponseCode() != 200) {
                return null;
            } else {
                BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
                StringBuilder resultado = new StringBuilder();
                String line;

                while ((line = br.readLine()) != null) {
                    resultado.append(line);
                }

                Type collectionType = new TypeToken<List<Contato>>(){}.getType();
                listaContatos = jsonContatos.fromJson(resultado.toString(), collectionType);
            }
        }catch(IOException | JsonSyntaxException e){
        }
        return listaContatos;
    }
    
    public List<Contato> putContatos(List<Contato> contatos){
        try {
            for (Contato contato : contatos) {
                HttpURLConnection con = (HttpURLConnection) (new URL("http://localhost:8080/wsrest")).openConnection();
                con.setDoOutput(true);
                con.setRequestMethod("PUT");
                con.setRequestProperty("Content-Type", "application/json");

                OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
                out.write(new Gson().toJson(contato));
                out.flush();
                out.close();
            }
        }catch(Exception e){
        }
        return getContatos();
    }
}
