/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Matheus Sobucki
 */
public class Json {
    
    public static void main(String[] args) throws IOException, ParseException {
    
        JSONParser parser = new JSONParser();
        
        JSONArray a = (JSONArray) parser.parse(new FileReader("saida.json"));

        for (Object o : a)
            {
              JSONObject object = (JSONObject) o;

              String name = (String) object.get("name");
              System.out.println(name);

              String city = (String) object.get("city");
              System.out.println(city);

              String job = (String) object.get("job");
              System.out.println(job);
               
              
              JSONArray cars = (JSONArray) object.get("cars");

                             for (Object c : cars)
                             {
                               System.out.println(c+"");
                             }
             
            }
    }
}
