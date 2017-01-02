package com.ementas.projecto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ementas.projecto.adapters.EmentaMenuListItemAdapter;
import com.ementas.projecto.db.EmentaDeserializer;
import com.ementas.projecto.db.LocalCache;
import com.ementas.projecto.models.Ementa;
import com.ementas.projecto.models.Fatura;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    private Gson gson;
    private GsonBuilder gsonBuilder;
    private LocalCache cache;

    private List<Ementa> ementas;
    private List<Ementa> db_ementas;

    private ListView lvEmentaMenu;

//    private TextView tvDia;
//    private TextView tvData;
//    private TextView tvRefeicao;
//    private TextView tvSopa;
//    private TextView tvCarne;
//    private TextView tvPeixe;
//    private TextView tvVegetariano;
//    private TextView tvSobremesa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //NAVIGATION DRAWER

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

        // END NAVIGATION DRAWER

        prefs = getSharedPreferences("mobilecanteen", 0);
        editor = prefs.edit();

        cache = new LocalCache(MenuActivity.this);

        gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Ementa.class, new EmentaDeserializer());
        gson = gsonBuilder.create();

        lvEmentaMenu = (ListView) findViewById(R.id.listViewMenuEmenta);

        // set the username in the navigation drawer
        TextView tv_username = (TextView) header.findViewById(R.id.textViewUsername);
        String username = prefs.getString("username", "Username not found");
        tv_username.setText(username);

        if(!prefs.getBoolean("apiEmenta", false)) {
            editor.putBoolean("apiEmenta", true);
            Ion.with(getApplicationContext())
                    .load("GET", "http://codicesapp.com/_alunos/grupo1/mobilecanteen/api/web/v1/ementas")
                    .setTimeout(10000)
                    .setHeader("Content-type", "application/json")
                    .asJsonArray()
                    .setCallback(new FutureCallback<JsonArray>() {
                        @Override
                        public void onCompleted(Exception e, final JsonArray result) {
                            if (e != null) {
                                Toast.makeText(MenuActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                            } else {
                                if (result != null) {
                                    new AsyncTask<Void, Void, List<Ementa>>() {

                                        @Override
                                        protected List<Ementa> doInBackground(Void... params) {
                                            Type listType = new TypeToken<List<Ementa>>(){}.getType();
                                            ementas = gson.fromJson(result, listType);
                                            Log.d("ementas", ementas.toString());
                                            cache.saveEmentas(ementas);

                                            return ementas;
                                        }

                                        @Override
                                        protected void onPostExecute(List<Ementa> ementas1) {

                                            String data = "2016-12-05";
                                            db_ementas = cache.findDataEmenta(data);

                                            lvEmentaMenu.setAdapter(new EmentaMenuListItemAdapter(MenuActivity.this, db_ementas));
                                        }
                                    }.execute();
                                } else {
                                    Toast.makeText(MenuActivity.this, "No connection available", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
        } else {
            String data = "2016-12-05";
            db_ementas = cache.findDataEmenta(data);

            lvEmentaMenu.setAdapter(new EmentaMenuListItemAdapter(MenuActivity.this, db_ementas));

        }


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
