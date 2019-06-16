package com.example.jolvalre.beworker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BeworkerHelper extends SQLiteOpenHelper {

    public static final String CREATE_TABLE_CHERCHEUR = "CREATE TABLE " + DatabaseContract.Chercheur.TABLE_NAME + "("
            + DatabaseContract.Chercheur._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DatabaseContract.Chercheur.COLUMN_EMAIL + " TEXT NOT NULL UNIQUE, "
            + DatabaseContract.Chercheur.COLUMN_NOM + " TEXT NOT NULL, "
            + DatabaseContract.Chercheur.COLUMN_PRENOM + " TEXT NOT NULL, "
            + DatabaseContract.Chercheur.COLUMN_DOMAINE + " TEXT, "
            + DatabaseContract.Chercheur.COLUMN_MOT_DE_PASSE + " TEXT NOT NULL, "
            + DatabaseContract.Chercheur.COLUMN_TELEPHONE + " TEXT NOT NULL, "
            + DatabaseContract.Chercheur.COLUMN_GENRE + " TEXT NOT NULL, "
            + DatabaseContract.Chercheur.COLUMN_STATUT + " TEXT NOT NULL, "
            + DatabaseContract.Chercheur.COLUMN_AGE + " TEXT NOT NULL, "
            + DatabaseContract.Chercheur.COLUMN_VILLE + " TEXT NOT NULL);";

    public static final String CREATE_TABLE_CV = "CREATE TABLE " + DatabaseContract.CV.TABLE_NAME + "("
            + DatabaseContract.CV._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DatabaseContract.CV.COLUMN_ID_CHERCHEUR + " INTEGER NOT NULL, "
            + DatabaseContract.CV.COLUMN_NUMERO_CV + " INTEGER NOT NULL, "
            + DatabaseContract.CV.COLUMN_PATH + " TEXT NOT NULL, "
            + "FOREIGN KEY (" + DatabaseContract.CV.COLUMN_ID_CHERCHEUR
            + ") REFERENCES " + DatabaseContract.Chercheur.TABLE_NAME + "(" + DatabaseContract.Chercheur._ID + "));";

    public static final String CREATE_TABLE_OFFRE = "CREATE TABLE " + DatabaseContract.OffreLocal.TABLE_NAME + "("
            + DatabaseContract.OffreLocal._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DatabaseContract.OffreLocal.COLUMN_ID_EMPLOYEUR + " INTEGER NOT NULL, "
            + DatabaseContract.OffreLocal.COLUMN_ID_CATEGORIE + " INTEGER NOT NULL, "
            + DatabaseContract.OffreLocal.COLUMN_POSTE + " TEXT NOT NULL, "
            + DatabaseContract.OffreLocal.COLUMN_DATE_POST + " DATE NOT NULL, "
            + DatabaseContract.OffreLocal.COLUMN_VILLES + " TEXT NOT NULL, "
            + DatabaseContract.OffreLocal.COLUMN_LAN + " TEXT NOT NULL, "
            + DatabaseContract.OffreLocal.COLUMN_DESCRIPTION + " TEXT NOT NULL);";

    /*public static final String CREATE_TABLE_POSTULER = "CREATE TABLE " + DatabaseContract.Postuler.TABLE_NAME + "("
            + DatabaseContract.Postuler.COLUMN_ID_CHERCHEUR + " INTEGER PRIMARY KEY, "
            + DatabaseContract.Postuler.COLUMN_ID_OFFRE + " INTEGER PRIMARY KEY, "
            + DatabaseContract.Postuler.COLUMN_DATE + " DATE NOT NULL, "
            + "FOREIGN KEY (" + DatabaseContract.Postuler.COLUMN_ID_CHERCHEUR
            + ") REFERENCES " + DatabaseContract.Chercheur.TABLE_NAME + "(" + DatabaseContract.Chercheur._ID + "),"
            + "FOREIGN KEY (" + DatabaseContract.Postuler.COLUMN_ID_OFFRE
            + ") REFERENCES " + DatabaseContract.OffreLocal.TABLE_NAME + "(" + DatabaseContract.OffreLocal._ID + "));";
*/

    public static final String BEWORKER_DATA_BASE_NAME = "beworker.db";
    public static final int VERSION_NUMBER = 1;

    public BeworkerHelper(Context context) {
        super(context, BEWORKER_DATA_BASE_NAME, null, VERSION_NUMBER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CHERCHEUR);
        db.execSQL(CREATE_TABLE_CV);
        db.execSQL(CREATE_TABLE_OFFRE);
        //db.execSQL(CREATE_TABLE_POSTULER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + DatabaseContract.Chercheur.TABLE_NAME + ";");
        db.execSQL("DROP TABLE " + DatabaseContract.CV.TABLE_NAME + ";");
        db.execSQL("DROP TABLE " + DatabaseContract.OffreLocal.TABLE_NAME + ";");
        //db.execSQL("DROP TABLE " + DatabaseContract.Postuler.TABLE_NAME + ";");
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + DatabaseContract.Chercheur.TABLE_NAME + ";");
        db.execSQL("DROP TABLE " + DatabaseContract.CV.TABLE_NAME + ";");
        db.execSQL("DROP TABLE " + DatabaseContract.OffreLocal.TABLE_NAME + ";");
       // db.execSQL("DROP TABLE " + DatabaseContract.Postuler.TABLE_NAME + ";");
        onCreate(db);
    }
}
