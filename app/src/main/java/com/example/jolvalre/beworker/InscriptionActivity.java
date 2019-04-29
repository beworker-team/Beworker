package com.example.jolvalre.beworker;

import android.os.AsyncTask;
import android.content.ContentValues;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.example.jolvalre.beworker.adapter.PagerAdapter2;
import com.example.jolvalre.beworker.entities.Chercheur;

import com.example.jolvalre.beworker.database.DatabaseContract;

public class InscriptionActivity extends AppCompatActivity {
   public static ViewPager viewPager;

    ImageButton suivant,suivant2, precedant2, suivant3, precedant3,precedant4,suivant4,fab,suivant5,precedant5;
    LinearLayout page1, page2,page3,page4,page5;
    ConstraintLayout ConstraintLayout;
    EditText Nom,Prenom,Age,Ville,Email,Telephone,Adresse,Password,Confirme_password,Domaine, Statut;
    RadioGroup radioGroup;
    RadioButton Sexe_masculin , Sexe_feminin;
    Boolean from_test = true;
    Animation animFadeIn,animFadeIn2,animFadeIn3;

    class RESTTask extends AsyncTask<String, Void, ResponseEntity<Chercheur>> {


        @Override
        protected ResponseEntity<Chercheur> doInBackground(String... uri) {
            final String url = uri[0];
            RestTemplate restTemplate = new RestTemplate();
            try{
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                HttpHeaders headers = new HttpHeaders();
                headers.set("","");
                String auth = "";
                String encodeAuth = Base64.encodeToString(auth.getBytes(),Base64.DEFAULT);
                String authHeader = "Basic" + new String(encodeAuth);
                headers.set("Authorization",authHeader);

                HttpEntity<String> entity = new HttpEntity<String>(headers);

                ResponseEntity<Chercheur> response = restTemplate.exchange(url, HttpMethod.GET, entity,Chercheur.class);
                return response;
            }
            catch (Exception e){
                String message = e.getMessage();
                return null;
            }
        }
        protected void onPostExecute(ResponseEntity<Chercheur> result){
            HttpStatus status = result.getStatusCode();
            Chercheur chercheur = result.getBody();
        }
    }



    public void addPersonne(){

        ContentValues contentValues = new ContentValues();

        Log.e("alllo", "on viens de mettre une personne ");

        String sexe = null;
        // = ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
        if (radioGroup.getCheckedRadioButtonId() == R.id.sexe_feminin){
            sexe = "feminin";

        }else if (radioGroup.getCheckedRadioButtonId() == R.id.sexe_masculin){
            sexe = "masculin";
        }

        Log.e("la nature du sexe", sexe);

        contentValues.put(DatabaseContract.Chercheur.COLUMN_EMAIL, Email.getText().toString());

        contentValues.put(DatabaseContract.Chercheur.COLUMN_NOM, Nom.getText().toString());

        contentValues.put(DatabaseContract.Chercheur.COLUMN_PRENOM, Prenom.getText().toString());

        contentValues.put(DatabaseContract.Chercheur.COLUMN_MOT_DE_PASSE, Password.getText().toString());

        contentValues.put(DatabaseContract.Chercheur.COLUMN_DOMAINE, Domaine.getText().toString());

        contentValues.put(DatabaseContract.Chercheur.COLUMN_AGE, Age.getText().toString());

        Log.e("alllo", "on viens de mettre une personne ");

        contentValues.put(DatabaseContract.Chercheur.COLUMN_GENRE, sexe);

        contentValues.put(DatabaseContract.Chercheur.COLUMN_DOMAINE, Domaine.getText().toString());

        contentValues.put(DatabaseContract.Chercheur.COLUMN_TELEPHONE, Telephone.getText().toString());

        contentValues.put(DatabaseContract.Chercheur.COLUMN_VILLE, Ville.getText().toString());

        contentValues.put(DatabaseContract.Chercheur.COLUMN_STATUT, Statut.getText().toString());

        //contentValues.put(DatabaseContract.Chercheur.COLUMN_ADRESSE, Adresse.getText().toString());

        this.getContentResolver().insert(DatabaseContract.Chercheur.CONTENT_URI,contentValues);


        Log.e("alllo", "on viens de mettre une personne ");


        //on vide les champs
        Statut.setText("");
        Ville.setText("");
        Telephone.setText("");
        Domaine.setText("");
        Password.setText("");
        Nom.setText("");
        Prenom.setText("");
        Email.setText("");
        Adresse.setText("");
        Age.setText("");
        Confirme_password.setText("");


    }

    //    fonction passer en mode connecter
    private void goInOnlineMode(){
        Intent intent = new Intent(InscriptionActivity.this, MainActivity.class);
        intent.putExtra(MainActivity.ONLINE_MODE, "ON");
        startActivity(intent);
        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);


        Nom = findViewById(R.id.nom);
        Prenom = findViewById(R.id.prenom);
        Age = findViewById(R.id.age);
        Ville = findViewById(R.id.ville);
        Email = findViewById(R.id.email);
        Telephone = findViewById(R.id.telephone);
        Adresse = findViewById(R.id.adresse);
        Password = findViewById(R.id.password);
        Confirme_password = findViewById(R.id.confirme_password);
        Sexe_feminin = findViewById(R.id.sexe_feminin);
        Sexe_masculin = findViewById(R.id.sexe_masculin);
        Domaine=findViewById(R.id.domaine);
        Statut=findViewById(R.id.statut);
        radioGroup = findViewById(R.id.radio_group);




        page1 = findViewById(R.id.page1);
        page2 = findViewById(R.id.page2);
        page3 = findViewById(R.id.page3);
        page4 = findViewById(R.id.page4);
        page5 = findViewById(R.id.page5);
        suivant = findViewById(R.id.suivant);
        suivant2 = findViewById(R.id.suivant2);
        suivant3 = findViewById(R.id.suivant3);
        precedant3 = findViewById(R.id.precedant3);
        precedant2 = findViewById(R.id.precedant2);
        suivant4 = findViewById(R.id.suivant4);
        precedant4 = findViewById(R.id.precedant4);
        suivant5 = findViewById(R.id.suivant5);
        precedant5 = findViewById(R.id.precedant5);
        ConstraintLayout = findViewById(R.id.constraintlayout);

        viewPager = (ViewPager) findViewById(R.id.view_pager_inscription);
        PagerAdapter pagerAdapter2 = new PagerAdapter2(getSupportFragmentManager());
        if(viewPager != null)
        viewPager.setAdapter(pagerAdapter2);




        /*fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.startAnimation(animFadeIn2);
                Snackbar snackbar = Snackbar
                        .make(ConstraintLayout, "Message is deleted", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Snackbar snackbar1 = Snackbar.make(ConstraintLayout, "Message is restored!", Snackbar.LENGTH_SHORT);
                                snackbar1.show();
                            }
                        });

                snackbar.show();
            }
        });*/

        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fabe_in);
        animFadeIn2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fabe_in2);
        animFadeIn3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fabe_in3);



/*
        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suivant.startAnimation(animFadeIn2);
                page1.setVisibility(View.GONE);
                page2.setVisibility(View.VISIBLE);
                page2.startAnimation(animFadeIn);

            }
        });

        suivant2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suivant2.startAnimation(animFadeIn2);
                page2.setVisibility(View.GONE);
                page3.setVisibility(View.VISIBLE);
                page3.startAnimation(animFadeIn);
            }
        });


        precedant2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                precedant2.startAnimation(animFadeIn2);
                page2.setVisibility(View.GONE);
                page1.setVisibility(View.VISIBLE);
                page1.startAnimation(animFadeIn3);

            }
        });
        precedant3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                precedant3.startAnimation(animFadeIn2);
                page3.setVisibility(View.GONE);
                page2.setVisibility(View.VISIBLE);
                page2.startAnimation(animFadeIn3);
            }
        });
        precedant4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                precedant4.startAnimation(animFadeIn2);
                page4.setVisibility(View.GONE);
                page3.setVisibility(View.VISIBLE);
                page3.startAnimation(animFadeIn3);
            }
        });
        precedant5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                precedant5.startAnimation(animFadeIn2);
                page5.setVisibility(View.GONE);
                page4.setVisibility(View.VISIBLE);
                page4.startAnimation(animFadeIn3);
            }
        });
        suivant3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suivant3.startAnimation(animFadeIn2);
                page3.setVisibility(View.GONE);
                page4.setVisibility(View.VISIBLE);
                page4.startAnimation(animFadeIn);
            }
        });

        suivant4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suivant4.startAnimation(animFadeIn2);
                page4.setVisibility(View.GONE);
                page5.setVisibility(View.VISIBLE);
                page5.startAnimation(animFadeIn);
            }
        });
*/
     /*   suivant5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suivant4.startAnimation(animFadeIn2);

                if (!Password.getText().toString().equals(Confirme_password.getText().toString())) {
                    Snackbar.make(ConstraintLayout, "Veillez resaisir le mot de passe", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Password.setText(null);
                    Confirme_password.setText(null);
                    Password.setText(null);
                    Confirme_password.setText(null);

                }
                else if((Password.getText().toString().length()<4 ) && (!(Password.getText()==null||Password.getText().toString().equals("")))){
                    Snackbar.make(ConstraintLayout, "Le mot de passe dit contenir au moins 4 caracteres", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Password.setText(null);
                    Confirme_password.setText(null);
                    Password.setText(null);
                    Confirme_password.setText(null);
                }
                else {


                    if (ControlForm() == true) {

                        Snackbar snackbar = Snackbar
                                .make(ConstraintLayout, "Veillez remplir tout les champs oubligatoire", Snackbar.LENGTH_LONG)
                                .setAction("OK", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Snackbar snackbar1 = Snackbar.make(ConstraintLayout, "Merci de votre comprehension", Snackbar.LENGTH_SHORT);
                                        snackbar1.show();
                                        suivant4.startAnimation(animFadeIn2);
                                        page4.setVisibility(View.GONE);
                                        page1.setVisibility(View.VISIBLE);
                                        page1.startAnimation(animFadeIn);
                                        Password.setText(null);
                                        Confirme_password.setText(null);
                                    }
                                });

                        snackbar.show();
                    } else {
                        final String uri="http://";
                        System.out.println(uri);
                        new     RESTTask().execute(uri);
                        addPersonne();
                        Snackbar.make(ConstraintLayout, "Inscription effectue avec succes", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        goInOnlineMode();
                    }
                }
            }

        });
*/



    }

    public Boolean ControlForm(){
        Boolean  test_nom = false;
        Boolean  test_prenom = false;
        Boolean  test_age = false;
        Boolean  test_email = false;
        Boolean  test_password = false;



        if(Nom.getText()==null||Nom.getText().toString().equals("")){
            test_nom  = true;

        }
        if(Prenom.getText()==null||Prenom.getText().toString().equals("")){
            test_prenom  = true;

        }
        if(Age.getText()==null||Age.getText().toString().equals("")) {
            test_age = true;


        }
        if(Email.getText()==null||Email.getText().toString().equals("")) {
            test_email = true;


        }
        if((Password.getText()==null||Password.getText().toString().equals("")||Password.getText().toString().length()<4) || (Confirme_password.getText()==null||Confirme_password.getText().toString().equals("")) || (!Password.getText().toString().equals(Confirme_password.getText().toString())) ) {
            test_password = true;

        }

        if(test_age==false && test_email==false && test_nom==false && test_prenom==false && test_password==false){
            from_test = false;
        }


        return from_test;
    }
    private class InscriptionAsyncTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            return null;
        }
    }
}
