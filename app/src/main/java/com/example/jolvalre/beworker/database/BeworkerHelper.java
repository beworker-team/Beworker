package com.example.jolvalre.beworker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BeworkerHelper extends SQLiteOpenHelper {

    public static final String CREATE_TABLE_CHERCHEUR = "CREATE TABLE " + DatabaseContract.Chercheur.TABLE_NAME + "("
            + DatabaseContract.Chercheur.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DatabaseContract.Chercheur.COLUMN_EMAIL + " TEXT NOT NULL UNIQUE, "
            + DatabaseContract.Chercheur.COLUMN_NOM + " TEXT NOT NULL, "
            + DatabaseContract.Chercheur.COLUMN_PRENOM + " TEXT NOT NULL, "
            + DatabaseContract.Chercheur.COLUMN_DOMAINE + " TEXT, "
            + DatabaseContract.Chercheur.COLUMN_MOT_DE_PASSE + " TEXT NOT NULL, "
            + DatabaseContract.Chercheur.COLUMN_TELEPHONE + " TEXT NOT NULL, "
            + DatabaseContract.Chercheur.COLUMN_GENRE + " TEXT NOT NULL, "
            + DatabaseContract.Chercheur.COLUMN_STATUT + " TEXT NOT NULL, "
            + DatabaseContract.Chercheur.COLUMN_DATE_DE_NAISSANCE + " DATE NOT NULL, "
            + DatabaseContract.Chercheur.COLUMN_VILLE + " TEXT NOT NULL);";

    public static final String CREATE_TABLE_CV = "CREATE TABLE " + DatabaseContract.CV.TABLE_NAME + "("
            + DatabaseContract.CV.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DatabaseContract.CV.COLUMN_ID_CHERCHEUR + " INTEGER NOT NULL, "
            + DatabaseContract.CV.COLUMN_NUMERO_CV + " INTEGER NOT NULL, "
            + DatabaseContract.CV.COLUMN_PATH + " TEXT NOT NULL, "
            + "FOREIGN KEY (" + DatabaseContract.CV.COLUMN_ID_CHERCHEUR
            + ") REFERENCES " + DatabaseContract.Chercheur.TABLE_NAME + "(" + DatabaseContract.Chercheur.COLUMN_ID + "));";

    public static final String CREATE_TABLE_OFFRE = "CREATE TABLE " + DatabaseContract.Offre.TABLE_NAME + "("
            + DatabaseContract.Offre.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DatabaseContract.Offre.COLUMN_ID_EMPLOYEUR + " INTEGER NOT NULL, "
            + DatabaseContract.Offre.COLUMN_POSTE + " TEXT NOT NULL, "
            + DatabaseContract.Offre.COLUMN_DATE_POST + " DATE NOT NULL, "
            + DatabaseContract.Offre.COLUMN_VILLE + " TEXT NOT NULL, "
            + DatabaseContract.Offre.COLUMN_DESCRIPTION + " TEXT NOT NULL);";

    public static final String CREATE_TABLE_POSTULER = "CREATE TABLE " + DatabaseContract.Postuler.TABLE_NAME + "("
            + DatabaseContract.Postuler.COLUMN_ID_CHERCHEUR + " INTEGER PRIMARY KEY, "
            + DatabaseContract.Postuler.COLUMN_ID_OFFRE + " INTEGER PRIMARY KEY, "
            + DatabaseContract.Postuler.COLUMN_DATE + " DATE NOT NULL, "
            + "FOREIGN KEY (" + DatabaseContract.Postuler.COLUMN_ID_CHERCHEUR
            + ") REFERENCES " + DatabaseContract.Chercheur.TABLE_NAME + "(" + DatabaseContract.Chercheur.COLUMN_ID + "),"
            + "FOREIGN KEY (" + DatabaseContract.Postuler.COLUMN_ID_OFFRE
            + ") REFERENCES " + DatabaseContract.Offre.TABLE_NAME + "(" + DatabaseContract.Offre.COLUMN_ID + "));";

    public BeworkerHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CHERCHEUR);
        db.execSQL(CREATE_TABLE_CV);
        db.execSQL(CREATE_TABLE_OFFRE);
        db.execSQL(CREATE_TABLE_POSTULER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + DatabaseContract.Chercheur.TABLE_NAME + ";");
        db.execSQL("DROP TABLE " + DatabaseContract.CV.TABLE_NAME + ";");
        db.execSQL("DROP TABLE " + DatabaseContract.Offre.TABLE_NAME + ";");
        db.execSQL("DROP TABLE " + DatabaseContract.Postuler.TABLE_NAME + ";");
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + DatabaseContract.Chercheur.TABLE_NAME + ";");
        db.execSQL("DROP TABLE " + DatabaseContract.CV.TABLE_NAME + ";");
        db.execSQL("DROP TABLE " + DatabaseContract.Offre.TABLE_NAME + ";");
        db.execSQL("DROP TABLE " + DatabaseContract.Postuler.TABLE_NAME + ";");
        onCreate(db);
    }
}
