package com.example.jolvalre.beworker;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.TabLayoutOnPageChangeListener;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.jolvalre.beworker.adapter.AdapterOffreCategorie;
import com.example.jolvalre.beworker.adapter.PagerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    public static String ONLINE_MODE = "ONLINE_MODE";

    /*
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

    /*
    offlineRV le recycleView de la page d' acceuil non connecter il permettra d'afficher les offres*/
    private RecyclerView offlineRV = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent inten = getIntent();
        String msg =  inten.getStringExtra(MainActivity.ONLINE_MODE);

//        if (msg!=null){
//            if (msg.equals("ON")){
//                setContentView(R.layout.home_page_online);
//                setOnlineMode();
//            }else{
//
//            }
//
//        }
        if(msg != null){
            if(msg.equals("ON")){
                System.out.println(">>>>>>>> OK!!!");
                setContentView(R.layout.home_page_online);
                setOnlineMode();
            }else{
                setContentView(R.layout.home_page_offline);
                setOffLineMode();
            }

        }else{
            setContentView(R.layout.home_page_offline);
            setOffLineMode();

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


        login = new Intent(MainActivity.this, LoginActivity.class);

        inscription = new Intent(MainActivity.this, InscriptionActivity.class);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/
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
            startActivity(login);
        }else if (id == R.id.nav_sign_in){
            startActivity(inscription);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setOffLineMode(){
        offlineRV = (RecyclerView)findViewById(R.id.recycle_view_off_line_mode);
        //la liste des categorie
        ArrayList<OffreCategorie> dataOC = new ArrayList<OffreCategorie>();
        //on initialise la liste
        for(int i=0; i<4; i++){
            dataOC.add(new OffreCategorie());
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //RecyclerView rv =(RecyclerView) view.findViewById(R.id.recycle_view_fragment_home);
        offlineRV.setLayoutManager(layoutManager);
        //on ajoute a la liste son adapter
        offlineRV.setAdapter(new AdapterOffreCategorie(LayoutInflater.from(this), dataOC));

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
}
