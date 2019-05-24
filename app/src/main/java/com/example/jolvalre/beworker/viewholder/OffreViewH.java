package com.example.jolvalre.beworker.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jolvalre.beworker.R;

public class OffreViewH extends RecyclerView.ViewHolder {

    public TextView vPoste;
    public TextView vDescription;
    public TextView vDate;
    public TextView vVille;


    public RelativeLayout base;

    public OffreViewH(@NonNull View itemView) {
        super(itemView);
        base = (RelativeLayout)itemView;
        vPoste= (TextView)itemView.findViewById(R.id.text_poste_card_offre);
        vDescription= (TextView)itemView.findViewById(R.id.text_descript_card_offre);
        vDate= (TextView)itemView.findViewById(R.id.text_date_card_offre);
        vVille= (TextView)itemView.findViewById(R.id.text_ville_card_offre);

    }
}
