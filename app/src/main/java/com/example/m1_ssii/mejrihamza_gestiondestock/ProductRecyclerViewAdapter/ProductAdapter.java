package com.example.m1_ssii.mejrihamza_gestiondestock.ProductRecyclerViewAdapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.m1_ssii.mejrihamza_gestiondestock.Model.ApiGestock;
import com.example.m1_ssii.mejrihamza_gestiondestock.Model.Produit;
import com.example.m1_ssii.mejrihamza_gestiondestock.Model.RetrofitInstance;
import com.example.m1_ssii.mejrihamza_gestiondestock.R;

import java.util.List;

import retrofit.Retrofit;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {


    private Intent redirection ;
    private List<Produit> lstProduits ;
    private Retrofit r = RetrofitInstance.getRetroInstance();
    private ApiGestock apigs = r.create(ApiGestock.class);
    private AlertDialog.Builder alert;
    private Context c;

    public static class ProductViewHolder extends RecyclerView.ViewHolder{
        TextView name,price,quantity,modified,description;
        Button btnDelete,btnEdit;

        public ProductViewHolder (View v){
            super(v);
            name        = (TextView) v.findViewById(R.id.name);
            price       = (TextView) v.findViewById(R.id.price);
            quantity    = (TextView) v.findViewById(R.id.quantity);
            modified    = (TextView) v.findViewById(R.id.modified);
            description = (TextView) v.findViewById(R.id.description);
            btnDelete   = (Button) v.findViewById(R.id.btnSupprimer);
            btnEdit     = (Button) v.findViewById(R.id.btnModifier);
        }
    }

    public ProductAdapter(Context c , List<Produit> lst){
        this.c = c;
        this.lstProduits=lst;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_item_display,viewGroup,false);
        ProductViewHolder productViewHolder = new ProductViewHolder(v);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {
        final Produit p = lstProduits.get(i);
        productViewHolder.name.setText(p.getName());
        productViewHolder.price.setText(String.valueOf(p.getPrice()));
        productViewHolder.quantity.setText(String.valueOf(p.getQuantity()));
        productViewHolder.modified.setText(p.getModified());
        productViewHolder.description.setText(p.getDescription());

        // Changing RecyclerView item color for visibility
        if(i %2 == 1)
            productViewHolder.itemView.setBackgroundColor(Color.parseColor("#DEDADB"));
        else
            productViewHolder.itemView.setBackgroundColor(Color.parseColor("#BBB7B8"));

    }

    @Override
    public int getItemCount() {
        return  lstProduits.size();
    }






}
