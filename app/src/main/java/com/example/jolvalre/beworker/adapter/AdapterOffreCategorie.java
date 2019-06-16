package com.example.jolvalre.beworker.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jolvalre.beworker.entities.Offre;
import com.example.jolvalre.beworker.entities.OffreCategorie;
import com.example.jolvalre.beworker.R;
import com.example.jolvalre.beworker.network.RetrofitInstance;
import com.example.jolvalre.beworker.service.BeworkerService;
import com.example.jolvalre.beworker.viewholder.CatOffreViewHolder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterOffreCategorie extends RecyclerView.Adapter<CatOffreViewHolder> {

    //la variable qui nous permettra d'inflater nous vue
    LayoutInflater inflater;
    //la liste des elements a afficher
    ArrayList<OffreCategorie> list = null;

    public AdapterOffreCategorie(LayoutInflater inflater, ArrayList<OffreCategorie> list) {
        this.inflater = inflater;
        this.list = list;
    }

    public void updateData(ArrayList<OffreCategorie> data){
        list = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CatOffreViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View co = inflater.inflate(R.layout.categorie_offre,viewGroup,false);
        return new CatOffreViewHolder(co);
    }

    @Override
    public void onBindViewHolder(@NonNull final CatOffreViewHolder catOffreViewHolder, int i) {
        //on recupere l'offre a afficher
        final OffreCategorie oc = list.get(i);
        catOffreViewHolder.text.setText(oc.getTitre());
        catOffreViewHolder.text.setTextColor(R.color.colorPdfBlue);
        //le layoutmanager permet d'afficher e recycleview a l'horizontal
        LinearLayoutManager layoutManager = new LinearLayoutManager(catOffreViewHolder.text.getContext());
        //on definit son orientation
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        catOffreViewHolder.recyclerView.setLayoutManager(layoutManager);
        //on recupere les offres de notre categorie
        BeworkerService service = RetrofitInstance.getRetrofitInstanceOffre().create(BeworkerService.class);
        ArrayList<Offre> data =new ArrayList<Offre>();
        Call<ArrayList<Offre>> call = service.offreCategorie(oc.id);
        call.enqueue(new Callback<ArrayList<Offre>>() {
            @Override
            public void onResponse(Call<ArrayList<Offre>> call, Response<ArrayList<Offre>> response) {
                if (response.code()==200){
                    ArrayList<Offre> list = response.body();
                    ((AdapterOffreH) catOffreViewHolder.recyclerView.getAdapter() ).updateData(list);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Offre>> call, Throwable t) {

            }
        });
        catOffreViewHolder.recyclerView.setAdapter(new AdapterOffreH(data,inflater));

    }

    @Override
    public int getItemCount() {
        if (list==null) return 0;
        return list.size();
    }

}
