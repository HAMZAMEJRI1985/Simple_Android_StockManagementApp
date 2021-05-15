package com.example.m1_ssii.mejrihamza_gestiondestock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    private Button btnGestock,btnApropos,btnSettings,btnDeconx;
    private Intent redirection;

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
                redirection = new Intent(Home.this,GestionProduits.class);
                startActivity(redirection);
                finish();
            }
        });
    }
}
