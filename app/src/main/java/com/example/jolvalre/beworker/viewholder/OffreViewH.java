package com.example.jolvalre.beworker.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.jolvalre.beworker.R;

public class OffreViewH extends RecyclerView.ViewHolder {
    TextView vPoste;
    TextView vDescription;
    TextView vDate;
    public OffreViewH(@NonNull View itemView) {
        super(itemView);
        vPoste= (TextView)itemView.findViewById(R.id.text_poste_card_offre);
        vDescription= (TextView)itemView.findViewById(R.id.text_poste_card_offre);
        vDate= (TextView)itemView.findViewById(R.id.text_poste_card_offre);
    }
}
