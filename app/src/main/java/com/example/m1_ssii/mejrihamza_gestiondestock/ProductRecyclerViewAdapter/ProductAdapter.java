package com.example.m1_ssii.mejrihamza_gestiondestock.ProductRecyclerViewAdapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.m1_ssii.mejrihamza_gestiondestock.Model.ApiGestock;
import com.example.m1_ssii.mejrihamza_gestiondestock.Model.DbServerResponse;
import com.example.m1_ssii.mejrihamza_gestiondestock.Model.Product;
import com.example.m1_ssii.mejrihamza_gestiondestock.Model.RetrofitInstance;
import com.example.m1_ssii.mejrihamza_gestiondestock.ProductEdit;
import com.example.m1_ssii.mejrihamza_gestiondestock.ProductManagement;
import com.example.m1_ssii.mejrihamza_gestiondestock.R;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {


    private Intent redirection ;
    private List<Product> lstProducts;
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

    public ProductAdapter(Context c , List<Product> lst){
        this.c = c;
        this.lstProducts =lst;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_item_display,viewGroup,false);
        ProductViewHolder productViewHolder = new ProductViewHolder(v);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder productViewHolder, int i) {
        final Product p = lstProducts.get(i);
        productViewHolder.name.setText(p.getName());
        productViewHolder.price.setText(String.valueOf(p.getPrice()));
        productViewHolder.quantity.setText(String.valueOf(p.getQuantity()));
        productViewHolder.modified.setText(p.getModified());
        productViewHolder.description.setText(p.getDescription());

        //On click button edit product
        productViewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirection = new Intent(c,ProductEdit.class);
                putAllExtras(redirection,p);
                c.startActivity(redirection);
            }
        });

        //On Click button delete product
        productViewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert = new AlertDialog.Builder(c);
                alert.setTitle("ATTENTION !");
                alert.setMessage("Voulez vous vraiment supprimer ce produit");
                alert.setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {
                        retrofit.Call<DbServerResponse> deleteCall = apigs.delete(p.getId());
                        deleteCall.enqueue(new Callback<DbServerResponse>() {
                            @Override
                            public void onResponse(Response<DbServerResponse> response, Retrofit retrofit) {
                                if(response.body().getStatus().trim().equals("1")) {
                                    Toast.makeText(c, "Produit supprimé", Toast.LENGTH_LONG).show();
                                    //simple way to reload data
                                    redirection = new Intent(c, ProductManagement.class);
                                    c.startActivity(redirection);
                                }else{
                                    Toast.makeText(c,"Problème de modification",Toast.LENGTH_LONG).show();
                                    System.out.println(response.body().getStatus_message());
                                }
                            }

                            @Override
                            public void onFailure(Throwable t) {
                                Toast.makeText(c,"Problème d'jout",Toast.LENGTH_LONG).show();
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



        // Changing RecyclerView item color for visibility
        if(i %2 == 1)
            productViewHolder.itemView.setBackgroundColor(Color.parseColor("#DEDADB"));
        else
            productViewHolder.itemView.setBackgroundColor(Color.parseColor("#BBB7B8"));

    }

    @Override
    public int getItemCount() {
        return  lstProducts.size();
    }



    public void putAllExtras(Intent i , Product p ){
        i.putExtra("id",p.getId())  ;
        i.putExtra("name",p.getName());
        i.putExtra("price",p.getPrice());
        i.putExtra("quantity",p.getQuantity());
        i.putExtra("modified",p.getModified());
        i.putExtra("description",p.getDescription());
    }






}
