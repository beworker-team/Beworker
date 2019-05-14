package com.example.jolvalre.beworker.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.jolvalre.beworker.R;
import com.example.jolvalre.beworker.entities.ExprPro;

import java.util.ArrayList;

public class ExperienceFragment extends Fragment {

    LinearLayout listExpr;

    public ArrayList<ExprPro> dataExpr = new ArrayList<ExprPro>();

    public ExperienceFragment() {
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
        return inflater.inflate(R.layout.fragment_experience, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listExpr = view.findViewById(R.id.ll_list_expr);
        View first = listExpr.getChildAt(0);
        view.findViewById(R.id.iv_del_expr).setVisibility(View.GONE);

        ImageView add = view.findViewById(R.id.ib_add_expr);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View f = inflater.inflate(R.layout.experience,null);
                ImageView iv = f.findViewById(R.id.iv_del_expr);
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listExpr.removeViewAt(listExpr.getChildCount() - 1);
                    }
                });
                listExpr.addView(f);
            }
        });
    }

    public ArrayList<ExprPro> retriveData(){
        dataExpr = new ArrayList<ExprPro>();
        for (int i =0; i<listExpr.getChildCount(); i++){
            View v = listExpr.getChildAt(i);
            EditText debut = v.findViewById(R.id.et_debut_expr);
            EditText fin = v.findViewById(R.id.et_fin_expr);
            CheckBox checkBox = v.findViewById(R.id.cb_present_expr);
            String finish;
            if (checkBox.isChecked()) finish = "present"; else finish = fin.getText().toString();
            EditText poste = v.findViewById(R.id.et_poste_expr);
            EditText institu = v.findViewById(R.id.et_instittution_expr);
            EditText t1 = v.findViewById(R.id.et_tache1_expr);
            EditText t2 = v.findViewById(R.id.et_tache2_expr);
            EditText t3 = v.findViewById(R.id.et_tache3_expr);
            ArrayList<String> taches = new ArrayList<String>();
            if (!t1.getText().toString().equals(""))taches.add(t1.getText().toString());
            if (!t2.getText().toString().equals(""))taches.add(t2.getText().toString());
            if (!t3.getText().toString().equals(""))taches.add(t3.getText().toString());

            dataExpr.add(new ExprPro(debut.getText().toString(),finish, poste.getText().toString(),
                    institu.getText().toString(),taches));
        }

        return dataExpr;
    }

}
