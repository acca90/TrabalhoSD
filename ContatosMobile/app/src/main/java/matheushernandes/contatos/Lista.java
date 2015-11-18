package matheushernandes.contatos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Lista extends Activity {

    public static Activity a;
    public Lista() {
        a = this;
    }
    public static RestConnect Contatos;
    public static JSONArray DATA;
    public static String url;
    public static JSONObject contato;
    public static int op;

    public static void setUrl(String urln) {
        url = urln;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        url = "http://192.168.0.102/wsRest/index.php/contato";

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contatos, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        //Do your work here in ActivityA

        try {
            this.Atualiza();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void listar(View view) {
        try {
            this.Atualiza();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Atualiza() throws Exception {

        Contatos = new RestConnect();
        Contatos.setUrl(url);

        /* LÊ O CAMPO DA CIDADE */
        EditText edit = (EditText)findViewById(R.id.cidade);
        Contatos.setCidade(edit.getText().toString());
        Contatos.setOP(1);

        try {
            Contatos.setResponse(Contatos.execute().get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!Contatos.getResponse().equals("{\"codigo\":0}")) {


            JSONObject resposta = new JSONObject(Contatos.getResponse());

            String conta = resposta.getString("contatos");

            //Toast.makeText(getApplicationContext(),conta,Toast.LENGTH_LONG).show();
            // QUEBRA JSON
            JSONArray ContatosArray = new JSONArray(conta);


            final ArrayList<String> list = new ArrayList<String>();

            if (ContatosArray.length() > 0) {

                DATA = new JSONArray(conta);

                for (int i = 0; i < ContatosArray.length(); i++) {
                    JSONObject nodo = ContatosArray.getJSONObject(i);
                    list.add(nodo.getString("nome"));
                }

                // ADICIONA PARA A LISTA
                ListView lista = (ListView) findViewById(R.id.lista);

                final StableArrayAdapter adapter =
                        new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);

                lista.setAdapter(adapter);

                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, final View view,
                                            int position, long id) {
                        try {
                            setOP(2);
                            contato = DATA.getJSONObject(position);
                            Intent i = new Intent(getApplicationContext(), Formulario.class);
                            startActivityForResult(i, 0);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } else {

            final ArrayList<String> list = new ArrayList<String>();

            list.add("Nada encontrado");

            // ADICIONA PARA A LISTA
            ListView lista = (ListView) findViewById(R.id.lista);
            final StableArrayAdapter adapter =
                    new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);

            lista.setAdapter(adapter);
            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, final View view,
                                        int position, long id) {

                }
            });
        }
        Contatos = null;
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.novo:
                setOP(1);
                Intent i = new Intent(getApplicationContext(), Formulario.class);
                startActivityForResult(i,0);
                break;
            case R.id.conf:
                Intent y = new Intent(getApplicationContext(), Configuracoes.class);
                startActivity(y);
                break;
            default:
                return super.onOptionsItemSelected(item);

        }
        return true;
    }


    public static int getOP() {
        return op;
    }

    public static void setOP(int nop) {
        op = nop;
    }

}