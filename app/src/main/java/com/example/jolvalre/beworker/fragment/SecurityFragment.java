package com.example.jolvalre.beworker.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.jolvalre.beworker.R;

import static com.example.jolvalre.beworker.InscriptionActivity.viewPager;

public class SecurityFragment extends Fragment {
    ImageButton suivant, precendant;
    ConstraintLayout constraintLayout;

    public SecurityFragment() {

    }
    public static EditText password;
    public static EditText confirm_password;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_securisezvous, container, false);
        //autre chose
        password = view.findViewById(R.id.password);
        confirm_password = view.findViewById(R.id.confirme_password);
        suivant= view.findViewById(R.id.suivant5);
        precendant= view.findViewById(R.id.precedant5);
        constraintLayout = view.findViewById(R.id.constraintlayout);
        precendant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
            }
        });
        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!password.getText().toString().equals(confirm_password.getText().toString())) {
                    Snackbar.make(constraintLayout, "Veillez resaisir le mot de passe", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    password.setText(null);
                    confirm_password.setText(null);
                    password.setText(null);
                    confirm_password.setText(null);

                }
                else if((password.getText().toString().length()<4 ) || ((password.getText()==null||password.getText().toString().equals("")))){
                    Snackbar.make(constraintLayout, "Le mot de passe dit contenir au moins 4 caracteres", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    password.setText(null);
                    confirm_password.setText(null);
                    password.setText(null);
                    confirm_password.setText(null);
                }
                else {

                        Snackbar.make(constraintLayout, "Inscription effectue avec succes", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }


        });
        return view;
    }
}
