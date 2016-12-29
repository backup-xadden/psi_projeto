package com.ementas.projecto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

public class LoginActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "mobilecanteen";

    EditText number;
    EditText password;
    Button buttonLogin;
    Intent intent;
    JsonObject json = new JsonObject();

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        prefs = this.getSharedPreferences(PREFS_NAME, 0);
        editor = prefs.edit();

        int id = prefs.getInt("id", -1);
        if(id != -1) {
            intent = new Intent(LoginActivity.this, MenuActivity.class);
            startActivity(intent);
            finish();
        }

        number = (EditText) findViewById(R.id.editTextNumber);
        password = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                json.addProperty("username", number.getText().toString());
                json.addProperty("password", password.getText().toString());

                Ion.with(getApplicationContext())
                        .load("POST", "http://codicesapp.com/_alunos/grupo1/mobilecanteen/api/web/v1/users/login")
                        .setTimeout(10000)
                        .setHeader("Content-type", "application/json")
                        .setJsonObjectBody(json)
                        .asJsonObject()
                        .withResponse()
                        .setCallback(new FutureCallback<Response<JsonObject>>() {
                            @Override
                            public void onCompleted(Exception e, Response<JsonObject> result) {
                                if (e != null) {
                                    Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                                } else {
                                    if (result != null) {
                                        if (result.getHeaders().code() != 200) {
                                            Toast.makeText(LoginActivity.this, "Username ou password errada", Toast.LENGTH_LONG).show();
                                        } else {
                                            JsonObject jsonObj = result.getResult();

                                            String token = jsonObj.get("token").getAsString();
                                            int id = jsonObj.get("id").getAsInt();
                                            String username = jsonObj.get("username").getAsString();

                                            editor.putString("token", token);
                                            editor.putInt("id", id);
                                            editor.putString("username", username);
                                            editor.apply();

                                            intent = new Intent(LoginActivity.this, MenuActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    } else {
                                        Toast.makeText(LoginActivity.this, "No result", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }
        });
    }
}
