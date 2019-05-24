package com.example.jolvalre.beworker.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    Offre toacces;

    int pos;

    public AdapterOffreH(ArrayList<Offre> list, LayoutInflater inflater) {
        this.list = list;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public OffreViewH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View ov = inflater.inflate(R.layout.card_offre,viewGroup,false);
        ov.setTag(ov);
        return new OffreViewH(ov);
    }

    public void updateData(ArrayList<Offre> data){
        list =data;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull final OffreViewH offreViewH, int i) {
        Offre o = list.get(i);
        //on met a jour les element de la vue

        offreViewH.vPoste.setText(o.getPoste());
        offreViewH.vDescription.setText(o.getDescription());
        offreViewH.vDate.setText(o.getDate().split("T")[0]);
        offreViewH.vVille.setText(o.getVille());

//        toacces=o;
        offreViewH.base.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list== null)return;
                TextView p = v.findViewById(R.id.text_poste_card_offre);
                TextView d = v.findViewById(R.id.text_descript_card_offre);
                TextView date = v.findViewById(R.id.text_date_card_offre);
                TextView ville = v.findViewById(R.id.text_ville_card_offre);
                int good =-1;
                for (int j = 0; j <list.size() ; j++) {
                    Offre o = list.get(j);
                    if (o.getPoste().equals(p.getText().toString()) &&
                            o.getDescription().equals(d.getText().toString() )&&
                            o.getVille().equals(ville.getText().toString())
                            && o.getDate().split("T")[0].equals(date.getText().toString()) ){
                        System.out.println("++++ " + o.getPoste() + " "+j );
                        good =j;

                    }
                }
                if(good!= -1) MainActivity.openOffre(list.get(good));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list==null) return 0;
        return list.size();
    }


}
