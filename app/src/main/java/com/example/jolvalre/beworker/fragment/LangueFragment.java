package com.example.jolvalre.beworker.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.jolvalre.beworker.R;
import com.example.jolvalre.beworker.entities.Langue;

import java.util.ArrayList;

public class LangueFragment extends Fragment {

    LinearLayout listLang;

    ArrayList<Langue> dataLang = new ArrayList<Langue>();

    public LangueFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_langue, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listLang = view.findViewById(R.id.ll_list_lang);
        View first = listLang.getChildAt(0);
        view.findViewById(R.id.iv_del_langue).setVisibility(View.GONE);

        ImageView add = view.findViewById(R.id.ib_add_lang);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("YESSS","Ok...Yes");
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View l = inflater.inflate(R.layout.langue,null);
                ImageView iv = l.findViewById(R.id.iv_del_langue);
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listLang.removeViewAt(listLang.getChildCount() - 1);
                    }
                });
                listLang.addView(l);
            }
        });

    }

    public ArrayList<Langue> retriveData(){
        dataLang = new ArrayList<Langue>();
        for (int i =0; i<listLang.getChildCount(); i++){
            View v = listLang.getChildAt(i);
            EditText nom = v.findViewById(R.id.et_langue);
            EditText niveau = v.findViewById(R.id.et_niveau_langue);
            dataLang.add(new Langue(nom.getText().toString() , niveau.getText().toString()));
        }

        return dataLang;
    }

}
