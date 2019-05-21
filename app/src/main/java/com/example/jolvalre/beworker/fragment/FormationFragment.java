package com.example.jolvalre.beworker.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.jolvalre.beworker.R;
import com.example.jolvalre.beworker.entities.Formation;

import java.util.ArrayList;

public class FormationFragment extends Fragment {

    LinearLayout listForm;

    public ArrayList<Formation> dataForm = new ArrayList<Formation>();

    public FormationFragment() {
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
        return inflater.inflate(R.layout.fragment_formation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listForm = (LinearLayout)view.findViewById(R.id.ll_list_formation);
        View first = listForm.getChildAt(0);
        view.findViewById(R.id.iv_del_formation).setVisibility(View.GONE);
        ImageView add = view.findViewById(R.id.ib_add_formation);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View f = inflater.inflate(R.layout.formation,null);
                ImageView iv = f.findViewById(R.id.iv_del_formation);
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listForm.removeViewAt(listForm.getChildCount() - 1);
                    }
                });
                listForm.addView(f);
            }
        });

    }

    public ArrayList<Formation> retriveData(){
        dataForm = new ArrayList<Formation>();
        for (int i =0; i<listForm.getChildCount(); i++){
            View v = listForm.getChildAt(i);
            EditText debut = v.findViewById(R.id.et_debut_formation);
            EditText fin = v.findViewById(R.id.et_fin_formation);
            EditText nom = v.findViewById(R.id.et_nom_formation);
            EditText institu = v.findViewById(R.id.et_instution_formation);
            dataForm.add(new Formation(debut.getText().toString() + " - "+ fin.getText().toString(),
                    nom.getText().toString(),
                    institu.getText().toString()));
        }

        return dataForm;
    }

}
