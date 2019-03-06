package com.example.jolvalre.beworker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ArrayList<Offre> dataOffre = new ArrayList<Offre>();

    public HomeFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ListView v=null;
        v = (ListView) inflater.inflate(R.layout.fragment_home, container,false);
        //la liste des categorie
        ArrayList<OffreCategorie> dataOC = new ArrayList<OffreCategorie>();
        //on initialise la liste
        for(int i=0; i<3; i++){
            dataOC.add(new OffreCategorie());
        }
        //on ajoute a la liste son adapter
        v.setAdapter(new AdapterOffreCategorie(inflater, dataOC));

        return v;
    }
}
