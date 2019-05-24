package com.example.jolvalre.beworker.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jolvalre.beworker.adapter.AdapterNotification;
import com.example.jolvalre.beworker.entities.Notification;
import com.example.jolvalre.beworker.R;

import java.util.ArrayList;

/**
 * Cette classe est utilise pour gere le View des notification
 * */
public class NotificationFragment extends Fragment {

    //la liste de notification a afficher
    ArrayList<Notification> listNotif = new ArrayList<Notification>();

    public NotificationFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notification, container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView rv = (RecyclerView)view.findViewById(R.id.recycle_view_fragment_notication);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(new AdapterNotification(LayoutInflater.from(getContext()),listNotif));
    }
}
