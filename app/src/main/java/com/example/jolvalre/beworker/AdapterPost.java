package com.example.jolvalre.beworker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * cette classe a pour but de d'afficher les offres au quelles l'utilusateur a postule
 * lorsque celui ci est dans l'option post
 * **/
public class AdapterPost extends BaseAdapter {

    //le layoutInflater
    LayoutInflater inflater;
    //La liste des elements a affiches
    ArrayList<Offre> list ;

    public AdapterPost(LayoutInflater inflater, ArrayList<Offre> list) {
        this.inflater = inflater;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderOffre holder;
        if (convertView==null){
            convertView = inflater.inflate(R.layout.card_offre,null);
            holder = new ViewHolderOffre();
            holder.vTitre = (TextView)convertView.findViewById(R.id.text_poste_card_offre);
            holder.vDescription = (TextView)convertView.findViewById(R.id.text_descript_card_offre);
            holder.vDate = (TextView)convertView.findViewById(R.id.text_date_card_offre);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolderOffre)convertView.getTag();
        }
        //on met ajour les information de la carte
//        Offre o = (Offre)getItem(position);
//        holder.vTitre.setText(o.getPoste());
//        holder.vDescription.setText(o.getDescription());
//        holder.vDate.setText(o.getDate());
        return convertView;
    }

    static class ViewHolderOffre{
        TextView vTitre;
        TextView vDescription;
        TextView vDate;
    }

}
