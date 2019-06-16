package com.example.jolvalre.beworker.database;

import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {

    public static final String CONTENT_AUTHORITY = "com.example.jolvalre.beworker";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

    public static final String PATH_CHERCHEUR = "chercheur";

    public static final String PATH_CV = "cv";

    public static final String PATH_OFFRE = "offre";

   // public static final String PATH_POSTULER = "postuler";

    public static final class Chercheur implements BaseColumns{

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_CHERCHEUR).build();

        public static final String TABLE_NAME = "chercheur";

        public static final String COLUMN_EMAIL = "email";

        public static final String COLUMN_NOM = "nom";

        public static final String COLUMN_PRENOM = "prenom";

        public static final String COLUMN_MOT_DE_PASSE = "mot_de_passe";

        public static final String COLUMN_DOMAINE = "domaine";

        public static final String COLUMN_AGE = "dateDeNaissance";

       // public static final String COLUMN_ADRESSE = "adresse";

        public static final String COLUMN_GENRE = "genre";

        public static final String COLUMN_STATUT = "statut";

        public static final String COLUMN_TELEPHONE = "telephone";

        public static final String COLUMN_VILLE = "ville";

        public static Uri fonction(long id){

            return  CONTENT_URI.buildUpon().appendPath(String.valueOf(id)).build();

        }
    }

    public static final class CV implements BaseColumns{

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_CV).build();

        public static final String TABLE_NAME = "cv";

        public static final String COLUMN_ID_CHERCHEUR = "id_chercheur";

        public static final String COLUMN_PATH = "path";

        public static final String COLUMN_NUMERO_CV = "numero_cv";

        public static Uri fonction(long id){

            return  CONTENT_URI.buildUpon().appendPath(String.valueOf(id)).build();

        }
    }

    public static final class OffreLocal implements BaseColumns{

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_OFFRE).build();

        public static final String TABLE_NAME = "offre";

        public static final String COLUMN_ID_EMPLOYEUR = "id_employeur";

        public static final String COLUMN_ID_CATEGORIE = "id_categorie";

        public static final String COLUMN_DATE_POST = "date_post";

        public static final String COLUMN_POSTE = "poste";

        public static final String COLUMN_VILLES = "ville";

        public static final String COLUMN_LAN = "lan";

        public static final String COLUMN_DESCRIPTION = "description";

        public static Uri fonction(long id){

            return  CONTENT_URI.buildUpon().appendPath(String.valueOf(id)).build();

        }
    }

  /*  public static final class Postuler implements BaseColumns{

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_POSTULER).build();

        public static final String TABLE_NAME = "postuler";

        public static final String COLUMN_ID_CHERCHEUR = "id_chercheur";

        public static final String COLUMN_ID_OFFRE = "id_offre";

        public static final String COLUMN_DATE = "date";

        public static Uri fonction(long id){

            return  CONTENT_URI.buildUpon().appendPath(String.valueOf(id)).build();

        }
    }*/

}
