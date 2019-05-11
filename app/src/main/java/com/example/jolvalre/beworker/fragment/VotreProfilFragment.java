package com.example.jolvalre.beworker.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.jolvalre.beworker.R;

import static com.example.jolvalre.beworker.InscriptionActivity.chercheur;
import static com.example.jolvalre.beworker.InscriptionActivity.viewPager;


public class VotreProfilFragment extends Fragment {
    ImageButton suivant1;

    public VotreProfilFragment() {

    }
        public static EditText nom;
        public static EditText prenom;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_votreprofil, container, false);
        //autre chose
        nom = view.findViewById(R.id.nom);
        prenom = view.findViewById(R.id.prenom);
        suivant1 = view.findViewById(R.id.suivant);
        suivant1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (retriveDate()) viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }
        });
        return view;
    }

    public boolean retriveDate(){
        if (TextUtils.isEmpty(nom.getText())){
            nom.setError( getString(R.string.obligation_message) );
            return false;
        }
        chercheur.setNom(nom.getText().toString());
        if (TextUtils.isEmpty(prenom.getText())){
            prenom.setError( getString(R.string.obligation_message) );
            return false;
        }
        chercheur.setNom(nom.getText().toString());
        chercheur.setPrenom(prenom.getText().toString());
        return true;
    }

}