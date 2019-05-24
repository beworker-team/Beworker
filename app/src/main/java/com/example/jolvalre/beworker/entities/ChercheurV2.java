package com.example.jolvalre.beworker.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ChercheurV2 {

    @SerializedName("id_chercheur")
    private Long id_chercheur;

    @SerializedName("email")
    private String email;

    @SerializedName("photo")
    private String photo;

    @SerializedName("nom")
    private String nom;

    @SerializedName("prenom")
    private String prenom;

    @SerializedName("date_de_naissance")
    private String date_de_naissance;

    @SerializedName("genre")
    private String genre;

    @SerializedName("statut")
    private String statut;

    @SerializedName("telephone")
    private int telephone;

    @SerializedName("ville")
    private String ville;

    @SerializedName("mot_de_passe")
    private String mot_de_passe;

    @SerializedName("domaine")
    private String domaine;

    @SerializedName("etat")
    private int etat;

    @SerializedName("adresse")
    private String adresse;

    public ChercheurV2(Long id_chercheur, String email, String photo, String nom, String prenom, String date_de_naissance, String genre, String statut, int telephone, String ville, String mot_de_passe, String domaine, int etat, String adresse) {
        this.id_chercheur = id_chercheur;
        this.email = email;
        this.photo = photo;
        this.nom = nom;
        this.prenom = prenom;
        this.date_de_naissance = date_de_naissance;
        this.genre = genre;
        this.statut = statut;
        this.telephone = telephone;
        this.ville = ville;
        this.mot_de_passe = mot_de_passe;
        this.domaine = domaine;
        this.etat = etat;
        this.adresse = adresse;
    }

    public ChercheurV2() {
    }

    public ChercheurV2(String email, String nom, String prenom, String date_de_naissance, String genre, String mot_de_passe, String domaine) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.date_de_naissance = date_de_naissance;
        this.genre = genre;
        this.mot_de_passe = mot_de_passe;
        this.domaine = domaine;
    }

    public Long getId_chercheur() {
        return id_chercheur;
    }

    public void setId_chercheur(Long id_chercheur) {
        this.id_chercheur = id_chercheur;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public String getDate_de_naissance() {
        return date_de_naissance;
    }

    public void setDate_de_naissance(String date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
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

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
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

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


    @Override
    public String toString() {
        String m="";
        m = "\n{ \n" +
                "\t\"email:\" "+email+", \n"+
                "\t\"photo:\" "+photo+", \n"+
                "\t\"nom:\" "+nom+", \n"+
                "\t\"prenom:\" "+prenom+", \n"+
                "\t\"date_de_naissance:\" "+date_de_naissance+", \n"+
                "\t\"genre:\" "+genre+", \n"+
                "\t\"statut:\" "+statut+", \n"+
                "\t\"telephone:\" "+telephone+", \n"+
                "\t\"ville:\" "+ville+", \n"+
                "\t\"mot_de_passe:\" "+mot_de_passe+", \n"+
                "\t\"domaine:\" "+domaine+", \n"+
                "\t\"etat:\" "+etat+", \n"+
                "\t\"adresse:\" "+adresse+", \n"+
                "\n}\n\n." ;
        return m;
    }
}
