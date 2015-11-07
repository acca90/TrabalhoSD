package matheushernandes.contatos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class Formulario extends Activity {

    private int codigo;
    private EditText enome, eemail, eemail_alter, eendereco, ecomp, ecep, ecidade, eestado;


    public void Confirmar (View view) throws Exception {
        try {
            if (Lista.Contatos.getOP().equals("1")) {
                Lista.Contatos.setOP(2);

                String json = "{\"nome\":\"\"" + enome.getText().toString() + "\"\","
                            + "\"email\":\"\"" + eemail.getText().toString() + "\"\","
                            + "\"email_alter\":\"\"" + eemail_alter.getText().toString() + "\"\","
                            + "\"endereco\":\"\"" + eendereco.getText().toString() + "\"\","
                            + "\"complemento\":\"\"" + ecomp.getText().toString() + "\"\","
                            + "\"cep\":\"\"" + ecep.getText().toString() + "\"\","
                            + "\"cidade\":\"\"" + ecidade.getText().toString() + "\"\","
                            + "\"estado\":\" \"" + eestado.getText().toString() + "\"\"}";

                Lista.Contatos.setContatoJson(json);

            } else {
                Lista.Contatos.setOP(3);

                String json = "{\"codigo\":\"" + String.valueOf(codigo) + "\","
                        + "\"nome\":\"\"" + enome.getText().toString() + "\"\","
                        + "\"email\":\"\"" + eemail.getText().toString() + "\"\","
                        + "\"email_alter\":\"\"" + eemail_alter.getText().toString() + "\"\","
                        + "\"endereco\":\"\"" + eendereco.getText().toString() + "\"\","
                        + "\"complemento\":\"\"" + ecomp.getText().toString() + "\"\","
                        + "\"cep\":\"\"" + ecep.getText().toString() + "\"\","
                        + "\"cidade\":\"\"" + ecidade.getText().toString() + "\"\","
                        + "\"estado\":\" \"" + eestado.getText().toString() + "\"\"}";

                Lista.Contatos.setContatoJson(json);
            }
            Lista.Contatos.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Toast.makeText(getApplicationContext(),Lista.Contatos.getResponse(),Toast.LENGTH_SHORT).show();

        finish();
    }


    public void Excluir(View view) throws Exception {
        Lista.Contatos.setOP(4);
        Lista.Contatos.execute().get();
        Toast.makeText(getApplicationContext(), Lista.Contatos.getResponse(), Toast.LENGTH_SHORT).show();
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        this.enome = (EditText)findViewById(R.id.edit_nome);
        this.eemail  = (EditText)findViewById(R.id.edit_email);
        this.eemail_alter = (EditText)findViewById(R.id.edit_email_alter);
        this.eendereco = (EditText)findViewById(R.id.edit_endereco);
        this.ecomp = (EditText)findViewById(R.id.edit_comp);
        this.ecep = (EditText)findViewById(R.id.edit_cep);
        this.ecidade = (EditText)findViewById(R.id.edit_cidade);
        this.eestado = (EditText)findViewById(R.id.edit_estado);

        if (Lista.Contatos.getOP().equals("1")) {
            Button exclui = (Button)findViewById(R.id.excluir);
            exclui.setVisibility(View.GONE);
        } else {

            try {
                this.enome.setText(Lista.contato.getString("nome").toString());
                this.eemail.setText(Lista.contato.getString("email").toString());
                this.eemail_alter.setText(Lista.contato.getString("email_alter").toString());
                this.eendereco.setText(Lista.contato.getString("endereco").toString());
                this.ecomp.setText(Lista.contato.getString("complemento").toString());
                this.ecep.setText(Lista.contato.getString("cep").toString());
                this.ecidade.setText(Lista.contato.getString("cidade").toString());
                this.eestado.setText(Lista.contato.getString("estado").toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
