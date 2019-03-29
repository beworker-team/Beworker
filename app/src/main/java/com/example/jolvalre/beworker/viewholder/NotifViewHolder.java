package com.example.jolvalre.beworker.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.jolvalre.beworker.R;

public class NotifViewHolder extends RecyclerView.ViewHolder {
    public TextView societe;
    public TextView poste;
    public TextView ville;
    public TextView dateV;

    public NotifViewHolder(@NonNull View itemView) {
        super(itemView);
        societe = (TextView)itemView.findViewById(R.id.text_societe_card_notif);
        poste = (TextView)itemView.findViewById(R.id.text_post_card_notif);
        ville = (TextView)itemView.findViewById(R.id.text_ville_card_notif);
        dateV = (TextView)itemView.findViewById(R.id.text_date_card_notif);
    }
}
