package com.example.jolvalre.beworker.service;

import com.example.jolvalre.beworker.entities.ChercheurV2;
import com.example.jolvalre.beworker.entities.MyCategorie;
import com.example.jolvalre.beworker.entities.Offre;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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
    Call<Integer> deconnexion (@Path("email") String email);

    @PUT("/chercheur/update/")
    Call<ChercheurV2> updateUserInfo(@Body ChercheurV2 user);

    @GET("/offre/offre_par_categorie/{id_categorie}")
    Call<ArrayList<Offre>> offreCategorie (@Path("id_categorie") int id_categorie);

    @GET("/offre/list_categ")
    Call<ArrayList<MyCategorie>> listCategories ();

}
