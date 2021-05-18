package com.example.m1_ssii.mejrihamza_gestiondestock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    private Button btnGestock,btnApropos,btnSettings,btnDeconx;
    private Intent redirection;
    private SharedPreferences pref;
    public static final String MY_PREFERENCES = "user_details";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Accueil");
        setContentView(R.layout.activity_home);

        btnGestock  = (Button) findViewById(R.id.btnGeStock);
        btnApropos  = (Button) findViewById(R.id.btnApropos);
        btnSettings = (Button) findViewById(R.id.btnSeting);
        btnDeconx   = (Button) findViewById(R.id.btnDecx);

        btnGestock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirection = new Intent(Home.this,ProductManagement.class);
                startActivity(redirection);
                finish();
            }
        });

        btnApropos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirection = new Intent(Home.this,About.class);
                startActivity(redirection);
                finish();
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirection = new Intent(Home.this,Settings.class);
                startActivity(redirection);
                finish();
            }
        });

        btnDeconx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pref  = getSharedPreferences(MY_PREFERENCES,MODE_PRIVATE);
                SharedPreferences.Editor prefEditor = pref.edit();
                prefEditor.clear();
                prefEditor.commit();
                redirection = new Intent(Home.this,MainActivity.class);
                startActivity(redirection);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.default_menu,menu);
        menu.getItem(0).setTitle("Gestion des produits");
        menu.getItem(1).setTitle("Paramètres");
        menu.getItem(2).setTitle("A propos");
        menu.getItem(3).setTitle("Déconnexion");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                redirection = new Intent(this,ProductManagement.class);
                startActivity(redirection);
                finishAffinity();
                return true;
            case R.id.item2:
                redirection = new Intent(this,Settings.class);
                startActivity(redirection);
                finishAffinity();
                return true;
            case R.id.item3:
                redirection = new Intent(this,About.class);
                startActivity(redirection);
                finishAffinity();
                return true;
            case R.id.item4:
                redirection = new Intent(this,MainActivity.class);
                startActivity(redirection);
                finishAffinity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
