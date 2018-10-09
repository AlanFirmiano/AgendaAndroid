package br.ufc.quixada.dadm.variastelas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import br.ufc.quixada.dadm.variastelas.transactions.Constants;

public class ContactActivity extends AppCompatActivity {

    EditText edtNome;
    EditText edtTel;
    EditText edtEnd;

    boolean edit;
    Integer idContatoEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        //Passando informacao da primeira tela para a segunda
        //String nome = ( String )getIntent().getExtras().get( "nome" );
        //String sobrenome = ( String )getIntent().getExtras().get( "sobrenome" );
        //Log.d( "ContactActivity", nome );
        //Log.d( "ContactActivity", sobrenome );

        edtNome = findViewById( R.id.editTextNome );
        edtTel = findViewById( R.id.editTextTel );
        edtEnd = findViewById( R.id.editTextEnd );

        edit = false;

        if( getIntent().getExtras() != null ){

            String nome = ( String )getIntent().getExtras().get( "nome" );
            String telefone = ( String )getIntent().getExtras().get( "telefone" );
            String endereco = ( String )getIntent().getExtras().get( "endereco" );
            idContatoEditar = (int)getIntent().getExtras().get( "id" );

            edtNome.setText( nome );
            edtTel.setText( telefone );
            edtEnd.setText( endereco );

            edit = true;

        }

    }

    public void cancelar( View view ){
        setResult( Constants.RESULT_CANCEL );
        finish();
    }

    public void adicionar( View view ){
        JSONObject jsonBodyObj = new JSONObject();
        try{
            if( edit ) jsonBodyObj.put("id", idContatoEditar.toString());
            jsonBodyObj.put("nome", edtNome.getText().toString());
            jsonBodyObj.put("telefone", edtTel.getText().toString());
            jsonBodyObj.put("endereco", edtEnd.getText().toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
        final String requestBody = jsonBodyObj.toString();

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest strRequest = new StringRequest(Request.Method.POST, Constants.URL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        Toast.makeText(getApplicationContext(), "Salvo!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        setResult( Constants.RESULT_ADD, intent );
                        finish();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getApplicationContext(), "Erro, tente novamente!", Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                    // can get more details such as response.headers
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        queue.add(strRequest);
    }
}
