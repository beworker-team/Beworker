package com.example.jolvalre.beworker;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.jolvalre.beworker.adapter.PagerAdapter2;
import com.example.jolvalre.beworker.entities.Chercheur;
import com.example.jolvalre.beworker.entities.ChercheurV2;
import com.example.jolvalre.beworker.fragment.SignInFragment;
import com.example.jolvalre.beworker.network.RetrofitInstance;
import com.example.jolvalre.beworker.service.BeworkerService;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InscriptionActivity extends AppCompatActivity {

   public static InscriptionActivity current;
   public static ViewPager viewPager;
   public static Chercheur chercheur = new Chercheur();

    SignInFragment newDialog;

    //    fonction passer en mode connecter
    public static void goInOnlineMode(){

        BeworkerService service = RetrofitInstance.getRetrofitInstance().create(BeworkerService.class);
        ChercheurV2 chercheurV2 = new ChercheurV2(
                "jordy",
                "jord",
                "1234567890",
                "Android",
                new Date(),
                "M",
                "Celibataire",
                "666777888",
                "BP 08 95",
                "Yaounde",
                "jodryJord@gmail.com"
        );
        Call<Chercheur> call = service.inscrireUser(chercheurV2);
        InscriptionActivity.current.mShowDialog();
        call.enqueue(new Callback<Chercheur>() {
            @Override
            public void onResponse(Call<Chercheur> call, Response<Chercheur> response) {

                Toast.makeText(InscriptionActivity.current, response.toString(), Toast.LENGTH_LONG).show();
                Log.i("INSCRIPTION", response.toString());
                Intent intent = new Intent(InscriptionActivity.current, MainActivity.class);
                intent.putExtra(MainActivity.ONLINE_MODE, "ON");
                InscriptionActivity.current.startActivity(intent);
                InscriptionActivity.current.finish();
            }

            @Override
            public void onFailure(Call<Chercheur> call, Throwable t) {
                Toast.makeText(current, "Probleme de connexion", Toast.LENGTH_LONG).show();
                InscriptionActivity.current.mDismiss();
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

    public void mShowDialog(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        newDialog = new SignInFragment();

        newDialog.show(fragmentManager, "Animation");
    }

    public void mDismiss(){
        newDialog.dismiss();
    }


    public void returnToMainActivity(View view){
        Intent intent = new Intent(InscriptionActivity.this, MainActivity.class);
        //intent.putExtra(MainActivity.ONLINE_MODE, "ON");
        startActivity(intent);
        InscriptionActivity.this.finish();
    }

}