package com.example.jolvalre.beworker.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Chercheur {
    @SerializedName("id_chercheur")
    private Long id_chercheur;
    @SerializedName("nom")
    private String nom;
    @SerializedName("prenom")
    private String prenom;
    @SerializedName("mot_de_passe")
    private String password;

    @SerializedName("domaine")
    private String domaine;
//    @SerializedName("date_de_naissance")
//    private Date date_de_naissance;

    @SerializedName("genre")
    private String genre;
    @SerializedName("statut")
    private String statut;
    @SerializedName("telephone")
    private String telephone;

    @SerializedName("ville")
    private String ville;
    @SerializedName("email")
    private String email;
    @SerializedName("etat")
    private Boolean etat;

//    @SerializedName("adresse")
//    private String adresse;
    @SerializedName("date_de_naissance")
    private String age;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

//    public String getAdresse() {
//        return adresse;
//    }
//
//    public String getPassword() {
//        return password;
//    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId_chercheur() {
        return id_chercheur;
    }

    public void setId_chercheur(Long id_chercheur) {
        this.id_chercheur = id_chercheur;
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

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

//    public Date getDate_de_naissance() {
//        return date_de_naissance;
//    }
//
//    public void setDate_de_naissance(Date date_de_naissance) {
//        this.date_de_naissance = date_de_naissance;
//    }

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

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }
}
