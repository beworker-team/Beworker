package com.example.jolvalre.beworker.fragment;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.jolvalre.beworker.R;
import com.example.jolvalre.beworker.entities.Chercheur;
import com.example.jolvalre.beworker.entities.ChercheurV2;
import com.example.jolvalre.beworker.network.RetrofitInstance;
import com.example.jolvalre.beworker.service.BeworkerService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilFragment extends Fragment {

    private ChercheurV2 user = new ChercheurV2();

    //on recupre les textview 
    TextView nom ;
    TextView age ;
    TextView domaine ;
    TextView city ;
    TextView tel ;
    TextView email ;
    TextView adresse ;
    TextView statut;
    TextView genre ;
    TextView pw ;

    //on recupere les images a d'edit
    View ed_name ;
    View ed_contact ;
    View ed_supp ;
    View ed_pw ;

    View iv_cancelName;
    View iv_cancelContact;
    View iv_cancelSupp;
    View iv_cancelPw;

    View iv_saveName;
    View iv_saveContact;
    View iv_saveSupp;
    View iv_savePw;

    View ll_v_a_Name = null;
    View ll_v_a_Contact = null;
    View ll_v_a_Supp = null;
    View ll_v_a_Pw = null;

    //on recupre les textview
    EditText edNom ;
    EditText edPrenom ;
    EditText edAge ;
    EditText edDomaine;
    EditText edCity ;
    EditText edTel ;
    EditText edEmail ;
    EditText edAdresse ;
    EditText edStatut;
    RadioGroup rgGenre ;
    EditText edPw ;

    RadioButton m;
    RadioButton f;


    public ProfilFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil,container,false);
        //on affiche les information de l'utilisateur

        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {

        nom = view.findViewById(R.id.tv_show_name_user);
        age = view.findViewById(R.id.tv_show_birth_day_user);
        domaine = view.findViewById(R.id.tv_show_domaine_user);
        city = view.findViewById(R.id.tv_show_city_user);
        tel = view.findViewById(R.id.tv_show_tel_user);
        email = view.findViewById(R.id.tv_show_email_user);
        adresse = view.findViewById(R.id.tv_adresse_user);
        statut = view.findViewById(R.id.tv_statut_user);
        genre = view.findViewById(R.id.tv_genre_user);
        pw = view.findViewById(R.id.tv_pw_email_user);

        edNom = view.findViewById(R.id.et_show_name_user);
        edPrenom = view.findViewById(R.id.et_show_prenom_user);
        edAge = view.findViewById(R.id.et_show_birth_day_user);
        edDomaine = view.findViewById(R.id.et_show_domaine_user);
        edCity = view.findViewById(R.id.et_show_city_user);
        edTel = view.findViewById(R.id.et_show_tel_user);
        edEmail = view.findViewById(R.id.et_show_email_user);
        edAdresse = view.findViewById(R.id.et_adresse_user);
        edStatut = view.findViewById(R.id.et_statut_user);
        rgGenre = view.findViewById(R.id.rg_genre_city_user);
        edPw = view.findViewById(R.id.et_pw_email_user);

        m = view.findViewById(R.id.rb_genre_M_user);
        f = view.findViewById(R.id.rb_genre_F_user);

        ll_v_a_Name = view.findViewById(R.id.ll_v_a_name);
        ll_v_a_Contact = view.findViewById(R.id.ll_v_a_contact);
        ll_v_a_Supp = view.findViewById(R.id.ll_v_a_supp);
        ll_v_a_Pw = view.findViewById(R.id.ll_v_a_pw);

        ed_name = view.findViewById(R.id.iv_edit_name);
        ed_contact = view.findViewById(R.id.iv_edit_contact);
        ed_supp = view.findViewById(R.id.iv_edit_supp);
        ed_pw = view.findViewById(R.id.iv_edit_pw);

        ed_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.GONE);
                showName(false);
                initEdit();
            }
        });
        ed_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.GONE);
                showContact(false);
                initEdit();
            }
        });
        ed_supp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.GONE);
                showSupp(false);
                initEdit();
            }
        });

        iv_cancelContact = view.findViewById(R.id.iv_cancel_contact);
        iv_cancelName = view.findViewById(R.id.iv_cancel_name);
        iv_cancelSupp = view.findViewById(R.id.iv_cancel_supp);
        iv_cancelPw = view.findViewById(R.id.iv_cancel_pw);

        iv_cancelContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_contact.setVisibility(View.VISIBLE);
                showContact(true);
            }
        });
        iv_cancelName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_name.setVisibility(View.VISIBLE);
                showName(true);
            }
        });
        iv_cancelSupp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_supp.setVisibility(View.VISIBLE);
                showSupp(true);
            }
        });

        iv_saveContact = view.findViewById(R.id.iv_save_contact);
        iv_saveName = view.findViewById(R.id.iv_save_name);
        iv_saveSupp = view.findViewById(R.id.iv_save_supp);
        iv_savePw = view.findViewById(R.id.iv_save_pw);

        iv_saveContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stel = edTel.getText().toString();
                String sad = edAdresse.getText().toString();
                String svil = edCity.getText().toString();
                user();
                final SignInFragment sign = new SignInFragment();
                user.setAdresse(sad);
                user.setTelephone(Integer.valueOf(stel));
                user.setVille(svil);
                sign.show(getFragmentManager(), "Animation");
                BeworkerService service = RetrofitInstance.getRetrofitInstanceChercheur().create(BeworkerService.class);
                Call<ChercheurV2> call = service.updateUserInfo(user);
                call.enqueue(new Callback<ChercheurV2>() {
                    @Override
                    public void onResponse(Call<ChercheurV2> call, Response<ChercheurV2> response) {
                        System.out.println("\nUPDATE ====> "+call.request());
                        if (response.code()==200){
                            Chercheur.load(response.body(),getContext());
                            user();
                            showContact(true);
                            sign.dismiss();
                            return;
                        }
                        sign.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle(R.string.title_error_onresponse);
                        builder.setMessage(R.string.message_error_onResponse);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.create().show();

                    }

                    @Override
                    public void onFailure(Call<ChercheurV2> call, Throwable t) {
                        System.out.println("\nUPDATE ====> "+call.request());
                        sign.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle(R.string.title_error_onfailure);
                        builder.setMessage(R.string.message_onfailure);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.create().show();
                    }
                });
            }
        });

        iv_saveName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = edNom.getText().toString();
                String p = edPrenom.getText().toString();
                String a = edAge.getText().toString();
                String d = edDomaine.getText().toString();
                user();
                final SignInFragment sign = new SignInFragment();
                user.setNom(n);
                user.setPrenom(p);
                user.setDate_de_naissance(a);
                user.setDomaine(d);
                sign.show(getFragmentManager(), "Animation");
                BeworkerService service = RetrofitInstance.getRetrofitInstanceChercheur().create(BeworkerService.class);
                Call<ChercheurV2> call = service.updateUserInfo(user);
                call.enqueue(new Callback<ChercheurV2>() {
                    @Override
                    public void onResponse(Call<ChercheurV2> call, Response<ChercheurV2> response) {
                        System.out.println("\nUPDATE ====> "+call.request());
                        if (response.code()==200){
                            Chercheur.load(response.body(),getContext());
                            showInformation();
                            showName(true);
                            sign.dismiss();
                            return;
                        }
                        sign.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle(R.string.title_error_onresponse);
                        builder.setMessage(R.string.message_error_onResponse);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.create().show();

                    }

                    @Override
                    public void onFailure(Call<ChercheurV2> call, Throwable t) {
                        sign.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle(R.string.title_error_onfailure);
                        builder.setMessage(R.string.message_onfailure);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.create().show();
                    }
                });
            }
        });

        iv_saveSupp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = edTel.getText().toString();
                String g ;
                if( rgGenre.getCheckedRadioButtonId()== R.id.rb_genre_M_user ) g = "M"; else g = "F";
                user();
                final SignInFragment sign = new SignInFragment();
                user.setStatut(s);
                user.setGenre(g);
                sign.show(getFragmentManager(), "Animation");
                BeworkerService service = RetrofitInstance.getRetrofitInstanceChercheur().create(BeworkerService.class);
                Call<ChercheurV2> call = service.updateUserInfo(user);
                call.enqueue(new Callback<ChercheurV2>() {
                    @Override
                    public void onResponse(Call<ChercheurV2> call, Response<ChercheurV2> response) {
                        if (response.code()==200){
                            Chercheur.load(response.body(),getContext());
                            showInformation();
                            showSupp(true);
                            sign.dismiss();
                            return;
                        }
                        sign.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle(R.string.title_error_onresponse);
                        builder.setMessage(R.string.message_error_onResponse);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.create().show();

                    }

                    @Override
                    public void onFailure(Call<ChercheurV2> call, Throwable t) {
                        sign.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle(R.string.title_error_onfailure);
                        builder.setMessage(R.string.message_onfailure);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.create().show();
                    }
                });
            }
        });

        showInformation();

    }

    public void user(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        user.setEmail(preferences.getString(Chercheur.EMAIL,""));
        user.setNom(preferences.getString(Chercheur.NOM,""));
        user.setPrenom(preferences.getString(Chercheur.PRENOM,""));
        user.setDate_de_naissance(preferences.getString(Chercheur.BIRTH_DAY,""));
        user.setGenre(preferences.getString(Chercheur.GENRE,""));
        user.setTelephone( Integer.valueOf(preferences.getString(Chercheur.TELEPHONE,"")) );
        user.setVille(preferences.getString(Chercheur.VILLE,""));
        user.setMot_de_passe(preferences.getString(Chercheur.PASSWORD,""));
        user.setDomaine(preferences.getString(Chercheur.DOMAINE,""));
        user.setStatut(preferences.getString(Chercheur.STATUT,""));
        user.setAdresse(preferences.getString(Chercheur.ADRESSE,""));
    }
    
    public void showInformation(){
        user();
        String name = user.getNom() + " " + user.getPrenom();
        nom.setText(name);
        age.setText(user.getDate_de_naissance());
        domaine.setText(user.getDomaine());

        city.setText(user.getVille());
        adresse.setText(user.getAdresse());
        tel.setText(String.valueOf(user.getTelephone()));
        email.setText(user.getEmail());

        statut.setText(user.getStatut());
        genre.setText(user.getGenre());

    }

    public void showName(boolean show){
        nom.setVisibility(show? View.VISIBLE : View.GONE);
        age.setVisibility(show? View.VISIBLE : View.GONE);
        domaine.setVisibility(show? View.VISIBLE : View.GONE);

        edNom.setVisibility(show? View.GONE : View.VISIBLE);
        edPrenom.setVisibility(show? View.GONE : View.VISIBLE);
        edAge.setVisibility(show? View.GONE : View.VISIBLE);
        edDomaine.setVisibility(show? View.GONE : View.VISIBLE);

        ll_v_a_Name.setVisibility(show? View.GONE: View.VISIBLE);
    }

    public void showContact(boolean show){
//        email.setVisibility(show? View.VISIBLE : View.GONE);
        tel.setVisibility(show? View.VISIBLE : View.GONE);
        adresse.setVisibility(show? View.VISIBLE : View.GONE);
        city.setVisibility(show? View.VISIBLE : View.GONE);

//        email.setVisibility(show? View.VISIBLE : View.GONE);
        edTel.setVisibility(!show? View.VISIBLE : View.GONE);
        edAdresse.setVisibility(!show? View.VISIBLE : View.GONE);
        edCity.setVisibility(!show? View.VISIBLE : View.GONE);

        ll_v_a_Contact.setVisibility(show? View.GONE : View.VISIBLE);
    }

    public void showSupp(boolean show){
        statut.setVisibility(show? View.VISIBLE : View.GONE);
        genre.setVisibility(show? View.VISIBLE : View.GONE);

        edStatut.setVisibility(!show? View.VISIBLE : View.GONE);
        rgGenre.setVisibility(!show? View.VISIBLE : View.GONE);

        ll_v_a_Supp.setVisibility(show? View.GONE : View.VISIBLE);

    }

    public  void initEdit(){

        if (user.getGenre().equals("M")) m.setChecked(true); else f.setChecked(false);

        edNom.setText(user.getNom());
        edPrenom.setText(user.getPrenom());
        edAge.setText(user.getDate_de_naissance());
        edDomaine.setText(user.getDomaine());

        edCity.setText(user.getVille());
        edAdresse.setText(user.getAdresse());
        edTel.setText(String.valueOf(user.getTelephone()));
        edEmail.setText(user.getEmail());

        edStatut.setText(user.getStatut());

        statut.setText(user.getStatut());

    }

}
