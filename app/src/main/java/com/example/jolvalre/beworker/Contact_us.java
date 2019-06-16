package com.example.jolvalre.beworker;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Contact_us extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
    }

    public void facebook_clic(View view) {
        Intent sendIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.link_facebook)));
        startActivity(sendIntent);
    }

    public void twitter_clic(View view) {
        Intent sendIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.link_twitter)));
//        startActivity(sendIntent);
    }

    public void gmail_clic(View view) {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        String to = "beworkerteam@gmail.com";
        sendIntent.setType("message/rfc822");
        sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Remarque");
        startActivity(Intent.createChooser(sendIntent, "Envoyer Email"));
    }

    public void whatsapp_clic(View view) {
        Intent sendIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.link_whatsapp) ));
        startActivity(sendIntent);
    }

    public void toback(View view){
        this.finish();
    }
}
