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

        URL url = new URL(this.url);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();

        con.setDoOutput(true);
        con.setRequestMethod("POST");

        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.addRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.connect();

        String params = this.contatoJson;
        con.getOutputStream().write(params.getBytes());
        con.getOutputStream().flush();
        con.getOutputStream().close();
        String code = String.valueOf(con.getResponseCode());

        if (code.equals("200")) {
            String retorno = "";
            BufferedReader buffer = new BufferedReader(new InputStreamReader((con.getInputStream()), "UTF-8"));
            String r = "";
            while ((r = buffer.readLine()) != null) {
                retorno = r;
            }
            return retorno;

        } else {
            return "{\"code\":\""+code+"\"}";
        }
    }

    private String Editar() throws Exception {

        URL url = new URL(this.url);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();

        con.setDoOutput(true);
        con.setRequestMethod("PUT");

        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.addRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.connect();

        String params = this.contatoJson;
        con.getOutputStream().write(params.getBytes());
        con.getOutputStream().flush();
        con.getOutputStream().close();
        String code = String.valueOf(con.getResponseCode());

        if (code.equals("200")) {
            String retorno = "";
            BufferedReader buffer = new BufferedReader(new InputStreamReader((con.getInputStream()), "UTF-8"));
            String r = "";
            while ((r = buffer.readLine()) != null) {
                retorno = r;
            }
            return retorno;

        } else {
            return "{\"code\":\""+code+"\"}";
        }

    }



    private String Excluir() throws Exception {

        URL url = new URL(this.url);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();

        con.setDoOutput(true);
        con.setRequestMethod("DELETE");

        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.addRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.connect();

        String params = this.contatoJson;
        con.getOutputStream().write(params.getBytes());
        con.getOutputStream().flush();
        con.getOutputStream().close();
        String code = String.valueOf(con.getResponseCode());

        if (code.equals("200")) {
            String retorno = "";
            BufferedReader buffer = new BufferedReader(new InputStreamReader((con.getInputStream()), "UTF-8"));
            String r = "";
            while ((r = buffer.readLine()) != null) {
                retorno = r;
            }
            return retorno;

        } else {
            return "{\"code\":\""+code+"\"}";
        }

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


