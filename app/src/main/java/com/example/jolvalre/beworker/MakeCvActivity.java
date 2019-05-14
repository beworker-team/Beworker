package com.example.jolvalre.beworker;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.jolvalre.beworker.entities.Competence;
import com.example.jolvalre.beworker.entities.ExprPro;
import com.example.jolvalre.beworker.entities.Formation;
import com.example.jolvalre.beworker.entities.Langue;
import com.example.jolvalre.beworker.fragment.CompetenceFragment;
import com.example.jolvalre.beworker.fragment.DetailsCvFragment;
import com.example.jolvalre.beworker.fragment.ExperienceFragment;
import com.example.jolvalre.beworker.fragment.FormationFragment;
import com.example.jolvalre.beworker.fragment.InterestFragment;
import com.example.jolvalre.beworker.fragment.LangueFragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MakeCvActivity extends AppCompatActivity {

    public static int MAX_VP = 6;

    public static int BLACK = Color.rgb(0,0,0);

    public static String[] SECTION = {"Details","Formations","Experiences Professionnelles",
    "Competences", "Langues", "Centres d'interet"};

    public static String[] Details = {"Adresse","Telephone", "E-mail", "Date de naissance",
        "Nationalite"};

    public int y = 80;
    public int pasCont = 20;
    public int pasRect =5;
    public int xValue=280;
    public int xDefault = 60;

    ImageButton next;
    ImageButton preview;

    ViewPager viewPager;

    PagerAdapter pagerAdapter;

    DetailsCvFragment detailsCvFragment = new DetailsCvFragment();
    FormationFragment formationFragment = new FormationFragment();
    ExperienceFragment experienceFragment = new ExperienceFragment();
    CompetenceFragment competenceFragment = new CompetenceFragment();
    LangueFragment langueFragment = new LangueFragment();
    InterestFragment interestFragment = new InterestFragment();

    LinearLayout saveL;
    LinearLayout printL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_cv);

        viewPager = (ViewPager)findViewById(R.id.vp_make_cv);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i==0)preview.setVisibility(View.GONE);
                else if (i==5){
                    next.setVisibility(View.GONE);
                    printL.setVisibility(View.VISIBLE);
                    saveL.setVisibility(View.VISIBLE);
                } else {
                    preview.setVisibility(View.VISIBLE);
                    next.setVisibility(View.VISIBLE);
                    printL.setVisibility(View.GONE);
                    saveL.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        next = (ImageButton)findViewById(R.id.ib_next_make_cv);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int c =viewPager.getCurrentItem();
                if (c < MAX_VP){
                    viewPager.setCurrentItem(c+1);
                }
            }
        });

        preview = (ImageButton)findViewById(R.id.ib_preview_make_cv);
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int c =viewPager.getCurrentItem();
                if (c > 0){
                    viewPager.setCurrentItem(c-1);
                }
            }
        });

        saveL = findViewById(R.id.ll_save_cv);
        printL = findViewById(R.id.ll_print_cv);

        printL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeHiscv();
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) super.onBackPressed();
        else viewPager.setCurrentItem( viewPager.getCurrentItem()-1 );
    }


    public void arrawBack(View v){
        this.finish();
    }

    /**
     * cette methode est utilse pour generer le cv de la personne au format pdf
     * et n'set disponible qu'appartir de l'api 19
     * */
    public void makeHiscv(){

         y = 80;
         pasCont = 20;
         pasRect =5;
         xValue=280;
         xDefault = 60;

        //on initialise les element a faire pour le document
        PdfDocument pdfDocument = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            pdfDocument = new PdfDocument();
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(542,
                    842,1).create();

            //on cree la page sur laquelle on veut ecrire
            PdfDocument.Page page = pdfDocument.startPage(pageInfo);
            //on recupere le canvas
            Canvas canvas = page.getCanvas();
            Paint paint = new Paint();
            //on se met a ecrire
            //TODO:terminer les information a ecrire dans le pdf
            //ceci est une demo
            //espace de 10 apres chaque secion

            printHeader(detailsCvFragment.retriveData(),canvas,paint);
            y+=10;

            printFormation(formationFragment.retriveData(), canvas,paint);
            y+=10;

            printExprPro(experienceFragment.retriveData(),canvas,paint);
            y+=10;

            printCompetences(competenceFragment.retriveData(),canvas,paint);
            y+=10;

            printLangues(langueFragment.retriveData(),canvas,paint);
            y+=10;

            printCentreInteret(interestFragment.reTriveData(),canvas,paint);

            //on sitipule qu'on a termine
            pdfDocument.finishPage(page);

            String directoriePath = Environment.getExternalStorageDirectory().getPath()+ "/Beworker/doc/";
            File file = new File(directoriePath);
            if(!file.exists()){
                file.mkdirs();
            }

            String targetPdf =directoriePath + "myPdf.pdf";
            File filePath = new File(targetPdf);
            try {
                pdfDocument.writeTo(new FileOutputStream(filePath));
                Toast.makeText(this, "Impression en cours", Toast.LENGTH_LONG).show();
            }catch (IOException e){
                Log.e("ERROR_PRINT", e.toString());
                Toast.makeText(this, "une erreur est survenue lors de l'impression", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(this,"Cette fonctionnalite n'est pas disponible avec votre version", Toast.LENGTH_LONG).show();
        }

    }

    public void printHeader( ArrayList<String> values, Canvas canvas, Paint paint) {

        paint.setColor(BLACK);
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
        paint.setTextSize(14);

        canvas.drawText(values.get(0),270,52,paint);

        paint.setColor(getResources().getColor(R.color.colorPdfBlue));
        canvas.drawText(SECTION[0], xDefault, y, paint);

        drawRect(canvas, paint);


        paint.setTextSize(11);
        paint.setColor(BLACK);
//        on affiche les paires cles valeurs de detailles
        for (int i = 0; i < Details.length; i++) {
            Log.i("VALUE", values.get(i+1));
            canvas.drawText(Details[i], xDefault, y, paint);
            canvas.drawText(values.get(i+1), xValue, y, paint);
            y+=pasCont;
        }

    }

    public void printFormation(ArrayList<Formation> formations, Canvas canvas, Paint paint){
        paint.setColor(getResources().getColor(R.color.colorPdfBlue));
        canvas.drawText(SECTION[1], xDefault, y, paint);
        drawRect(canvas, paint);

        for (Formation f: formations){
            paint.setColor(BLACK);
            canvas.drawText(f.date, xDefault, y, paint);
            canvas.drawText(f.nom, xValue, y, paint);

            paint.setColor(getResources().getColor(R.color.colorPdfBlue));
            y+=pasCont;
            canvas.drawText(f.etablissement, xValue, y, paint);
            y+=pasCont;
        }
    }

    public void printExprPro(ArrayList<ExprPro> exprPros, Canvas canvas, Paint paint){
        paint.setColor(getResources().getColor(R.color.colorPdfBlue));
        canvas.drawText(SECTION[2], xDefault, y, paint);
        drawRect(canvas, paint);

        for (ExprPro exp: exprPros){
            paint.setColor(BLACK);
            canvas.drawText(exp.debut+" - "+ exp.fin, xDefault, y, paint);
            canvas.drawText(exp.poste, xValue, y, paint);
            paint.setColor(getResources().getColor(R.color.colorPdfBlue));
            y+=pasCont;
            canvas.drawText(exp.societe, xValue, y, paint);
            y+=pasCont;
            //on definit la couleur pour ajouter des taches
            paint.setColor(BLACK);
            for (String tache: exp.taches){
                canvas.drawText(tache, xValue, y, paint);
                y+=pasCont;
            }

        }
    }

    public void printCompetences(ArrayList<Competence> competences, Canvas canvas, Paint paint){
        paint.setColor(getResources().getColor(R.color.colorPdfBlue));
        canvas.drawText(SECTION[3], xDefault, y, paint);
        drawRect(canvas, paint);

        paint.setColor(BLACK);
        for (Competence c:competences){
            canvas.drawText(c.nom, xDefault, y, paint);
            canvas.drawText(c.niveau, xValue, y, paint);
            y+=pasCont;
        }
    }

    public void printLangues(ArrayList<Langue> langues, Canvas canvas, Paint paint){
        paint.setColor(getResources().getColor(R.color.colorPdfBlue));
        canvas.drawText(SECTION[4], xDefault, y, paint);
        drawRect(canvas, paint);

        paint.setColor(BLACK);
        for (Langue l:langues){
            canvas.drawText(l.nom, xDefault, y, paint);
            canvas.drawText(l.niveau, xValue, y, paint);
            y+=pasCont;
        }
    }

    public void printCentreInteret(String centres, Canvas canvas, Paint paint){
        paint.setColor(getResources().getColor(R.color.colorPdfBlue));
        canvas.drawText(SECTION[5], xDefault, y, paint);
        drawRect(canvas, paint);

        paint.setColor(BLACK);
        canvas.drawText(centres, xDefault, y, paint);
        y+=pasCont;
    }

    private void drawRect(Canvas c, Paint p){
        y+=pasRect;
        p.setColor(getResources().getColor(R.color.colorPdfGray));
        c.drawRect(new Rect(60,y,482,y+1), p);
        y = y-pasRect +pasCont;
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i){
                case 0:
                    return detailsCvFragment;
                case 1:
                    return formationFragment;
                case 2:
                    return experienceFragment;
                case 3:
                    return competenceFragment;
                case 4:
                    return langueFragment;
                case 5:
                    return interestFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return MAX_VP;
        }
    }

}
