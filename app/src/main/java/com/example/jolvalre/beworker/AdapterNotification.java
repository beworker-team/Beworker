package com.example.jolvalre.beworker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterNotification extends BaseAdapter {

    //l'inflater qui nous permettra d'inflater les vues pour chaque elemen a afficher
    LayoutInflater inflater;
    // la liste des element a afficher
    ArrayList<Notification> listNotif;

    public AdapterNotification(LayoutInflater inflater, ArrayList<Notification> listNotif) {
        this.inflater = inflater;
        this.listNotif = listNotif;
    }

    @Override
    public int getCount() {
        return listNotif.size();
    }

    /**
     * retourne l'element situe a la position passe en parametre
     * */
    @Override
    public Object getItem(int position) {
        return listNotif.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderNotif holder;
        if (convertView==null){
            holder = new ViewHolderNotif();
            convertView = inflater.inflate(R.layout.card_notification,null);
            holder.societe = (TextView)convertView.findViewById(R.id.text_societe_card_notif);
            holder.poste = (TextView)convertView.findViewById(R.id.text_post_card_notif);
            holder.ville = (TextView)convertView.findViewById(R.id.text_ville_card_notif);
            holder.dateV = (TextView)convertView.findViewById(R.id.text_date_card_notif);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolderNotif)convertView.getTag();
        }
        Notification notif =(Notification) getItem(position);
        //on met a jour les information de la vue a afficher
//        holder.societe.setText(notif.getSociete());
//        holder.poste.setText(notif.getPoste());
//        holder.ville.setText(notif.getVille());
//        holder.dateV.setText(notif.getDate());
        return convertView;
    }

    static class ViewHolderNotif{
        TextView societe;
        TextView poste;
        TextView ville;
        TextView dateV;
    }

}
