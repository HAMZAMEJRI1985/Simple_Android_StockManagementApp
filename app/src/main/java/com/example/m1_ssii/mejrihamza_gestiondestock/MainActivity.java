package com.example.m1_ssii.mejrihamza_gestiondestock;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.m1_ssii.mejrihamza_gestiondestock.DataBase.UserDataBaseHelper;
import com.example.m1_ssii.mejrihamza_gestiondestock.Model.User;

import java.nio.charset.MalformedInputException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText log,passwd;
    private Button btnCx,btnSinsc;
    private Intent redirection;
    private AlertDialog.Builder alert;
    UserDataBaseHelper db = new UserDataBaseHelper(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        log         = (EditText) findViewById(R.id.login);
        passwd      = (EditText) findViewById(R.id.password);
        btnCx       = (Button) findViewById(R.id.cx);
        btnSinsc    = (Button) findViewById(R.id.insc);

        btnSinsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inscriptinIntent;
                inscriptinIntent = new Intent(MainActivity.this,Inscription.class);
                startActivity(inscriptinIntent);
                finish();
            }
        });

        btnCx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Validation des inputs
                if(log.getText().toString().trim().equals("") || passwd.getText().toString().trim().equals("")){
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("ATTENTION !!");
                    alert.setMessage("Login et/ou mot de passe non renseigné :");
                    alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alert.show();
                }else{
                    db.open();
                    User u = db.getUser(log.getText().toString(),passwd.getText().toString());
                    if(u.getNom() == null){
                        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                        alert.setTitle("ATTENTION !!");
                        alert.setMessage("Login et/ou mot de passe erroné");
                        alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alert.show();
                    }else{
                        Toast.makeText(MainActivity.this, "Bonjour "+u.getNom()+" "+u.getPrenom(), Toast.LENGTH_SHORT).show();
                        redirection = new Intent(MainActivity.this,Home.class);
                        startActivity(redirection);
                        finish();

                    }
                }
            }
        });


    }


}
