package com.example.m1_ssii.mejrihamza_gestiondestock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;
import android.widget.TextView;

import com.example.m1_ssii.mejrihamza_gestiondestock.Model.ApiGestock;
import com.example.m1_ssii.mejrihamza_gestiondestock.Model.DbServerResponse;
import com.example.m1_ssii.mejrihamza_gestiondestock.Model.Produit;
import com.example.m1_ssii.mejrihamza_gestiondestock.Model.RetrofitInstance;
import com.example.m1_ssii.mejrihamza_gestiondestock.ProductRecyclerViewAdapter.ProductAdapter;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class GestionProduits extends AppCompatActivity {


    private Retrofit r = RetrofitInstance.getRetroInstance();
    private ApiGestock apigs ;
    private Intent redirection;
    private List<Produit> lstProduits;
    private RecyclerView productRecyclerView;
    private RecyclerView.LayoutManager productLayoutManager;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Gestion des produits");
        setContentView(R.layout.activity_gestion_produits);

        apigs = r.create(ApiGestock.class);


        //GET ALL PRODUCTS
        retrofit.Call<List<Produit>> getProduct = apigs.getAllProducts();
        getProduct.enqueue(new Callback<List<Produit>>() {
            @Override
            public void onResponse(Response<List<Produit>> response, Retrofit retrofit) {
                lstProduits             = (List<Produit>) response.body();
                Produit p = lstProduits.get(1);
                System.out.println(p.getQuantity());

                productRecyclerView     = (RecyclerView) findViewById(R.id.product_recycler_view);
                productLayoutManager    = new LinearLayoutManager(GestionProduits.this);
                productRecyclerView.setLayoutManager(productLayoutManager);
                productAdapter          = new ProductAdapter(GestionProduits.this,lstProduits);
                productRecyclerView.setAdapter(productAdapter);


            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("FAILED");
            }
        });
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

        /*retrofit.Call<DbServerResponse> addProduct = apigs.addProduct("Chaise","Chaise metallique tournante capitonnee",630,12);
        addProduct.enqueue(new Callback<DbServerResponse>() {
            @Override
            public void onResponse(Response<DbServerResponse> response, Retrofit retrofit) {
                System.out.println(response.body().getStatus_message());
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t.getLocalizedMessage());
            }
        });*/

        //UPDATE PRODUCT
        /*retrofit.Call<DbServerResponse> updateProduct = apigs.upDate(39,"aaa","aaa",0,0);
        updateProduct.enqueue(new Callback<DbServerResponse>() {
            @Override
            public void onResponse(Response<DbServerResponse> response, Retrofit retrofit) {
                System.out.println(response.body().getStatus_message());
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t.getLocalizedMessage());
            }
        });*/

        //DELETE PRODUCT
        /*retrofit.Call<DbServerResponse> delProduct = apigs.delete(40);
        delProduct.enqueue(new Callback<DbServerResponse>() {
            @Override
            public void onResponse(Response<DbServerResponse> response, Retrofit retrofit) {
               System.out.println(response.body().getStatus_message());
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t.getLocalizedMessage());
            }


        });*/

    }
}
