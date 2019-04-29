package com.example.jolvalre.beworker.service;

import com.example.jolvalre.beworker.entities.Chercheur;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BeworkerService {
    //TODO: Definir le model des offres

    //TODO: Definir la methode pour l'inscription

    @POST("users/new")
    Call<Chercheur> inscrireUser(@Body Chercheur user);

    //TODO: Definir la methode pour l'authentification
}
