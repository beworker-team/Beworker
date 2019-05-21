package com.example.jolvalre.beworker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.jolvalre.beworker.entities.Offre;

public class OffreActivity extends AppCompatActivity {

    Offre offre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offre);
    }

    public void arrawBack(View v){
        this.finish();
    }
}
