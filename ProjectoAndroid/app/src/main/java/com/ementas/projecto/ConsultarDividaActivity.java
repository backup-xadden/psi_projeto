package com.ementas.projecto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

public class ConsultarDividaActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "mobilecanteen";

    SharedPreferences prefs;
    JsonObject json = new JsonObject();

    TextView tv_numero;
    TextView tv_divida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_divida);

        prefs = this.getSharedPreferences(PREFS_NAME, 0);

        int id = prefs.getInt("id", -1);
        String username = prefs.getString("username", null);

        tv_numero = (TextView) findViewById(R.id.textViewNumero);
        tv_divida = (TextView) findViewById(R.id.textViewDivida);

        String aux = "Número: " + username;
        tv_numero.setText(aux);

        json.addProperty("id", id);

        Ion.with(getApplicationContext())
                .load("POST", "http://codicesapp.com/_alunos/grupo1/mobilecanteen/api/web/v1/users/divida")
                .setTimeout(10000)
                .setHeader("Content-type", "application/json")
                .setJsonObjectBody(json)
                .asJsonObject()
                .withResponse()
                .setCallback(new FutureCallback<Response<JsonObject>>() {
                    @Override
                    public void onCompleted(Exception e, Response<JsonObject> result) {
                        if (e != null) {
                            Toast.makeText(ConsultarDividaActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                        } else {
                            if (result != null) {
                                if (result.getHeaders().code() != 200) {
                                    Toast.makeText(ConsultarDividaActivity.this, "!= 200", Toast.LENGTH_LONG).show();
                                } else {
                                    JsonObject jsonObj = result.getResult();

                                    String divida = jsonObj.get("divida").getAsString();

                                    String dividaPlaceholder  = "Deve: " + divida + "€";
                                    tv_divida.setText(dividaPlaceholder);
                                }
                            } else {
                                Toast.makeText(ConsultarDividaActivity.this, "No connection", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
}
