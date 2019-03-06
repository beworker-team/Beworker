package com.example.jolvalre.beworker;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterOffreCategorie extends BaseAdapter {

    //la variable qui nous permettra d'inflater nous vue
    LayoutInflater inflater;
    //la liste des elements a afficher
    ArrayList<OffreCategorie> list ;

    public AdapterOffreCategorie(LayoutInflater inflater, ArrayList<OffreCategorie> list) {
        this.inflater = inflater;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    /**
     * Recupere un item de la liste en fonction de sa position
     * */
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    /**
     * Recupere un item de la liste en fonction de son id
     * elle est generalement utiliser dans le cas d'une base de donnees
     * */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * permet de creer la vue de l'item qui sera passe en parametre
     * */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //on declare notre ViewHolder
        ViewHolderOffreC viewHolder=null;

        if (convertView == null){
            //Si la vue n'est pas recycler on recuper la vue
            viewHolder= new ViewHolderOffreC();
            convertView =inflater.inflate(R.layout.categorie_offre,null);
            viewHolder.text = (TextView) convertView.findViewById(R.id.text_titre_categorie_offre);
            viewHolder.recyclerView = (RecyclerView)convertView.findViewById(R.id.recycle_view_categorie_offre);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolderOffreC) convertView.getTag();
        }
        OffreCategorie oc = (OffreCategorie)getItem(position);
        viewHolder.text.setText(oc.getTitre());
        //le layoutmanager permet d'afficher e recycleview a l'horizontal
        LinearLayoutManager layoutManager = new LinearLayoutManager(convertView.getContext());
        //on definit son orientation
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        viewHolder.recyclerView.setLayoutManager(layoutManager);
        viewHolder.recyclerView.setAdapter(new AdapterOffreH(oc.getData(),inflater));
        return convertView;
    }

    /**
     * classe utiliser pour ameliorer le fonctionnement de notre adapter
     * */
    static class ViewHolderOffreC{
        TextView text;
        RecyclerView recyclerView;
    }
}
