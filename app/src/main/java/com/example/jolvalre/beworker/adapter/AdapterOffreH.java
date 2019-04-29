package com.example.jolvalre.beworker.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jolvalre.beworker.MainActivity;
import com.example.jolvalre.beworker.OffreActivity;
import com.example.jolvalre.beworker.entities.Offre;
import com.example.jolvalre.beworker.R;
import com.example.jolvalre.beworker.viewholder.OffreViewH;

import java.util.ArrayList;

/**
 * Cette classe est l'adapter qui nous permet d'afficher les card_offre a l'horizontal
 * elle possede deux attributs:
 *  -list ArrayList<Offre></Offre>: la liste des elements a afficher
 *  -inflater LayoutInflter: permet de creer la vue en fonction de l'element a afficher
 * */
public class AdapterOffreH extends RecyclerView.Adapter<OffreViewH> {

    //la liste des element a afficher
    ArrayList<Offre> list;
    //le LayoutInflater
    LayoutInflater inflater;

    //definit l'id de l'offre a la quelle on veut acceder
    int toacces;

    public AdapterOffreH(ArrayList<Offre> list, LayoutInflater inflater) {
        this.list = list;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public OffreViewH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View ov = inflater.inflate(R.layout.card_offre,viewGroup,false);
        return new OffreViewH(ov);
    }

    @Override
    public void onBindViewHolder(@NonNull final OffreViewH offreViewH, int i) {
        Offre o = list.get(i);
        //on met a jour les element de la vue
//        offreViewH.vPoste.setText(o.getPoste());
//        offreViewH.vDescription.setText(o.getDescription());
//        offreViewH.vDate.setText(o.getDate());
        toacces = i;
        offreViewH.base.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.openOffre(toacces);
            }
        });
        Intent intent = new Intent(MainActivity.MY_CONTEXT, OffreActivity.class);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}
