package com.example.jolvalre.beworker.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jolvalre.beworker.entities.Notification;
import com.example.jolvalre.beworker.R;
import com.example.jolvalre.beworker.viewholder.NotifViewHolder;

import java.util.ArrayList;

public class AdapterNotification extends RecyclerView.Adapter<NotifViewHolder> {

    //l'inflater qui nous permettra d'inflater les vues pour chaque elemen a afficher
    LayoutInflater inflater;
    // la liste des element a afficher
    ArrayList<Notification> listNotif;

    public AdapterNotification(LayoutInflater inflater, ArrayList<Notification> listNotif) {
        this.inflater = inflater;
        this.listNotif = listNotif;
    }

    @NonNull
    @Override
    public NotifViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.card_notification,viewGroup,false);
        return new NotifViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotifViewHolder notifViewHolder, int i) {
        Notification n = listNotif.get(i);
        //on met a jour les information de la vue a afficher
//        notifViewHolder.societe.setText(n.getSociete());
//        notifViewHolder.poste.setText(n.getPoste());
//        notifViewHolder.ville.setText(n.getVille());
//        notifViewHolder.dateV.setText("ma ville");
    }

    @Override
    public int getItemCount() {
        return listNotif.size();
    }

}
