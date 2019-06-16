package com.example.jolvalre.beworker.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jolvalre.beworker.adapter.AdapterOffreCategorie;
import com.example.jolvalre.beworker.entities.MyCategorie;
import com.example.jolvalre.beworker.entities.Offre;
import com.example.jolvalre.beworker.entities.OffreCategorie;
import com.example.jolvalre.beworker.R;
import com.example.jolvalre.beworker.network.RetrofitInstance;
import com.example.jolvalre.beworker.service.BeworkerService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {



    RecyclerView rv;
    private ArrayList<OffreCategorie> dataOC;

    public HomeFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container,false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //la liste des categorie
        ArrayList<OffreCategorie> dataOC = new ArrayList<OffreCategorie>();
        //on initialise la liste
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv =(RecyclerView) view.findViewById(R.id.recycle_view_fragment_home);
        rv.setLayoutManager(layoutManager);
        //on ajoute a la liste son adapter
        rv.setAdapter(new AdapterOffreCategorie(LayoutInflater.from(getContext()), dataOC));
        loadingData();
        View iv = view.findViewById(R.id.iv_refresh_online);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingData();
                hideRefresh();
            }
        });
    }

    public void loadingData(){
        showWaiting(true);
        dataOC = new ArrayList<OffreCategorie>();
        BeworkerService service = RetrofitInstance.getRetrofitInstanceOffre().create(BeworkerService.class);
        Call<ArrayList<MyCategorie>> call = service.listCategories();
        call.enqueue(new Callback<ArrayList<MyCategorie>>() {
            @Override
            public void onResponse(Call<ArrayList<MyCategorie>> call, Response<ArrayList<MyCategorie>> response) {
                showWaiting(false);
                if (response.code()!= 200){
                    showRefresh();
                    return;
                }

                ArrayList<MyCategorie> list = response.body();
                if(list != null) for (MyCategorie c: list ){

                    OffreCategorie oc =new OffreCategorie();
                    oc.id = c.getId_categorie();
                    oc.setTitre(c.getCategorie());
                    dataOC.add(oc);
                }

                ( (AdapterOffreCategorie)rv.getAdapter() ).updateData(dataOC);
            }

            @Override
            public void onFailure(Call<ArrayList<MyCategorie>> call, Throwable t) {
                //TODO: EN_CAS_DEcHEC_1 lorsaue le chargement des categorie echoue
                showRefresh();
                showWaiting(false);
            }
        });
    }

    public void showRefresh(){
        Activity a = getActivity();
        View refresh=null;
        if (a!= null)refresh= a.findViewById(R.id.refresh_online);
        if (refresh!= null)refresh.setVisibility(View.VISIBLE);
    }

    public void hideRefresh(){
        View refresh = getActivity().findViewById(R.id.refresh_online);
        refresh.setVisibility(View.GONE);
    }

    public void showWaiting(boolean visible){
        Activity a = getActivity();
        View refresh =null;
        if (a!=null) refresh = getActivity().findViewById(R.id.waiting_online);
        if (refresh!=null)refresh.setVisibility(visible? View.VISIBLE: View.GONE) ;
    }

}
