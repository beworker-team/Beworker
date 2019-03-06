package com.example.jolvalre.beworker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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

        ListView listView =(ListView) inflater.inflate(R.layout.fragment_notification, container,false);
        //pour les test on ajoute quelque notification
        for (int i = 0; i<7;i++){
            listNotif.add(new Notification());
        }
        listView.setAdapter(new AdapterNotification(inflater,listNotif));
        return listView;
    }
}
