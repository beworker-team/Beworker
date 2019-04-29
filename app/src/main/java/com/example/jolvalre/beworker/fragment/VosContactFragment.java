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

public class VosContactFragment extends Fragment {
    ImageButton suivant, precedant;

    public VosContactFragment() {

    }
    public static EditText email;
    public static EditText telephone;
    public static  EditText adresse;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_voscontact, container, false);
        //autre chose
        email = view.findViewById(R.id.email);
        telephone = view.findViewById(R.id.telephone);
        adresse = view.findViewById(R.id.adresse);
        suivant = view.findViewById(R.id.suivant3);
        precedant = view.findViewById(R.id.precedant3);
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
        chercheur.setEmail(email.toString());
        chercheur.setTelephone(telephone.toString());
        chercheur.setAdresse(adresse.toString());
        return view;
    }
}
