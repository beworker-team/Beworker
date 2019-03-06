package com.example.jolvalre.beworker;
/**
 * Classe utiliser pour representer les detailles d'une notification
 * */
public class Notification {

    private String societe = "";
    private String poste ="";
    private String ville="";
    private String date="";

    public Notification() {
    }

    public Notification(String societe, String poste, String ville, String date) {
        this.societe = societe;
        this.poste = poste;
        this.ville = ville;
        this.date = date;
    }

    public String getSociete() {
        return societe;
    }

    public String getPoste() {
        return poste;
    }

    public String getVille() {
        return ville;
    }

    public String getDate() {
        return date;
    }
}
