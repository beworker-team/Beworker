package com.example.jolvalre.beworker;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.TabLayoutOnPageChangeListener;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.TextView;
import com.example.jolvalre.beworker.adapter.AdapterOffreCategorie;
import com.example.jolvalre.beworker.adapter.PagerAdapter;
import com.example.jolvalre.beworker.entities.Chercheur;
import com.example.jolvalre.beworker.entities.MyCategorie;
import com.example.jolvalre.beworker.entities.Offre;
import com.example.jolvalre.beworker.entities.OffreCategorie;
import com.example.jolvalre.beworker.fragment.SignInFragment;
import com.example.jolvalre.beworker.network.RetrofitInstance;
import com.example.jolvalre.beworker.service.BeworkerService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    public static String ONLINE_MODE = "ONLINE_MODE";

    public static String DEFAULT_ID = "-1";

    public static String MY_PREFS = "preferences";
    //l'offre dont on souhaite voir le contenue
    public static Offre OFFRE_TO_OPEN =null;

    public static  Context MY_CONTEXT;

    /**
     * La variable viewpager fait référence au layout qui va contenir les differents
     * fragments de notre activité
     * La variable tablayout fait référence au layout qui nous permet de navigeuer entre:
     *  -home
     *  -profil
     *  -...etc
     * */
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Intent login, inscription;

    /**
     * cette variable permet de savoir si le delai d'appui sur la touche retour est inferieur a 2s
     * */
    private long last_press= -1;

    public static String id = "";
    /*
    offlineRV le recycleView de la page d' acceuil non connecter il permettra d'afficher les offres*/
    private RecyclerView offlineRV = null;
    private ArrayList<OffreCategorie> dataOC;

    //Cette fontion sera appeler a chaque fois qu'on voudra ouvrir une offre
    public static void openOffre(Offre offre){
        OFFRE_TO_OPEN = offre;
        Intent intent = new Intent(MainActivity.MY_CONTEXT, OffreActivity.class);
        MainActivity.MY_CONTEXT.startActivity(intent);
    }

    //Cette fontion sera appeler a chaque fois qu'on voudra ouvrir une offre
    public static void openOffre(int id){
        Intent intent = new Intent(MainActivity.MY_CONTEXT, OffreActivity.class);
        MainActivity.MY_CONTEXT.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //on specifie le context de l'activite
        MainActivity.MY_CONTEXT = this;
        Intent inten = getIntent();
        String msg =  inten.getStringExtra(MainActivity.ONLINE_MODE);

        id = PreferenceManager.getDefaultSharedPreferences(MainActivity.this).getString(Chercheur.ID, DEFAULT_ID);

        if(msg != null){
            if(msg.equals("ON")){
                setContentView(R.layout.home_page_online);
                setOnlineMode();
            }else{
                setContentView(R.layout.home_page_offline);
                setOffLineMode();
            }

        }else{
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
            if (DEFAULT_ID.equals( id ) ){
                setContentView(R.layout.home_page_offline);
                setOffLineMode();
            }else{
                setContentView(R.layout.home_page_online);
                setOnlineMode();
            }

        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //on met a jour les info de la page de navigation
        if (!id.equals(DEFAULT_ID))setDataUser();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            long time = System.currentTimeMillis();
            if (last_press !=-1 && (time - last_press)<2000){
                super.onBackPressed();
            }else{
                last_press =time;
                Toast.makeText(getApplicationContext(), "Appuyer de nouveau pour quitter",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
        //searchView.setSubmitButtonEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_view) {
            // Handle the camera action
        } else if (id == R.id.nav_log_in) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_sign_in){
            Intent intent = new Intent(this, InscriptionActivity.class);
            startActivity(intent);
        }else if (id==R.id.nav_sign_out){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.title_deconnexion);
            builder.setMessage(R.string.message_ask_deconnxion);
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                    BeworkerService service = RetrofitInstance.getRetrofitInstance().create(BeworkerService.class);
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                    Call<Integer> call = service.deconnexion(preferences.getString(Chercheur.EMAIL, ""));
                    call.enqueue(new Callback<Integer>() {
                        @Override
                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                            if(response.code() == 200){
                                System.out.println(response.toString());
                                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                                intent.putExtra(MainActivity.ONLINE_MODE, "OFF");
                                startActivity(intent);
                                MainActivity.this.finish();
                            }else{
                                //TODO:ACtion a effectuer lorsqu'un probleme survient
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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
                        }

                        @Override
                        public void onFailure(Call<Integer> call, Throwable t) {
                            //TODO: completerles action a effectuer lorsqu'une erreur survient
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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
            builder.setNegativeButton("NON", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        }else if (id== R.id.nav_create_cv){
            Intent intent = new Intent(this, MakeCvActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setOffLineMode(){
        offlineRV = (RecyclerView)findViewById(R.id.recycle_view_off_line_mode);
        //la liste des categorie


        offlineRV.setAdapter(new AdapterOffreCategorie(LayoutInflater.from(MainActivity.this), dataOC));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //RecyclerView rv =(RecyclerView) view.findViewById(R.id.recycle_view_fragment_home);
        offlineRV.setLayoutManager(layoutManager);
        loadingData();
        resetDataUser();

    }

    private void setOnlineMode(){
        // on recupere notre viewpager et notre tablayout
        tabLayout = (TabLayout)findViewById(R.id.tab_layout_main);
        viewPager = (ViewPager)findViewById(R.id.view_pager_main);

        //on declare notre pagerAdapter
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayoutOnPageChangeListener(tabLayout));

        //on lie le tabLayout au viewPager
        tabLayout.setupWithViewPager(viewPager);

        //on recupere les icones des tabs
        int[] icon={R.drawable.ic_home_black_24dp,//l'icone pour le home
                R.drawable.ic_favorite_black_24dp,//l'icone pour les offres postuler
                R.drawable.ic_notifications_black_24dp,//l'icone des notificatioins
                R.drawable.ic_account_circle_black_24dp};//l'icone du profile
        //on itere tous les tabs et on leur associe leurs icones
        for(int i=0; i<icon.length;i++){
            tabLayout.getTabAt(i).setIcon(icon[i]);
        }

    }

    private void resetDataUser(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.edit().putString(Chercheur.ID, DEFAULT_ID).apply();
        NavigationView navigationView =(NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        TextView nom =(TextView) header.findViewById(R.id.tv_nav_header_name_user);
        TextView email =(TextView) header.findViewById(R.id.tv_nav_header_email_id_user);

        nom.setText(R.string.nav_header_title);
        email.setText(R.string.nav_header_subtitle);
    }

    private void setDataUser(){

        NavigationView navigationView =(NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        TextView nom =(TextView) header.findViewById(R.id.tv_nav_header_name_user);
        TextView email =(TextView) header.findViewById(R.id.tv_nav_header_email_id_user);
        if (preferences.getString(Chercheur.ID, "").equals(DEFAULT_ID)){
            nom.setText(R.string.nav_header_title );
            email.setText(R.string.nav_header_subtitle );
        }else{
            nom.setText(preferences.getString(Chercheur.NOM, "") + " " + preferences.getString(Chercheur.PRENOM, "") );
            email.setText(preferences.getString(Chercheur.EMAIL, "") );
        }

    }

    public void refreshOffLine(View view) {
        View refresh = findViewById(R.id.refresh_offline);
        refresh.setVisibility(View.GONE);
        loadingData();

    }

    public void loadingData(){
        showWaiting(true);
        dataOC = new ArrayList<OffreCategorie>();
        BeworkerService service = RetrofitInstance.getRetrofitInstance().create(BeworkerService.class);
        Call<ArrayList<MyCategorie>> call = service.listCategories();
        call.enqueue(new Callback<ArrayList<MyCategorie>>() {
            @Override
            public void onResponse(Call<ArrayList<MyCategorie>> call, Response<ArrayList<MyCategorie>> response) {
                showWaiting(false);
                if (response.code()!= 200){
                    showRefresh();
                    return;
                }

                ArrayList<MyCategorie> list = response.body();
                if(list != null) for (MyCategorie c: list ){

                    OffreCategorie oc =new OffreCategorie();
                    oc.id = c.getId_categorie();
                    oc.setTitre(c.getCategorie());
                    dataOC.add(oc);
                }

                Toast.makeText(getBaseContext(),"yesss", Toast.LENGTH_LONG).show();
                ( (AdapterOffreCategorie)offlineRV.getAdapter() ).updateData(dataOC);
            }

            @Override
            public void onFailure(Call<ArrayList<MyCategorie>> call, Throwable t) {
                //TODO: EN_CAS_DEcHEC_1 lorsaue le chargement des categorie echoue
                showRefresh();
                showWaiting(false);
            }
        });
    }

    public void showRefresh(){
        View refresh = findViewById(R.id.refresh_offline);
        refresh.setVisibility(View.VISIBLE);
    }

    public void showWaiting(boolean visible){
        View refresh = findViewById(R.id.waitin_offline);
        refresh.setVisibility(visible? View.VISIBLE: View.GONE) ;
    }

    @SuppressLint("ValidFragment")
    public static class ReponseFragment extends DialogFragment {
        private Button bouton_oui;
        private Button bouton_non;

        MainActivity activity;

        @SuppressLint("ValidFragment")
        public ReponseFragment(MainActivity activity) {
            this.activity = activity;
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            Dialog dialog = super.onCreateDialog(savedInstanceState);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            return dialog;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.verifie_deconnexion, container, false);
            return view;
        }

        @Override
        public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            bouton_oui = view.findViewById(R.id.bouton_oui);
            bouton_non = view.findViewById(R.id.bouton_non);
            bouton_oui.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    view.findViewById(R.id.rl_ask_deconnexion).setVisibility(View.GONE);
                    view.findViewById(R.id.rl_show_progress_deconnexion).setVisibility(View.VISIBLE);
                    BeworkerService service = RetrofitInstance.getRetrofitInstance().create(BeworkerService.class);
                    Call<Integer> call = service.deconnexion(MainActivity.id);
                    call.enqueue(new Callback<Integer>() {
                        @Override
                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                            System.out.println("response code deconnexion "+response.code());
                            if(response.code() == 200){
                                System.out.println(response.toString());
                                Intent intent = new Intent(getContext(), MainActivity.class);
                                intent.putExtra(MainActivity.ONLINE_MODE, "OFF");
                                dismiss();
                                startActivity(intent);
                                activity.finish();
                            }else{
                                dismiss();
                                System.out.println("yo  "+response.toString());
                                //TODO:ACtion a effectuer lorsqu'un probleme survient
                            }
                        }

                        @Override
                        public void onFailure(Call<Integer> call, Throwable t) {
                            System.out.println("yoyyo   ");
                            dismiss();
                            //TODO: completerles action a effectuer lorsqu'une erreur survient
                        }
                    });

                }
            });
            bouton_non.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
    }

}
