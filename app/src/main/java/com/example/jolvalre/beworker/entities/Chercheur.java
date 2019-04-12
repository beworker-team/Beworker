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
    @JsonProperty("ville")
    private String ville;
    @JsonProperty("email")
    private String email;
    @JsonProperty("etat")
    private Boolean etat;
}
