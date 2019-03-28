package com.example.jolvalre.beworker;

import android.content.ContentValues;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.example.jolvalre.beworker.database.BeworkerProvider;
import com.example.jolvalre.beworker.database.DatabaseContract;

public class InscriptionActivity extends AppCompatActivity {

    ImageButton suivant,suivant2, precedant2, suivant3, precedant3,precedant4,suivant4;
    LinearLayout page1, page2,page3,page4;
    ConstraintLayout ConstraintLayout;
    EditText Nom,Prenom,Age,Ville,Email,Telephone,Adresse,Password,Confirme_password;
    RadioGroup radioGroup;
    RadioButton Sexe_masculin , Sexe_feminin;
    Boolean from_test = true;
    Animation animFadeIn,animFadeIn2;

    public void viderChamps(){
      Nom.setText("");

      Prenom.setText("");

      Age.setText("");

      Ville.setText("");

      Email.setText("");

      Adresse.setText("");

      Telephone.setText("");

      Password.setText("");

      Confirme_password.setText("");
    }


    public void addChercheur(){




        Log.e("alllo", "on veux mettre une personne ");

        String nom = this.Nom.getText().toString();

        String prenom = this.Prenom.getText().toString();

        String age = this.Age.getText().toString();

        String ville = this.Ville.getText().toString();

        String email = this.Email.getText().toString();

        String adresse = this.Adresse.getText().toString();

        String telephone = this.Telephone.getText().toString();

        String password = this.Password.getText().toString();

        String  genre = "masculin";

        String domaine = "informatique";

        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseContract.Chercheur.COLUMN_EMAIL, email);

        contentValues.put(DatabaseContract.Chercheur.COLUMN_NOM, nom);

        contentValues.put(DatabaseContract.Chercheur.COLUMN_PRENOM, prenom);

        contentValues.put(DatabaseContract.Chercheur.COLUMN_DOMAINE, domaine);

        contentValues.put(DatabaseContract.Chercheur.COLUMN_MOT_DE_PASSE, password);

        contentValues.put(DatabaseContract.Chercheur.COLUMN_TELEPHONE, telephone);

        contentValues.put(DatabaseContract.Chercheur.COLUMN_GENRE, genre);

        contentValues.put(DatabaseContract.Chercheur.COLUMN_STATUT, adresse);

        contentValues.put(DatabaseContract.Chercheur.COLUMN_AGE, age);

        contentValues.put(DatabaseContract.Chercheur.COLUMN_VILLE, ville);


        BeworkerProvider rep = new  BeworkerProvider();

        boolean bl = rep.onCreate();

        Log.e("alllo", "la valeur de la creation du provider :  "+ bl);

        this.getContentResolver().insert(DatabaseContract.Chercheur.CONTENT_URI,contentValues);


        Log.e("alllo", "on viens de mettre une personne ");

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);



        Log.e("alllo", "on creer la vue inscription ");

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
        radioGroup = findViewById(R.id.radio_group);



        page1 = findViewById(R.id.page1);
        page2 = findViewById(R.id.page2);
        page3 = findViewById(R.id.page3);
        page4 = findViewById(R.id.page4);
        suivant = findViewById(R.id.suivant);
        suivant2 = findViewById(R.id.suivant2);
        suivant3 = findViewById(R.id.suivant3);
        precedant3 = findViewById(R.id.precedant3);
        precedant2 = findViewById(R.id.precedant2);
        suivant4 = findViewById(R.id.suivant4);
        precedant4 = findViewById(R.id.precedant4);
        ConstraintLayout = findViewById(R.id.constraintlayout);




        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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



        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suivant.startAnimation(animFadeIn2);
                page1.setVisibility(View.GONE);
                page2.setVisibility(View.VISIBLE);
                page2.startAnimation(animFadeIn);


                Log.e("alllo", "premier feuilletage ");

            }
        });

        suivant2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suivant2.startAnimation(animFadeIn2);
                page2.setVisibility(View.GONE);
                page3.setVisibility(View.VISIBLE);
                page3.startAnimation(animFadeIn);

                Log.e("alllo", "scond feuilletage ");
            }
        });


        precedant2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                precedant2.startAnimation(animFadeIn2);
                page2.setVisibility(View.GONE);
                page1.setVisibility(View.VISIBLE);
                page1.startAnimation(animFadeIn);

            }
        });
        precedant3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                precedant3.startAnimation(animFadeIn2);
                page3.setVisibility(View.GONE);
                page2.setVisibility(View.VISIBLE);
                page2.startAnimation(animFadeIn);
            }
        });
        precedant4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                precedant4.startAnimation(animFadeIn2);
                page4.setVisibility(View.GONE);
                page3.setVisibility(View.VISIBLE);
                page3.startAnimation(animFadeIn);
            }
        });
        suivant3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suivant3.startAnimation(animFadeIn2);
                page3.setVisibility(View.GONE);
                page4.setVisibility(View.VISIBLE);
                page4.startAnimation(animFadeIn);

                Log.e("alllo", "troisieme feuilletage ");
            }
        });
        suivant4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suivant4.startAnimation(animFadeIn2);
                Log.e("alllo", "dernier feuilletage ");

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
                        Snackbar.make(ConstraintLayout, "Inscription effectue avec succes", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        addChercheur();
                        viderChamps();
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
}
