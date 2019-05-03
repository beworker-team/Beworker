package com.example.jolvalre.beworker.service;

import com.example.jolvalre.beworker.entities.Chercheur;
import com.example.jolvalre.beworker.entities.ChercheurV2;
import com.example.jolvalre.beworker.entities.Offre;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BeworkerService {
    //TODO: Definir le model des offres
    @GET("/offre/listAll")
//    Call<ArrayList<Offre>> getAllOffre(/*@Query("page") int page*/);
    Call<Offre> getAllOffre(/*@Query("page") int page*/);

    //TODO: Definir la methode pour l'inscription

    @POST("/chercheur/add")
    Call<Chercheur> inscrireUser(@Body ChercheurV2 user);

    //TODO: Definir la methode pour l'authentification
    @GET("/chercheur/authentification/{email}/{password}")
    Call<Chercheur> autthentifiaction(@Path("email") String email, @Path("password") String password);

}
