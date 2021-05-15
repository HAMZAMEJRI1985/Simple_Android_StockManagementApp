package com.example.m1_ssii.mejrihamza_gestiondestock.Model;


import retrofit.GsonConverterFactory;

public class RetrofitInstance {

    //Méthode static qui retourne un objet retrofit qui nous permet de nous connecter au fournisseur du web service

    public  static retrofit.Retrofit getRetroInstance(){
        //Création d’un objet retrofit qui nous permet de nous connecter au fournisseur du web service
        retrofit.Retrofit RF =new retrofit.Retrofit.Builder()
                .baseUrl("http://192.168.1.16:80/")
                //.baseUrl("http://172.20.10.2:80/")
                //.baseUrl("http://127.0.0.1:80/")
                //.baseUrl("http://localhost:8000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return RF;
    }


}
