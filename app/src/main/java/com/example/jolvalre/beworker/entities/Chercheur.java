package com.example.jolvalre.beworker.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Chercheur {
    @JsonProperty("id_chercheur")
    private Long id_chercheur;
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("prenom")
    private String prenom;
    @JsonProperty("mot_de_passe")
    private String mot_de_passe;
    @JsonProperty("domaine")
    private String domaine;
    @JsonProperty("date_de_naissance")
    private Date date_de_naissance;
    @JsonProperty("genre")
    private String genre;
    @JsonProperty("statut")
    private String statut;
    @JsonProperty("telephone")
    private String telephone;
    @JsonProperty("adresse")
    private String adresse;
    @JsonProperty("ville")
    private String ville;
    @JsonProperty("email")
    private String email;
    @JsonProperty("etat")
    private Boolean etat;
    @JsonProperty("password")
    private String password;

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

    public Date getDate_de_naissance() {
        return date_de_naissance;
    }

    public void setDate_de_naissance(Date date_de_naissance) {
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
