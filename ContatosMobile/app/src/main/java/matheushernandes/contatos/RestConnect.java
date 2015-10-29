package matheushernandes.contatos;


import android.os.AsyncTask;
import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by virgilio on 28/10/15.
 */
public class RestConnect extends AsyncTask<String, Void, String> {


    @Override
    protected String doInBackground(String... params) {

        String lista  = "aaaa";

        try {
            lista = this.listar();
        } catch (Exception e) {
            lista = "fuck";
            e.printStackTrace();
        }

        return lista;
    }


    public String listar() throws Exception {

        StrictMode.ThreadPolicy tp = StrictMode.ThreadPolicy.LAX;
        StrictMode.setThreadPolicy(tp);

        String retorno = "AAA";

        String url = "http://172.30.147.71/wsRest/index.php/contato";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.addRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        //con.setInstanceFollowRedirects(false);
        con.connect();

     //


        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        retorno = response.toString();

        //print result
        return retorno;
        //System.out.println(response.toString());

    }

}


