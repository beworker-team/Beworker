package com.example.jolvalre.beworker;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jolvalre.beworker.database.BeworkerHelper;
import com.example.jolvalre.beworker.database.DatabaseContract;
import com.example.jolvalre.beworker.entities.Chercheur;
import com.example.jolvalre.beworker.entities.Offre;

import static com.example.jolvalre.beworker.MainActivity.DEFAULT_ID;

public class OffreActivity extends AppCompatActivity {

    Offre offre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offre);
        final Offre o = MainActivity.OFFRE_TO_OPEN;
        offre =o;
        TextView poste = (TextView)findViewById(R.id.tv_post_offre_activity);
        TextView description = (TextView)findViewById(R.id.tv_description_offre_activity);
        TextView ville = (TextView)findViewById(R.id.tv_ville_offre_activity);
        TextView date = (TextView)findViewById(R.id.tv_date_offre_activity);
        ImageButton share_offre = (ImageButton) findViewById(R.id.share_offres);

        poste.setText(o.getPoste());
        description.setText(o.getDescription());
        ville.setText(o.getVille());
        date.setText( "le " + o.getDate().split("T")[0]);
        share_offre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,"\tBEWORKER"+"\n"+"\n"+o.getPoste()+"\n"+o.getDescription()
                        +"\n"+ o.getDate()+"\n"+o.getVille());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences.getString(Chercheur.ID, DEFAULT_ID).equals(DEFAULT_ID)) {
            RelativeLayout rl = findViewById(R.id.rl_bottom_offre_activity);
            rl.setVisibility(View.GONE);
        }
    }

    public void arrawBack(View v){
        this.finish();
    }

    public void saveIt(View view) {

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.OffreLocal.COLUMN_ID_CATEGORIE, offre.getId_categorie()+"");
        values.put(DatabaseContract.OffreLocal.COLUMN_ID_EMPLOYEUR, offre.getId_employeur()+"");
        values.put(DatabaseContract.OffreLocal.COLUMN_POSTE, offre.getPoste());
        values.put(DatabaseContract.OffreLocal.COLUMN_DATE_POST, offre.getDate());
        values.put(DatabaseContract.OffreLocal.COLUMN_LAN, offre.getLangue());
        values.put(DatabaseContract.OffreLocal.COLUMN_VILLES, offre.getVille());
        values.put(DatabaseContract.OffreLocal.COLUMN_DESCRIPTION, offre.getDescription());
        this.getContentResolver().insert(DatabaseContract.OffreLocal.CONTENT_URI,values);
        ImageView iv = (ImageView)view;
        iv.setImageResource(R.drawable.ic_favorite_white_24dp);

    }
        public void getAllOffre(){
            Cursor cursor;
            String[] projection = {DatabaseContract.OffreLocal._ID,DatabaseContract.OffreLocal.COLUMN_VILLES,DatabaseContract.OffreLocal.COLUMN_DESCRIPTION,DatabaseContract.OffreLocal.COLUMN_POSTE,DatabaseContract.OffreLocal.COLUMN_DATE_POST};
            cursor = this.getContentResolver().query(DatabaseContract.OffreLocal.CONTENT_URI,projection,null,null,null);

    }
}
