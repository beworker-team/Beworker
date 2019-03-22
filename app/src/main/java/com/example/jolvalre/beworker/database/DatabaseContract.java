package com.example.jolvalre.beworker.database;

import android.provider.BaseColumns;

public class DatabaseContract {

    public static final class Chercheur implements BaseColumns{

        public static final String TABLE_NAME = "chercheur";

        public static final String COLUMN_ID = "id_chercheur";

        public static final String COLUMN_EMAIL = "email";

        public static final String COLUMN_NOM = "nom";

        public static final String COLUMN_PRENOM = "prenom";

        public static final String COLUMN_MOT_DE_PASSE = "mot_de_passe";

        public static final String COLUMN_DOMAINE = "domaine";

        public static final String COLUMN_DATE_DE_NAISSANCE = "date_naissance";

        public static final String COLUMN_GENRE = "genre";

        public static final String COLUMN_STATUT = "statut";

        public static final String COLUMN_TELEPHONE = "telephone";

        public static final String COLUMN_VILLE = "ville";
    }

    public static final class CV implements BaseColumns{

        public static final String TABLE_NAME = "cv";

        public static final String COLUMN_ID = "id_cv";

        public static final String COLUMN_ID_CHERCHEUR = "id_chercheur";

        public static final String COLUMN_PATH = "path";

        public static final String COLUMN_NUMERO_CV = "numero_cv";
    }

    public static final class Offre implements BaseColumns{

        public static final String TABLE_NAME = "offre";

        public static final String COLUMN_ID = "id_offre";

        public static final String COLUMN_ID_EMPLOYEUR = "id_employeur";

        public static final String COLUMN_DATE_POST = "date_post";

        public static final String COLUMN_POSTE = "poste";

        public static final String COLUMN_VILLE = "ville";

        public static final String COLUMN_DESCRIPTION = "description";
    }

    public static final class Postuler implements BaseColumns{

        public static final String TABLE_NAME = "postuler";

        public static final String COLUMN_ID_CHERCHEUR = "id_chercheur";

        public static final String COLUMN_ID_OFFRE = "id_offre";

        public static final String COLUMN_DATE = "date";
    }

}
