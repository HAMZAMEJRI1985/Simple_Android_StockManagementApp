package com.example.m1_ssii.mejrihamza_gestiondestock.Model;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

public interface ApiGestock {

    // Get all products
    @GET("MEJRIHamza-API-gestionDeStock/produits")
    Call<List<Produit>>getAllProducts();

    //Get a specific product
    @GET("MEJRIHamza-API-gestionDeStock/produits/{id}")
    Call<Produit> getProduct(@Path("id") int id);

    //Add a new product
    @FormUrlEncoded
    @POST("MEJRIHamza-API-gestionDeStock/produits")
    Call<Object> addProduct(
                                @Field("name") String name,
                                @Field("description") String description,
                                @Field("price") int price,
                                @Field("quantity") int quantity
                            );

    //Update product
    @FormUrlEncoded
    @PUT("MEJRIHamza-API-gestionDeStock/produits/{id}")
    Call<String> upDate(
            @Path("id") int id,
            @Field("name") String name,
            @Field("description") String description,
            @Field("price") int price,
            @Field("quantity") int quantity
            );

    //Delete product
    @DELETE("MEJRIHamza-API-gestionDeStock/produits/{id}")
    Call<Object> delete(@Path("id") int id);
}
