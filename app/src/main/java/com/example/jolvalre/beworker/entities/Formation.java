package com.example.jolvalre.beworker.entities;

public class Formation {
    public String date, nom, etablissement="";

    public Formation(String date, String nom, String etablissement) {
        this.date = date;
        this.nom = nom;
        this.etablissement = etablissement;
    }
}
