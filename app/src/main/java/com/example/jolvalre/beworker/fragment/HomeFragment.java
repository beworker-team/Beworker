package com.example.jolvalre.beworker.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jolvalre.beworker.adapter.AdapterOffreCategorie;
import com.example.jolvalre.beworker.entities.Offre;
import com.example.jolvalre.beworker.OffreCategorie;
import com.example.jolvalre.beworker.R;

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

        RecyclerView v=(RecyclerView) inflater.inflate(R.layout.fragment_home, container,false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //la liste des categorie
        ArrayList<OffreCategorie> dataOC = new ArrayList<OffreCategorie>();
        //on initialise la liste
        for(int i=0; i<4; i++){
            dataOC.add(new OffreCategorie());
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView rv =(RecyclerView) view.findViewById(R.id.recycle_view_fragment_home);
        rv.setLayoutManager(layoutManager);
        //on ajoute a la liste son adapter
        rv.setAdapter(new AdapterOffreCategorie(LayoutInflater.from(getContext()), dataOC));
    }
}
