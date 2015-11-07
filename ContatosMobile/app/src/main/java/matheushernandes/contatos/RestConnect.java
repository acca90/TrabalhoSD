package matheushernandes.contatos;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by virgilio on 28/10/15.
 */
public class RestConnect extends AsyncTask<String, String, String> {

    private String ContatosJson;
    private String response;
    private String Cidade;
    private static int op = 1;
    private String url;

    public void setUrl(String urln) {
        this.url = urln;
    }

    @Override
    protected String doInBackground(String... params) {
        String retorno = "{'response':'null'}";
        try {
            switch(op) {
                case 1:
                    retorno = this.Listar();
                    break;
                case 2:
                    retorno = this.novo();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setResponse(retorno);
        setContatosJson(retorno);

        return retorno;
    }

    private String novo() {

        return "OII";
    }

    @Override
    protected void onPostExecute(String result) {
        this.setResponse(result);
    }



    public String Listar() throws Exception {

        //StrictMode.ThreadPolicy tp = StrictMode.ThreadPolicy.LAX;
        //StrictMode.setThreadPolicy(tp);

        if (!this.Cidade.equals("")) {
            this.url += "/" + this.Cidade;
        }

        URL obj = new URL(this.url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.addRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.connect();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }





    public static void setOP(int novoOP) { op = novoOP; }
    public String getContatosJson() { return this.ContatosJson;  }
    public void setContatosJson(String contatosJson) { this.ContatosJson = contatosJson; }
    public String getResponse() {
        return response;
    }
    public void setResponse(String response) {
        this.response = response;
    }
    public void setCidade(String cidade) { this.Cidade = cidade; }

    public String getOP() {
        return String.valueOf(op);
    }
}


