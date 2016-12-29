package com.ementas.projecto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        prefs = getSharedPreferences("mobilecanteen", 0);
        editor = prefs.edit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);

        TextView tv_username = (TextView) header.findViewById(R.id.textViewUsername);

        String username = prefs.getString("username", "Username not found");

        tv_username.setText(username);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_ementas) {
            Intent i = new Intent(MenuActivity.this, EmentasActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_reservar_senhas) {
            Intent i = new Intent(MenuActivity.this, ReservarSenhasActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_consultar_divida) {
            Intent i = new Intent(MenuActivity.this, ConsultarDividaActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_consultar_faturas) {
            Intent i = new Intent(MenuActivity.this, FaturaActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_logout) {
            JsonObject json = new JsonObject();
            String token = prefs.getString("token", null);
            json.addProperty("token", token);


            Ion.with(getApplicationContext())
                    .load("POST", "http://codicesapp.com/_alunos/grupo1/mobilecanteen/api/web/v1/users/logout")
                    .setTimeout(10000)
                    .setHeader("Content-type", "application/json")
                    .setJsonObjectBody(json)
                    .asJsonObject()
                    .withResponse()
                    .setCallback(new FutureCallback<Response<JsonObject>>() {
                        @Override
                        public void onCompleted(Exception e, Response<JsonObject> result) {
                            if (e != null) {
                                Toast.makeText(MenuActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                            } else {
                                if (result != null) {
                                    if (result.getHeaders().code() != 200) {
//                                        Toast.makeText(MenuActivity.this, "Code error", Toast.LENGTH_LONG).show();
                                    } else {

                                        editor.clear();
                                        editor.apply();

                                        Intent i = new Intent(MenuActivity.this, LoginActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                } else {
//                                    Toast.makeText(MenuActivity.this, "No result", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
