package com.example.jolvalre.beworker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InscriptionActivity extends AppCompatActivity {

   public static InscriptionActivity current;
   public static ViewPager viewPager;
   public static ChercheurV2 chercheur = new ChercheurV2();

    SignInFragment newDialog;

    //    fonction passer en mode connecter
    public static void goInOnlineMode(){

        BeworkerService service = RetrofitInstance.getRetrofitInstance().create(BeworkerService.class);

        final ChercheurV2 chercheurV2 = new ChercheurV2(
                "jordy",
                "jord",
                "1234567890",
                "Android",
                "2013-03-04",
                "M",
                "Celibataire",
                "666777888",
                "BP 08 95",
                "Yaounde",
                "qjodrybpJorvd@gmail.com"
        );

        System.out.println(chercheur);
        Call<ChercheurV2> call = service.inscrireUser(chercheur);
        InscriptionActivity.current.mShowDialog();
        call.enqueue(new Callback<ChercheurV2>() {
            @Override
            public void onResponse(Call<ChercheurV2> call, Response<ChercheurV2> response) {
                System.out.println(call.request());
                Log.i("INSCRIPTION", response.toString());
                System.out.println(response.toString());
                if (response.code() == 200 ){
                    //on met a jour les donnees des utilisateur
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(InscriptionActivity.current);
                    SharedPreferences.Editor editor = preferences.edit();
                    ChercheurV2 chercheur = response.body();

                    editor.putString(Chercheur.ID, chercheur.getEmail() );
                    editor.putString(Chercheur.NOM, chercheur.getNom() );

                    editor.putString(Chercheur.PRENOM, chercheur.getPrenom() );
                    editor.putString(Chercheur.EMAIL, chercheur.getEmail() );

                    editor.putString(Chercheur.PASSWORD, chercheur.getMot_de_passe() );

                    editor.putString(Chercheur.GENRE, chercheur.getGenre() );
                    editor.putString(Chercheur.BIRTH_DAY, chercheur.getDate_de_naissance() );

                    editor.putString(Chercheur.DOMAINE, chercheur.getDomaine() );
                    editor.putString(Chercheur.STATUT, chercheur.getStatut() );

                    editor.putString(Chercheur.VILLE, chercheur.getVille() );
                    editor.putString(Chercheur.TELEPHONE, chercheur.getTelephone() );

                    editor.apply();

                    Intent intent = new Intent(InscriptionActivity.current, MainActivity.class);
                    intent.putExtra(MainActivity.ONLINE_MODE, "ON");
                    InscriptionActivity.current.startActivity(intent);
                    InscriptionActivity.current.finish();
                }
                else{
                    System.out.println("jojo");
                    Toast.makeText(InscriptionActivity.current, "l'adresse Email existe deja", Toast.LENGTH_LONG).show();
                    InscriptionActivity.current.mDismiss();
                }

            }

            @Override
            public void onFailure(Call<ChercheurV2> call, Throwable t) {
                Toast.makeText(current, "Probleme de connexion", Toast.LENGTH_SHORT).cancel();
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