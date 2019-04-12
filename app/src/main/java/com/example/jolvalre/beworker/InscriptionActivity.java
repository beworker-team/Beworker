package com.example.jolvalre.beworker;

import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
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

import com.example.jolvalre.beworker.entities.Chercheur;

public class InscriptionActivity extends AppCompatActivity {

    ImageButton suivant,suivant2, precedant2, suivant3, precedant3,precedant4,suivant4,fab,suivant5,precedant5;
    LinearLayout page1, page2,page3,page4,page5;
    ConstraintLayout ConstraintLayout;
    EditText Nom,Prenom,Age,Ville,Email,Telephone,Adresse,Password,Confirme_password;
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




        fab = findViewById(R.id.fab);
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
        });

        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fabe_in);
        animFadeIn2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fabe_in2);
        animFadeIn3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fabe_in3);



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
        suivant5.setOnClickListener(new View.OnClickListener() {
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
                        Snackbar.make(ConstraintLayout, "Inscription effectue avec succes", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            }

        });




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
