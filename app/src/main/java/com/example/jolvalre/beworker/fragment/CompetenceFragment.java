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
import com.example.jolvalre.beworker.entities.Competence;

import java.util.ArrayList;

public class CompetenceFragment extends Fragment {

    LinearLayout listComp;

    ArrayList<Competence> dataComp = new ArrayList<Competence>();

    public CompetenceFragment() {
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
        return inflater.inflate(R.layout.fragment_competence, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listComp = view.findViewById(R.id.ll_list_competence);
        View first = listComp.getChildAt(0);
        view.findViewById(R.id.iv_del_competence).setVisibility(View.GONE);

        ImageView add = view.findViewById(R.id.ib_add_competence);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View f = inflater.inflate(R.layout.competence,null);
                ImageView iv = f.findViewById(R.id.iv_del_competence);
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listComp.removeViewAt(listComp.getChildCount() - 1);
                    }
                });
                listComp.addView(f);
            }
        });
    }

    public ArrayList<Competence> retriveData(){
        dataComp = new ArrayList<Competence>();
        for (int i =0; i<listComp.getChildCount(); i++){
            View v = listComp.getChildAt(i);
            EditText nom = v.findViewById(R.id.et_competence);
            EditText niveau = v.findViewById(R.id.et_niveau_competence);
            dataComp.add(new Competence(nom.getText().toString() , niveau.getText().toString()));
        }

        return dataComp;
    }

}
