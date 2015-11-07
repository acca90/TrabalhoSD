package matheushernandes.contatos;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by virgilio on 28/10/15.
 */
public class RestConnect extends AsyncTask<String, String, String> {

    private String ContatosJson;
    private String response;
    private String Cidade;
    private static int op = 1;
    private String url;
    private String contatoJson;

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
                    retorno = this.Novo();
                    break;
                case 3:
                    retorno = this.Editar();
                    break;
                case 4:
                    retorno = this.Excluir();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setResponse(retorno);
        setContatosJson(retorno);

        return retorno;
    }

    private String Novo() throws Exception{

        URL obj = new URL(this.url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setReadTimeout(10000);
        con.setConnectTimeout(15000);
        con.setRequestMethod("POST");
        con.setDoInput(true);
        con.setDoOutput(true);


        OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
        writer.write(this.contatoJson);
        writer.flush();

        con.connect();


        BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = rd.readLine()) != null) {
            response.append(inputLine);
        }
        rd.close();
        writer.close();

        return response.toString();

        //return this.url;
    }

    private String Editar() {
        return this.contatoJson;
    }

    private String Excluir() {
        return "Excluir";
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

    public void setContatoJson(String contatoJson) {
        this.contatoJson = contatoJson;
    }
}


