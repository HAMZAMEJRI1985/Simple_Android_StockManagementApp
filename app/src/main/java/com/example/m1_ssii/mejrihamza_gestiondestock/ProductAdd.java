package com.example.m1_ssii.mejrihamza_gestiondestock;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.m1_ssii.mejrihamza_gestiondestock.Model.ApiGestock;
import com.example.m1_ssii.mejrihamza_gestiondestock.Model.DbServerResponse;
import com.example.m1_ssii.mejrihamza_gestiondestock.Model.RetrofitInstance;

import javax.xml.transform.sax.TemplatesHandler;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ProductAdd extends AppCompatActivity {

    private EditText name,price,quantity,description;
    private Button btnAjouter,btnAnnuler;
    private Intent reception,redirection;
    private Retrofit r = RetrofitInstance.getRetroInstance();
    private ApiGestock apigs = r.create(ApiGestock.class);
    private AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Ajouter produit");
        setContentView(R.layout.activity_product_add);

        name        = (EditText) findViewById(R.id.name);
        price       = (EditText) findViewById(R.id.price);
        quantity    = (EditText) findViewById(R.id.quantity);
        description = (EditText) findViewById(R.id.description);
        btnAjouter  = (Button)   findViewById(R.id.btnAjouter);
        btnAnnuler  = (Button)   findViewById(R.id.btnAnnuler);

        //On click Add button
        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alert = new AlertDialog.Builder(ProductAdd.this);
                alert.setTitle("ATTENTION !");
                alert.setMessage("Voulez vous vraiment ajouter ce produit?");
                alert.setPositiveButton("Ajouter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {
                        retrofit.Call<DbServerResponse> addCall = apigs.addProduct(
                                name.getText().toString(),
                                description.getText().toString(),
                                Integer.parseInt(price.getText().toString()),
                                Integer.parseInt(quantity.getText().toString())
                        );
                        addCall.enqueue(new Callback<DbServerResponse>() {
                            @Override
                            public void onResponse(Response<DbServerResponse> response, Retrofit retrofit) {
                                if(response.body().getStatus().trim().equals("1")) {
                                    Toast.makeText(ProductAdd.this, "Produit ajouté", Toast.LENGTH_LONG).show();
                                    redirection = new Intent(ProductAdd.this, ProductManagement.class);
                                    startActivity(redirection);
                                    finishAffinity();
                                }else{
                                    Toast.makeText(ProductAdd.this,"Problème d'jout",Toast.LENGTH_LONG).show();
                                    System.out.println(response.body().getStatus_message());
                                }
                            }

                            @Override
                            public void onFailure(Throwable t) {
                                Toast.makeText(ProductAdd.this,"Problème d'jout",Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                                t.getStackTrace();
                            }
                        });

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

        //On click cancel button
        btnAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirection = new Intent(ProductAdd.this, ProductManagement.class);
                startActivity(redirection);
                finishAffinity();
            }
        });


    }

    //Function to check empty EditText
    public boolean notValid(EditText e ){
        if(e.getText().toString().trim().equals(""))
            return true;
        else
            return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.default_menu,menu);
        menu.getItem(0).setTitle("Home");
        menu.getItem(1).setTitle("Paramètres");
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
