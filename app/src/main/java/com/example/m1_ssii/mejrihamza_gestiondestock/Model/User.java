package com.example.m1_ssii.mejrihamza_gestiondestock.Model;

public class User {

    private int id;
    private String nom;
    private String prenom;
    private int tel;
    private String email;
    private String password;

    public User() {
    }

    public User(int id, String nom, String prenom, int tel, String email, String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
