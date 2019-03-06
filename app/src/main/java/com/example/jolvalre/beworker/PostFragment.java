package com.example.jolvalre.beworker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

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
        GridView grid =(GridView) inflater.inflate(R.layout.fragment_post,container,false);
        ArrayList<Offre> data = new ArrayList<Offre>();
        for(int i = 0; i<10; i++){
            data.add(new Offre());
        }
        AdapterPost adapter= new AdapterPost(inflater, data);
        grid.setAdapter(adapter);
        return grid;
    }
}
