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
    @GET("/listAll")
//    Call<ArrayList<OffreLocal>> getAllOffre(/*@Query("page") int page*/);
    Call<ArrayList<Offre> > getAllOffre();

    @POST("/add")
    Call<ChercheurV2> inscrireUser(@Body ChercheurV2 user);

    @GET("/authentification/{email}/{password}")
    Call<ChercheurV2> authentifiaction(@Path("email") String email, @Path("password") String password);

    @GET("/deconnexion/{email}")
    Call<Integer> deconnexion (@Path("email") String email);

    @PUT("/update/")
    Call<ChercheurV2> updateUserInfo(@Body ChercheurV2 user);

    @GET("/offre_par_categorie/{id_categorie}")
    Call<ArrayList<Offre>> offreCategorie (@Path("id_categorie") int id_categorie);

    @GET("/list_categ")
    Call<ArrayList<MyCategorie>> listCategories ();

    @GET("rechercher/{mot_cle}")
    Call<ArrayList<Offre>> searchOffre(@Path("mot_cle") String mot_cle);

}
