package com.example.jolvalre.beworker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.jolvalre.beworker.entities.Offre;

public class OffreActivity extends AppCompatActivity {

    Offre offre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offre);
        Offre o = MainActivity.OFFRE_TO_OPEN;
        TextView poste = (TextView)findViewById(R.id.tv_post_offre_activity);
        TextView description = (TextView)findViewById(R.id.tv_description_offre_activity);
        TextView ville = (TextView)findViewById(R.id.tv_ville_offre_activity);
        TextView date = (TextView)findViewById(R.id.tv_date_offre_activity);

        poste.setText(o.getPoste());
        description.setText(o.getDescription());
        ville.setText(o.getVille());
        date.setText( "le " + o.getDate().split("T")[0]);
    }

    public void arrawBack(View v){
        this.finish();
    }
}
