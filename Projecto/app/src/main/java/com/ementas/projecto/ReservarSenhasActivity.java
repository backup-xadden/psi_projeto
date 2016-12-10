package com.ementas.projecto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ReservarSenhasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar_senhas);

        Spinner spinner = (Spinner)findViewById(R.id.spinner);

        ArrayAdapter spadapter = ArrayAdapter.createFromResource(this, R.array.diasdesemana, android.R.layout.simple_spinner_item);
        spadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spadapter);
    }
}
