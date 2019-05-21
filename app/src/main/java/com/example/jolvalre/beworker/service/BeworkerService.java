package com.example.jolvalre.beworker.service;

import com.example.jolvalre.beworker.entities.ChercheurV2;
import com.example.jolvalre.beworker.entities.Offre;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BeworkerService {
    //TODO: Definir le model des offres
    @GET("/offre/listAll")
//    Call<ArrayList<Offre>> getAllOffre(/*@Query("page") int page*/);
    Call<Offre> getAllOffre(/*@Query("page") int page*/);

    @POST("/chercheur/add")
    Call<ChercheurV2> inscrireUser(@Body ChercheurV2 user);

    @GET("/chercheur/authentification/{email}/{password}")
    Call<ChercheurV2> authentifiaction(@Path("email") String email, @Path("password") String password);

    @GET("/chercheur/deconnexion/{email}")
    Call<ChercheurV2> deconnexion (@Path("email") String email);

    @GET("/offre/offre_par_categorie/{id_categorie}")
    Call<ChercheurV2> offreCategorie (@Path("id_categorie") String email);

}
