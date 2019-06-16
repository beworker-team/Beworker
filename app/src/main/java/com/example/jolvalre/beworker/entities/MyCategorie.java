package com.example.jolvalre.beworker.entities;

import com.google.gson.annotations.SerializedName;

public class MyCategorie {

    @SerializedName("id_categorie")
    private int id_categorie;
    @SerializedName("nom")
    private String categorie;

    public MyCategorie(int id_categorie, String categorie) {
        this.id_categorie = id_categorie;
        this.categorie = categorie;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

}
