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

import com.example.jolvalre.beworker.InscriptionActivity;
import com.example.jolvalre.beworker.R;
import com.example.jolvalre.beworker.service.BeworkerService;

import static com.example.jolvalre.beworker.InscriptionActivity.chercheur;
import static com.example.jolvalre.beworker.InscriptionActivity.viewPager;

public class SecurityFragment extends Fragment {
    ImageButton validation_inscription, precendant;
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
        validation_inscription = view.findViewById(R.id.ib_validation_inscription);
        precendant= view.findViewById(R.id.precedant5);
        constraintLayout = view.findViewById(R.id.constraintlayout);
        precendant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
            }
        });
        validation_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!password.getText().toString().equals(confirm_password.getText().toString())) {
                    confirm_password.setError("Ne correspond pas au password");

                }
                else if((password.getText().toString().length()<4 ) || ((password.getText()==null||password.getText().toString().equals("")))){
                    password.setError("password trop court");
                }
                else {
                    //TODO: ternimer l'appel pour le service d'inscrption
                        chercheur.setMot_de_passe(password.getText().toString());
                        InscriptionActivity.goInOnlineMode();

                    }

                }


        });

        return view;
    }
}
