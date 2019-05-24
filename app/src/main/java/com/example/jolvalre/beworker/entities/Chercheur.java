package com.example.jolvalre.beworker.entities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Chercheur {

    public static String ID = "id";

    public static String NOM = "nom";

    public static String PRENOM = "prenom";

    public static String PASSWORD = "password";

    public static String DOMAINE = "domaine";
    public static String GENRE = "genre";
    public static String STATUT = "statut";
    public static String TELEPHONE = "telephone";

    public static String VILLE = "ville";
    public static String EMAIL = "email";
    public static String ETAT = "etat";

    public static String BIRTH_DAY = "birth_day";
    public static String ADRESSE = "adresse";

    public static void load(ChercheurV2 body, Context context){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        ChercheurV2 chercheur = body;

        editor.putString(Chercheur.ID, String.valueOf(chercheur.getId_chercheur()) );
        editor.putString(Chercheur.NOM, chercheur.getNom() );

        editor.putString(Chercheur.PRENOM, chercheur.getPrenom() );
        editor.putString(Chercheur.EMAIL, chercheur.getEmail() );

        editor.putString(Chercheur.PASSWORD, chercheur.getMot_de_passe() );

        editor.putString(Chercheur.GENRE, chercheur.getGenre() );
        editor.putString(Chercheur.BIRTH_DAY, chercheur.getDate_de_naissance().split("T")[0] );

        editor.putString(Chercheur.DOMAINE, chercheur.getDomaine() );
        editor.putString(Chercheur.STATUT, chercheur.getStatut() );

        editor.putString(Chercheur.VILLE, chercheur.getVille() );
        editor.putString(Chercheur.TELEPHONE, String.valueOf(chercheur.getTelephone()) );

        editor.apply();

    }
}
