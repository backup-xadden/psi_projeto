package com.ementas.projecto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ementas.projecto.adapters.FaturaListItemAdapter;
import com.ementas.projecto.models.Fatura;

import java.util.ArrayList;
import java.util.List;

public class FaturaActivity extends AppCompatActivity {

    private ListView lvFatura;
    private FaturaListItemAdapter adapter;
    private List<Fatura> faturas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fatura);

        faturas = new ArrayList<>();

        faturas.add(new Fatura("2016-02-03", 1.00, "2", "Almoço", "Carne"));
        faturas.add(new Fatura("2016-11-03", 1.50, "3", "Jantar", "Vegetariano"));
        faturas.add(new Fatura("2016-05-13", 1.50, "2", "Almoço", "Carne"));
        faturas.add(new Fatura("2015-12-23", 2.00, "4", "Almoço", "Peixe"));

        lvFatura = (ListView) findViewById(R.id.listViewFatura);
        lvFatura.setAdapter(new FaturaListItemAdapter(this, faturas));


    }
}
