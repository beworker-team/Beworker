package com.example.jolvalre.beworker.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jolvalre.beworker.adapter.AdapterOffreH;
import com.example.jolvalre.beworker.entities.Offre;
import com.example.jolvalre.beworker.R;

import java.util.ArrayList;

public class PostFragment extends Fragment {

    public PostFragment() {
        // neccessite un constructeur vide
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_post,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView =(RecyclerView) view.findViewById(R.id.gridview_fragment_post);
        ArrayList<Offre> data = new ArrayList<Offre>();
        AdapterOffreH adapter= new AdapterOffreH(data,LayoutInflater.from(getContext()));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(adapter);
    }
}
