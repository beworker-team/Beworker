package com.example.jolvalre.beworker.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.jolvalre.beworker.R;

import static com.example.jolvalre.beworker.InscriptionActivity.chercheur;
import static com.example.jolvalre.beworker.InscriptionActivity.viewPager;

public class CompleterProfilFragment extends Fragment{
    ImageButton suivant, precedant;
    public CompleterProfilFragment() {

    }
    public static EditText age;
    public static EditText ville;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_completerprofil, container, false);
        //autre chose
        age = view.findViewById(R.id.age);
        ville = view.findViewById(R.id.ville);
        suivant = view.findViewById(R.id.suivant2);
        precedant = view.findViewById(R.id.precedant2);
        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }
        });
        precedant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
            }
        });
        chercheur.setMot_de_passe(age.toString());
        chercheur.setVille(ville.toString());
        return view;
    }
}
