package com.example.jolvalre.beworker.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import com.example.jolvalre.beworker.R;

import java.util.Calendar;
import java.util.Date;

import static com.example.jolvalre.beworker.InscriptionActivity.chercheur;
import static com.example.jolvalre.beworker.InscriptionActivity.viewPager;

public class CompleterProfilFragment extends Fragment{
    ImageButton suivant, precedant;
    public CompleterProfilFragment() {

    }
    public static EditText dateDeNaissance;
    public static EditText ville;

    public RadioGroup rgSexe;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_completerprofil, container, false);
        //autre chose

        rgSexe = view.findViewById(R.id.rg_sexe);
        dateDeNaissance = view.findViewById(R.id.age);
        ville = view.findViewById(R.id.ville);
        suivant = view.findViewById(R.id.suivant2);
        precedant = view.findViewById(R.id.precedant2);
        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (retriveData()) viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }
        });
        precedant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (retriveData()) viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
            }
        });

        dateDeNaissance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getFragmentManager(),"datePicker");

            }
        });
        return view;
    }

    public boolean retriveData(){
        int sex = rgSexe.getCheckedRadioButtonId();
        if (sex == R.id.rb_sexe_masculin) chercheur.setGenre("M");
        else chercheur.setGenre("F");

        if (TextUtils.isEmpty(dateDeNaissance.getText())){
            dateDeNaissance.setError("Ce champ est obligatoire");
            return false;
        }
        chercheur.setVille(ville.getText().toString());
        chercheur.setDate_de_naissance(dateDeNaissance.getText().toString());
        return true;
    }


    public void showDatePickerDialog(View view){
        DialogFragment dialogFragment = new DatePickerFragment();
        dialogFragment.show(getFragmentManager(),"datePicker");
    }

    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener{

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            final int year = c.get(Calendar.YEAR);
            final int month = c.get(Calendar.MONTH);
            final int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this,year,month,day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String y = year+"";
            String d = "";
            if (dayOfMonth<10) d = "0"+dayOfMonth; else d =dayOfMonth+"";
            String m = "";
            if ( month+1 < 10 ) m = "0"+(month+1); else m =(1+month)+"";
            dateDeNaissance.setText(y+"-"+m+"-"+d);
        }
    }

}
