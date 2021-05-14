package com.example.m1_ssii.mejrihamza_gestiondestock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Accueil");
        setContentView(R.layout.activity_home);
    }
}
