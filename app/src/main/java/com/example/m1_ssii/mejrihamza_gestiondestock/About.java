package com.example.m1_ssii.mejrihamza_gestiondestock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class About extends AppCompatActivity {

    private Intent redirection;
    private SharedPreferences pref;
    public static final String MY_PREFERENCES = "user_details";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("À propos");
        setContentView(R.layout.activity_about);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.default_menu,menu);
        menu.getItem(0).setTitle("Home");
        menu.getItem(1).setTitle("Gestion des produits");
        menu.getItem(2).setTitle("A propos");
        menu.getItem(3).setTitle("Déconnexion");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                redirection = new Intent(this,Home.class);
                startActivity(redirection);
                finishAffinity();
                return true;
            case R.id.item2:
                redirection = new Intent(this,ProductManagement.class);
                startActivity(redirection);
                finishAffinity();
                return true;
            case R.id.item3:
                redirection = new Intent(this,About.class);
                startActivity(redirection);
                finishAffinity();
                return true;
            case R.id.item4:
                pref  = getSharedPreferences(MY_PREFERENCES,MODE_PRIVATE);
                SharedPreferences.Editor prefEditor = pref.edit();
                prefEditor.clear();
                prefEditor.commit();
                redirection = new Intent(About.this,MainActivity.class);
                startActivity(redirection);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
