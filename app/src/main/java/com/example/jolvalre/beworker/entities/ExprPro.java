package com.example.jolvalre.beworker.entities;

import java.util.ArrayList;

public class ExprPro {

    public String debut, fin, poste, societe;
    public ArrayList<String> taches;

    public ExprPro(String debut, String fin, String poste, String societe, ArrayList<String> taches) {
        this.debut = debut;
        this.fin = fin;
        this.poste = poste;
        this.societe = societe;
        this.taches = taches;
    }
}
