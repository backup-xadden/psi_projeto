package com.ementas.projecto;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ementas.projecto.adapters.FaturaListItemAdapter;
import com.ementas.projecto.db.FaturaDeserializer;
import com.ementas.projecto.db.LocalCache;
import com.ementas.projecto.models.Fatura;

import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FaturaActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "mobilecanteen";

    private ListView lvFatura;
    private FaturaListItemAdapter adapter;
    private List<Fatura> faturas;

    private Gson gson;
    private GsonBuilder gsonBuilder;
    private LocalCache cache;

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fatura);

        cache = new LocalCache(FaturaActivity.this);

        gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Fatura.class, new FaturaDeserializer());
        gson = gsonBuilder.create();

        lvFatura = (ListView) findViewById(R.id.listViewFatura);

        prefs = this.getSharedPreferences(PREFS_NAME, 0);
        int userid = prefs.getInt("id", -1);

        Ion.with(getApplicationContext())
                .load("GET", "http://codicesapp.com/_alunos/grupo1/mobilecanteen/api/web/v1/faturas/faturauserid/" + userid)
                .setLogging("MyLogs", Log.DEBUG)
                .setTimeout(10000)
                .setHeader("Content-type", "application/json")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        if (e != null) {
                            Toast.makeText(FaturaActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                        } else {
                            if (result != null) {
                                Type listType = new TypeToken<List<Fatura>>(){}.getType();
                                faturas = gson.fromJson(result, listType);
                               lvFatura.setAdapter(new FaturaListItemAdapter(FaturaActivity.this, faturas));
                            } else {
                                Toast.makeText(FaturaActivity.this, "No connection available", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
}
