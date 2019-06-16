package com.example.jolvalre.beworker;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.jolvalre.beworker.adapter.AdapterOffreH;
import com.example.jolvalre.beworker.entities.Offre;
import com.example.jolvalre.beworker.fragment.SignInFragment;
import com.example.jolvalre.beworker.network.RetrofitInstance;
import com.example.jolvalre.beworker.service.BeworkerService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchableActivity extends AppCompatActivity {

    public static final String QUERY= "MY_QUERY_BW";

    RecyclerView rv;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);
        rv= findViewById(R.id.rv_search_result);
        tv= findViewById(R.id.tv_no_match_found);
        rv.setLayoutManager(new GridLayoutManager(this,2));
        Intent intent = getIntent();

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(QUERY);
            System.out.println("=====> +"+ query);
            doMySearch(query);
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
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                System.out.println("======> "+s);
                if (s == null || s== "") return false;
                doMySearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
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

    public void doMySearch(String query){

        BeworkerService service = RetrofitInstance.getRetrofitInstanceOffre().create(BeworkerService.class);
        Call<ArrayList<Offre>> call = service.searchOffre(query);
        final SignInFragment anim = new SignInFragment();
        anim.show(getSupportFragmentManager(), "Anim");
        call.enqueue(new Callback<ArrayList<Offre>>() {
            @Override
            public void onResponse(Call<ArrayList<Offre>> call, Response<ArrayList<Offre>> response) {

                if (response.code()==200){
                    AdapterOffreH adapter= new AdapterOffreH(response.body(),
                            LayoutInflater.from(SearchableActivity.this));
                    rv.setAdapter(adapter);
                    show(true);
                    anim.dismiss();

                }else {
                    show(false);
                    anim.dismiss();
                    System.out.println("REsponse code \n\t\t"+response.code());
                    System.out.println(call.request());
                    AlertDialog.Builder builder = new AlertDialog.Builder(SearchableActivity.this);
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
            public void onFailure(Call<ArrayList<Offre>> call, Throwable t) {
                show(false);
                AlertDialog.Builder builder = new AlertDialog.Builder(SearchableActivity.this);
                System.out.println(call.request());
                builder.setTitle(R.string.title_error_onfailure);
                builder.setMessage(R.string.message_onfailure);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
                anim.dismiss();
            }
        });

    }

    public void show(boolean s){
        tv.setVisibility(s? View.GONE: View.VISIBLE);
        rv.setVisibility(s? View.VISIBLE: View.GONE);
    }

    public void toback(View view) {
        this.finish();
    }
}
