package com.example.jolvalre.beworker.entities;

import com.google.gson.annotations.SerializedName;

public class Offre {
    private static final long serialVersionUID = 1L;

    @SerializedName("id_offre")
    private Long id_offre;
    @SerializedName("id_employeur")
    private int id_employeur;
    @SerializedName("poste")
    private String poste;
    @SerializedName("ville")
    private String ville;
    @SerializedName("description")
    private String description;
    @SerializedName("date")
    private String date;
    @SerializedName("langue")
    private String langue;

    public Offre() {

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId_offre() {
        return id_offre;
    }

    public void setId_offre(Long id_offre) {
        this.id_offre = id_offre;
    }

    public int getId_employeur() {
        return id_employeur;
    }

    public void setId_employeur(int id_employeur) {
        this.id_employeur = id_employeur;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }
}
