package com.example.jolvalre.beworker.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    //instance retrofit qui sera cree
    private static Retrofit retrofit = null;
    private static Retrofit retrofitOffre = null;
    private static Retrofit retrofitChercheur = null;
    /**
     * l'adresse url de base de l'api
     * TODO: Remplacer cette adresse par l'adresse effective une deploye
     * */
    private static String BASE_URL = "http://192.168.43.52:90";
    private static String BASE_URL_CHERCHEUR = "https://b-chercheur.herokuapp.com";
//    private static String BASE_URL = "https://7f981107.ngrok.io";
   private static String BASE_URL_OFFRE = "https://b-offre.herokuapp.com";

    /**
     * Retourne une instance retrofit a base de BASE_URL
     * */
    public static Retrofit getRetrofitInstance(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    /**
     * Retourne une instance retrofit a base de BASE_URL
     * */
    public static Retrofit getRetrofitInstanceChercheur(){
        if (retrofitChercheur == null){
            retrofitChercheur = new Retrofit.Builder()
                    .baseUrl(BASE_URL_CHERCHEUR)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitChercheur;
    }
    /**
     * Retourne une instance retrofit a base de BASE_URL
     * */
    public static Retrofit getRetrofitInstanceOffre(){
        if (retrofitOffre == null){
            retrofitOffre = new Retrofit.Builder()
                    .baseUrl(BASE_URL_OFFRE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitOffre;
    }

}
