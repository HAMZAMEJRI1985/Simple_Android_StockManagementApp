package com.example.m1_ssii.mejrihamza_gestiondestock;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.m1_ssii.mejrihamza_gestiondestock.DataBase.UserDataBaseHelper;
import com.example.m1_ssii.mejrihamza_gestiondestock.Model.User;

public class Inscription extends AppCompatActivity {

    private EditText nom,prenom,tel,email,password,cpassword;
    private Button btnAnnuler,btnEnregistrer;
    private Intent redirection;
    private AlertDialog.Builder alert;
    UserDataBaseHelper db = new UserDataBaseHelper(Inscription.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Inscription");
        setContentView(R.layout.activity_inscription);

        nom             = (EditText) findViewById(R.id.nom);
        prenom          = (EditText) findViewById(R.id.prenom);
        tel             = (EditText) findViewById(R.id.tel);
        email           = (EditText) findViewById(R.id.email);
        password        = (EditText) findViewById(R.id.password);
        cpassword       = (EditText) findViewById(R.id.password2);
        btnEnregistrer  = (Button)   findViewById(R.id.btnEnregistrer);
        btnAnnuler      = (Button)   findViewById(R.id.btnAnnuler);

        btnEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Validation des input avec la fonction noValid(EditText e) -en bas- pour éviter les chaines vides
                if(notValid(nom) || notValid(prenom) || notValid(tel) || notValid(email)|| notValid(password) || notValid(cpassword)){
                    alert = new AlertDialog.Builder(Inscription.this);
                    alert.setTitle("ATTENTION !!");
                    alert.setMessage("Veuillez renseigner tous les champs SVP !");
                    alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alert.show();
                }else{
                    //Validation de la conformité du mot de pase et confirmation mot de passe
                    if(!password.getText().toString().trim().equals(cpassword.getText().toString().trim())){
                        alert = new AlertDialog.Builder(Inscription.this);
                        alert.setTitle("ATTENTION !!");
                        alert.setMessage("Veuillez confirmer à nouveau votre mot de passe !");
                        alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alert.show();
                    }else{
                        //Ajout de l'utilisateur dans la base
                        db.open();
                        User u = new User();
                        u.setNom(nom.getText().toString());
                        u.setPrenom(prenom.getText().toString());
                        u.setTel(Integer.parseInt(tel.getText().toString()));
                        u.setEmail(email.getText().toString());
                        u.setPassword(password.getText().toString());
                        double response = db.add(u);
                        if(response>0) {
                            Toast.makeText(Inscription.this, "Bien ! Connectez vous .", Toast.LENGTH_SHORT).show();
                            db.close();
                            redirection = new Intent(Inscription.this,MainActivity.class);
                            startActivity(redirection);
                            finish();
                        }else{
                            Toast.makeText(Inscription.this, "Problème d'inscription, veuillez réessayer! .", Toast.LENGTH_SHORT).show();
                        }

                    }

                }

            }
        });

        btnAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert = new AlertDialog.Builder(Inscription.this);
                alert.setTitle("ATTENTION !!");
                alert.setMessage("Voulez vous vraiment quiter l'inscription ?");
                alert.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        redirection = new Intent(Inscription.this,MainActivity.class);
                        startActivity(redirection);
                        finish();
                    }
                });
                alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.show();
            }
        });


    }

    public boolean notValid(EditText e ){
        if(e.getText().toString().trim().equals(""))
            return true;
        else
            return false;
    }
}
