package com.example.jolvalre.beworker.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.jolvalre.beworker.R;

public class CatOffreViewHolder extends RecyclerView.ViewHolder {

    public TextView text;
    public RecyclerView recyclerView;


    public CatOffreViewHolder(@NonNull View itemView) {
        super(itemView);
        text = (TextView)itemView.findViewById(R.id.text_titre_categorie_offre);
        recyclerView = (RecyclerView)itemView.findViewById(R.id.recycle_view_categorie_offre);
    }
}
