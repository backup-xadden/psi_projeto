package com.ementas.projecto;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.ementas.projecto.adapters.EmentaListItemAdapter;
import com.ementas.projecto.db.LocalCache;
import com.ementas.projecto.models.Ementa;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class EmentasActivity extends AppCompatActivity {

    private LocalCache db;
    private ListView lvEmenta;
    private List<Ementa> ementas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ementas);

        db = new LocalCache(this);
        lvEmenta = (ListView) findViewById(R.id.listViewEmenta);

        new AsyncTask<Void, Void, List<Ementa>>() {

            @Override
            protected List<Ementa> doInBackground(Void... params) {
                ementas = db.findAllEmentas();
                return ementas;
            }

            @Override
            protected void onPostExecute(List<Ementa> ementas1) {
                lvEmenta.setAdapter(new EmentaListItemAdapter(EmentasActivity.this, ementas1));
            }
        }.execute();
    }
}
