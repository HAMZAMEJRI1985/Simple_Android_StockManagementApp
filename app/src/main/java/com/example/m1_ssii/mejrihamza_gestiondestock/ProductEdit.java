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
import android.widget.TextView;
import android.widget.Toast;

import com.example.m1_ssii.mejrihamza_gestiondestock.Model.ApiGestock;
import com.example.m1_ssii.mejrihamza_gestiondestock.Model.DbServerResponse;
import com.example.m1_ssii.mejrihamza_gestiondestock.Model.RetrofitInstance;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ProductEdit extends AppCompatActivity {

    private EditText name,price,quantity,description;
    private TextView modified;
    private Button btnModifier,btnAnnuler;
    private Intent reception,redirection;
    private Retrofit r = RetrofitInstance.getRetroInstance();
    private ApiGestock apigs = r.create(ApiGestock.class);
    private AlertDialog.Builder alert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Modifier produit");
        setContentView(R.layout.activity_product_edit);

        name        = (EditText) findViewById(R.id.name);
        price       = (EditText) findViewById(R.id.price);
        quantity    = (EditText) findViewById(R.id.quantity);
        modified    = (TextView) findViewById(R.id.modified);
        description = (EditText) findViewById(R.id.description);
        btnModifier = (Button)   findViewById(R.id.btnModifier);
        btnAnnuler  = (Button)   findViewById(R.id.btnAnnuler);


        try{
            reception = getIntent();
        }catch (Exception e){
            e.getStackTrace();
        }
        //Function to put Extras from Intent to XML fields
        putExtrasInFields(reception);

        //On click Edit button
        btnModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (notValid(name) || notValid(price) || notValid(quantity) || notValid(description)) {
                    alert = new AlertDialog.Builder(ProductEdit.this)
                            .setTitle("ATTENTION !")
                            .setMessage("Veuillez remplir tous les champs SVP.")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alert.show();
                }else{

                    alert = new AlertDialog.Builder(ProductEdit.this);
                    alert.setTitle("ATTENTION !");
                    alert.setMessage("Voulez vous vraiment enregistrer les modification ?");
                    alert.setPositiveButton("Enregistrer", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(final DialogInterface dialog, int which) {

                            retrofit.Call<DbServerResponse> updateCall = apigs.upDate(
                                    reception.getIntExtra("id",0),
                                    name.getText().toString(),
                                    description.getText().toString(),
                                    Integer.parseInt(price.getText().toString()),
                                    Integer.parseInt(quantity.getText().toString())
                            );
                            updateCall.enqueue(new Callback<DbServerResponse>() {
                                @Override
                                public void onResponse(Response<DbServerResponse> response, Retrofit retrofit) {
                                    if(response.body().getStatus().trim().equals("1")) {
                                        Toast.makeText(ProductEdit.this, "Produit modifié", Toast.LENGTH_LONG).show();
                                        redirection = new Intent(ProductEdit.this, ProductManagement.class);
                                        startActivity(redirection);
                                        finishAffinity();
                                    }else{
                                        Toast.makeText(ProductEdit.this,"Problème de modification",Toast.LENGTH_LONG).show();
                                        System.out.println(response.body().getStatus_message());
                                    }
                                }

                                @Override
                                public void onFailure(Throwable t) {
                                    Toast.makeText(ProductEdit.this,"Problème de modification",Toast.LENGTH_LONG).show();
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
            }
        });

        //On click cancel button
        btnAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirection = new Intent(ProductEdit.this,ProductManagement.class);
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

    public void putExtrasInFields (Intent reception){
        this.name.setText(reception.getStringExtra("name"));
        this.price.setText(String.valueOf(reception.getIntExtra("price",0)));
        this.quantity.setText(String.valueOf(reception.getIntExtra("quantity",0)));
        this.modified.setText(reception.getStringExtra("modified"));
        this.description.setText(reception.getStringExtra("description"));
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
