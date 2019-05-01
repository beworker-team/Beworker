package com.example.jolvalre.beworker;

import android.content.Context;
import android.os.AsyncTask;
import android.content.ContentValues;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jolvalre.beworker.adapter.PagerAdapter2;
import com.example.jolvalre.beworker.entities.Chercheur;
import com.example.jolvalre.beworker.network.RetrofitInstance;
import com.example.jolvalre.beworker.service.BeworkerService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InscriptionActivity extends AppCompatActivity {

   public static AppCompatActivity current;
   public static ViewPager viewPager;
   public static Chercheur chercheur = new Chercheur();

    //    fonction passer en mode connecter
    public static void goInOnlineMode(){

        BeworkerService service = RetrofitInstance.getRetrofitInstance().create(BeworkerService.class);
        Call<Chercheur> call = service.inscrireUser(chercheur);
        call.enqueue(new Callback<Chercheur>() {
            @Override
            public void onResponse(Call<Chercheur> call, Response<Chercheur> response) {

                Toast.makeText(InscriptionActivity.current, response.toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(InscriptionActivity.current, MainActivity.class);
                intent.putExtra(MainActivity.ONLINE_MODE, "ON");
                InscriptionActivity.current.startActivity(intent);
                InscriptionActivity.current.finish();
            }

            @Override
            public void onFailure(Call<Chercheur> call, Throwable t) {
                Toast.makeText(current, "Probleme de connexion", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);
        InscriptionActivity.current = this;

        viewPager = (ViewPager) findViewById(R.id.view_pager_inscription);
        PagerAdapter pagerAdapter2 = new PagerAdapter2(getSupportFragmentManager());
        if(viewPager != null) viewPager.setAdapter(pagerAdapter2);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(InscriptionActivity.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

}