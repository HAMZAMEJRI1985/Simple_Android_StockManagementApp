package com.example.m1_ssii.mejrihamza_gestiondestock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.m1_ssii.mejrihamza_gestiondestock.Model.ApiGestock;
import com.example.m1_ssii.mejrihamza_gestiondestock.Model.Produit;
import com.example.m1_ssii.mejrihamza_gestiondestock.Model.RetrofitInstance;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.PUT;

public class GestionProduits extends AppCompatActivity {

    private TextView test;
    private Retrofit r = RetrofitInstance.getRetroInstance();
    private ApiGestock apigs ;
    private Intent redirection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Gestion des produits");
        setContentView(R.layout.activity_gestion_produits);

        test = (TextView) findViewById(R.id.test);
        apigs = r.create(ApiGestock.class);


        //GET ALL PRODUCTS
        /*retrofit.Call<List<Produit>> getProduct = apigs.getAllProducts();
        getProduct.enqueue(new Callback<List<Produit>>() {
            @Override
            public void onResponse(Response<List<Produit>> response, Retrofit retrofit) {
                Produit p = response.body().get(3);
                test.setText(p.getDescription());
            }

            @Override
            public void onFailure(Throwable t) {
                test.setText("FAILED");
            }
        });*/
        //GET SPECIFIC PRODUCT
        /*retrofit.Call<Produit> getProduct = apigs.getProduct(2);
        getProduct.enqueue(new Callback<Produit>() {
            @Override
            public void onResponse(Response<Produit> response, Retrofit retrofit) {
                Produit p = response.body();
                test.setText(p.getDescription());
            }

            @Override
            public void onFailure(Throwable t) {
                test.setText("FAILED");
            }


        });*/

        //ADDING A NEW PRODUCT
        /*Produit p = new Produit ();
        p.setName("Chaise");
        p.setDescription("Chaise metallique tournante capitonnée");
        p.setPrice(630);
        p.setQuantity(12);
        retrofit.Call<Object> addProduct = apigs.addProduct("Chaise","Chaise metallique tournante capitonnee",630,12);
        addProduct.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Response<Object> response, Retrofit retrofit) {
                test.setText(response.body().toString());
            }

            @Override
            public void onFailure(Throwable t) {
                test.setText("kooooo");
                System.out.println(t.getLocalizedMessage());
            }
        });*/

        //UPDATE PRODUCT
        /*retrofit.Call<String> updateProduct = apigs.upDate(22,"aaa","aaa",0,0);
        updateProduct.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
                test.setText(response.body().toString());
            }

            @Override
            public void onFailure(Throwable t) {
                test.setText("kooooo");
            }
        });*/

        //DELETE PRODUCT
        /*retrofit.Call<Object> delProduct = apigs.delete(23);
        delProduct.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Response<Object> response, Retrofit retrofit) {
                test.setText(response.body().toString());
            }

            @Override
            public void onFailure(Throwable t) {
                test.setText("mochklaaaa");
                System.out.println(t.getLocalizedMessage());
            }


        });*/

    }
}
