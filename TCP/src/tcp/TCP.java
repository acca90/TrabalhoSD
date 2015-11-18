/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp;

import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Matheus Sobucki
 */
public class TCP {

     public static void main(String[] args) throws IOException, ParseException {
         
          JSONParser parser = new JSONParser();
        
        JSONArray a = (JSONArray) parser.parse(new FileReader("saida.json"));

        for (Object o : a)
            {
              JSONObject object = (JSONObject) o;

              String name = (String) object.get("name");
             

              String city = (String) object.get("city");
              

              String job = (String) object.get("job");
             
               
              
              JSONArray cars = (JSONArray) object.get("cars");

                             
            
         
            try ( // Cria um ServerSocket que vai se conectar com sockets
                    
                ServerSocket servidor = new ServerSocket(1236)) {
                System.out.println("Aguardando conexão");
                // Cria um Socket que é definido como a abertura de conexão com o Server
                Socket cliente = servidor.accept();
                System.out.println("Conectou");
                //Escreve a casca da saida, é aqui que você define onde o que sera enviado vai ficar
                ObjectOutputStream sai = new ObjectOutputStream(cliente.getOutputStream());
                // String que vai ser colocada na casca
                String info = ("name: "+ name +"  city: " + city + "  job: "  + job  +"  cars: " + cars);
                // Envio da string encapsulada
                sai.writeObject(info);
                // Limpa o object
                sai.flush();
            }
            }
}
    
}
