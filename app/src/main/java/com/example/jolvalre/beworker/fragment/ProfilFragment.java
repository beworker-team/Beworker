package com.example.jolvalre.beworker.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jolvalre.beworker.R;
import com.example.jolvalre.beworker.entities.Chercheur;

public class ProfilFragment extends Fragment {

    public ProfilFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil,container,false);
        //on affiche les information de l'utilisateur
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        TextView nom = view.findViewById(R.id.tv_show_name_user);
        String name = preferences.getString(Chercheur.NOM,"") + " " + preferences.getString(Chercheur.PRENOM,"");
        nom.setText(name);

        TextView age = view.findViewById(R.id.tv_show_birth_day_user);
        age.setText(preferences.getString(Chercheur.BIRTH_DAY,""));

        TextView domaine = view.findViewById(R.id.tv_show_domaine_user);
        domaine.setText(preferences.getString(Chercheur.DOMAINE,""));

        TextView city = view.findViewById(R.id.tv_show_city_user);
        city.setText(preferences.getString(Chercheur.VILLE,""));

        TextView tel = view.findViewById(R.id.tv_show_tel_user);
        tel.setText(preferences.getString(Chercheur.TELEPHONE,""));
        TextView email = view.findViewById(R.id.tv_show_email_user);
        email.setText(preferences.getString(Chercheur.EMAIL,""));

        return view;
    }
}
