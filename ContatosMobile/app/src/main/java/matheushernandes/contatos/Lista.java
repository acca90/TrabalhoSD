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
import java.util.ArrayList;

public class Lista extends Activity {

    public static Activity a;
    public Lista() {
        a = this;
    }
    public static RestConnect Contatos;
    public static JSONArray DATA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        Contatos = new RestConnect();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contatos, menu);
        return true;
    }




    public void listar(View view) throws JSONException {

        /* LÊ O CAMPO DA CIDADE */
        EditText edit = (EditText)findViewById(R.id.cidade);
        Contatos.setCidade(edit.getText().toString());
        Contatos.setOP(1);

        try {
            Contatos.setResponse(Contatos.execute().get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // QUEBRA JSON
        JSONArray ContatosArray = new JSONArray(Contatos.getResponse());
        final ArrayList<String> list = new ArrayList<String>();

        if (ContatosArray.length() > 0) {

            DATA = new JSONArray(Contatos.getResponse());

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
                     JSONObject contato = DATA.getJSONObject(position);
                     Toast.makeText(getApplicationContext(), contato.getString("codigo"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        Toast.makeText(getApplicationContext(), " aaa", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.novo:
                Contatos.setOP(1);
                Intent i = new Intent(getApplicationContext(), Formulario.class);
                startActivity(i);
                break;
            case R.id.conf:
                Toast.makeText(getApplicationContext(),"eee",Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onOptionsItemSelected(item);

        }
        return true;
    }


}