package com.example.m1_ssii.mejrihamza_gestiondestock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.m1_ssii.mejrihamza_gestiondestock.Model.ApiGestock;
import com.example.m1_ssii.mejrihamza_gestiondestock.Model.Product;
import com.example.m1_ssii.mejrihamza_gestiondestock.Model.RetrofitInstance;
import com.example.m1_ssii.mejrihamza_gestiondestock.ProductRecyclerViewAdapter.ProductAdapter;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ProductManagement extends AppCompatActivity {


    private Retrofit r = RetrofitInstance.getRetroInstance();
    private ApiGestock apigs ;
    private Intent redirection;
    private List<Product> lstProducts;
    private RecyclerView productRecyclerView;
    private RecyclerView.LayoutManager productLayoutManager;
    private ProductAdapter productAdapter;
    private SharedPreferences pref;
    public static final String MY_PREFERENCES = "user_details";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Gestion des produits");
        setContentView(R.layout.activity_gestion_produits);

        apigs = r.create(ApiGestock.class);


        //GET ALL PRODUCTS
        retrofit.Call<List<Product>> getProduct = apigs.getAllProducts();
        getProduct.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Response<List<Product>> response, Retrofit retrofit) {
                lstProducts = (List<Product>) response.body();
                Product p = lstProducts.get(1);
                System.out.println(p.getQuantity());

                productRecyclerView     = (RecyclerView) findViewById(R.id.product_recycler_view);
                productLayoutManager    = new LinearLayoutManager(ProductManagement.this);
                productRecyclerView.setLayoutManager(productLayoutManager);
                productAdapter          = new ProductAdapter(ProductManagement.this, lstProducts);
                productRecyclerView.setAdapter(productAdapter);


            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("FAILED");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar_product_management,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addProduct:
                redirection = new Intent(this,ProductAdd.class);
                startActivity(redirection);
                finishAffinity();
                return true;
            case R.id.home:
                redirection = new Intent(this,Home.class);
                startActivity(redirection);
                finishAffinity();
                return true;
            case R.id.settings:
                redirection = new Intent(this,Settings.class);
                startActivity(redirection);
                finishAffinity();
                return true;
            case R.id.apropos:
                redirection = new Intent(this,About.class);
                startActivity(redirection);
                finishAffinity();
                return true;
            case R.id.logout:
                pref  = getSharedPreferences(MY_PREFERENCES,MODE_PRIVATE);
                SharedPreferences.Editor prefEditor = pref.edit();
                prefEditor.clear();
                prefEditor.commit();
                redirection = new Intent(ProductManagement.this,MainActivity.class);
                startActivity(redirection);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
