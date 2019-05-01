package com.example.jolvalre.beworker;

import com.example.jolvalre.beworker.entities.Offre;

import java.util.ArrayList;

public class OffreCategorie {
    /**
     * Cette classe permet de regroupe les offres par categorie
     * Elle possede deux attributs:
     *  -titre: designe le nom de la categorie
     *  -data: est une liste des offres de cette categorie
     * */
    private String titre="";
    private ArrayList<Offre> data = new ArrayList<Offre>();

    public OffreCategorie() {
        /*
        * constructeur par defaut pour les tests devra etre modifier par la suite
        * */
        titre="Default Categorie";
        for(int i=0; i<5; i++){
            data.add(new Offre());
        }
    }

    public String getTitre() {
        return titre;
    }

    public ArrayList<Offre> getData() {
        return data;
    }
}
