package com.example.jolvalre.beworker.service;

import com.example.jolvalre.beworker.entities.Chercheur;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BeworkerService {
    //TODO: Definir le model des offres

    //TODO: Definir la methode pour l'inscription

    @POST("/chercheur/add")
    Call<Chercheur> inscrireUser(@Body Chercheur user);

    @GET("/chercheur/authentification/{email}/{password}")
    Call<Chercheur> autthentifiaction(@Path("email") String email, @Path("password") String password);

    //TODO: Definir la methode pour l'authentification
}
