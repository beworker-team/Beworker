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

public class AutreFragment extends Fragment {
    ImageButton suivant , precedant;

    public AutreFragment() {

    }

    public static EditText domaine;
    public static  EditText statut;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_autre, container, false);
        //autre chose
        domaine = view.findViewById(R.id.domaine);
        statut = view.findViewById(R.id.statut);
        suivant = view.findViewById(R.id.suivant4);
        precedant = view.findViewById(R.id.precedant4);
        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retriveData();
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }
        });
        precedant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retriveData();
                viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
            }
        });
        return view;
    }

    public void retriveData(){
        chercheur.setDomaine(domaine.getText().toString());
        chercheur.setStatut(statut.getText().toString());
    }

}
