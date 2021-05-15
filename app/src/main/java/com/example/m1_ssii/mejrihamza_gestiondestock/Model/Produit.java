package com.example.m1_ssii.mejrihamza_gestiondestock.Model;

public class Produit {


    private int id;
    private String name;
    private String description;
    private int price;
    private int quantity;
    private String created;
    private String modified;

    public Produit() {}

    public Produit(int id, String name, String description, int price, int quantity, String created, String modified) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.created = created;
        this.modified = modified;
    }

    public Produit(String name, String description, int price, int quantity, String created, String modified) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.created = created;
        this.modified = modified;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCreated() {
        return created;
    }

    public String getModified() {
        return modified;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quentity) {
        this.quantity = quentity;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }
}
