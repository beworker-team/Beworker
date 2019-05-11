package com.example.jolvalre.beworker.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ChercheurV2 {

    @SerializedName("nom")
    private String nom;
    @SerializedName("prenom")
    private String prenom;
    @SerializedName("mot_de_passe")
    private String mot_de_passe;
    @SerializedName("domaine")
    private String domaine;
    @SerializedName("date_de_naissance")
    private String date_de_naissance;
    @SerializedName("genre")
    private String genre;
    @SerializedName("statut")
    private String statut;
    @SerializedName("telephone")
    private String telephone;
    @SerializedName("adresse")
    private String adresse;
    @SerializedName("ville")
    private String ville;
    @SerializedName("email")
    private String email;

    public ChercheurV2() {
    }

    public ChercheurV2(String nom, String prenom, String mot_de_passe, String domaine, String date_de_naissance,
                       String genre, String statut, String telephone, String adresse, String ville, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.mot_de_passe = mot_de_passe;
        this.domaine = domaine;
        this.date_de_naissance = date_de_naissance;
        this.genre = genre;
        this.statut = statut;
        this.telephone = telephone;
        this.adresse = adresse;
        this.ville = ville;
        this.email = email;

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

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDate_de_naissance() {
        return date_de_naissance;
    }

    public void setDate_de_naissance(String date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
    }

    @Override
    public String toString() {
        return "ChercheurV2{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", mot_de_passe='" + mot_de_passe + '\'' +
                ", domaine='" + domaine + '\'' +
                ", date_de_naissance='" + date_de_naissance + '\'' +
                ", genre='" + genre + '\'' +
                ", statut='" + statut + '\'' +
                ", telephone='" + telephone + '\'' +
                ", adresse='" + adresse + '\'' +
                ", ville='" + ville + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
