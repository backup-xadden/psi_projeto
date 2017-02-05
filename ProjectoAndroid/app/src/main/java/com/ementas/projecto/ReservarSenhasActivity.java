package com.ementas.projecto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.ementas.projecto.db.LocalCache;
import com.ementas.projecto.models.Ementa;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import java.util.List;

public class ReservarSenhasActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "mobilecanteen";

    Spinner spinnerDia;
    Spinner spinnerRefeicao;
    Spinner spinnerPrato;
    ArrayAdapter spadapter;
    LocalCache cache;
    List<Ementa> ementas;
    JsonObject json = new JsonObject();
    Button buttonReservar;

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar_senhas);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        cache = new LocalCache(this);
        ementas = cache.findAllEmentas();

        prefs = this.getSharedPreferences(PREFS_NAME, 0);

        spinnerDia = (Spinner)findViewById(R.id.spinnerDia);

        spadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item);
        spadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spadapter.add(ementas.get(0).getData());
        spadapter.add(ementas.get(2).getData());
        spadapter.add(ementas.get(4).getData());
        spadapter.add(ementas.get(6).getData());
        spadapter.add(ementas.get(8).getData());
        spinnerDia.setAdapter(spadapter);

        spinnerRefeicao = (Spinner)findViewById(R.id.spinnerRefeicao);

        spadapter = ArrayAdapter.createFromResource(this, R.array.refeicao, android.R.layout.simple_spinner_item);
        spadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRefeicao.setAdapter(spadapter);

        spinnerPrato = (Spinner)findViewById(R.id.spinnerPrato);

        spadapter = ArrayAdapter.createFromResource(this, R.array.prato, android.R.layout.simple_spinner_item);
        spadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPrato.setAdapter(spadapter);

        buttonReservar = (Button) findViewById(R.id.buttonReservar);

        buttonReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                json.addProperty("id", prefs.getInt("id", -1));
                json.addProperty("data", spinnerDia.getSelectedItem().toString());
                json.addProperty("refeicao", spinnerRefeicao.getSelectedItem().toString());
                json.addProperty("prato", spinnerPrato.getSelectedItem().toString());


                Ion.with(getApplicationContext())
                        .load("POST", "http://codicesapp.com/_alunos/grupo1/mobilecanteen/api/web/v1/faturas/reservarsenha")
                        .setTimeout(10000)
                        .setLogging("MyLogs", Log.DEBUG)
                        .setHeader("Content-type", "application/json")
                        .setJsonObjectBody(json)
                        .asJsonObject()
                        .withResponse()
                        .setCallback(new FutureCallback<Response<JsonObject>>() {
                            @Override
                            public void onCompleted(Exception e, Response<JsonObject> result) {
                                if (e != null) {
                                    Toast.makeText(ReservarSenhasActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                                } else {
                                    if (result != null) {
                                        if (result.getHeaders().code() != 200) {
                                            Toast.makeText(ReservarSenhasActivity.this, "!= 200", Toast.LENGTH_LONG).show();
                                        } else {
                                            JsonObject jsonObj = result.getResult();
                                            String apiResult = jsonObj.get("result").getAsString();
                                            Toast.makeText(ReservarSenhasActivity.this, apiResult, Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        Toast.makeText(ReservarSenhasActivity.this, "No result", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
