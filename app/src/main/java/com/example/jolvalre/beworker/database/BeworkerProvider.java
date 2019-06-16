package com.example.jolvalre.beworker.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class BeworkerProvider extends ContentProvider {


    BeworkerHelper BEWORKER_DATA_BASE;

    private  static  final int CHERCHEUR = 100;

    private  static  final int CHERCHEUR_VIA_ID = 101;

    private  static  final int CV = 110;

    private  static  final int CV_VIA_ID = 111;

    private  static  final int OFFRE = 120;

    private  static  final int OFFRE_VIA_ID = 121;

    //private  static  final int POSTULER = 130;

    UriMatcher uriMatcher = buildUriMatcher();

    private UriMatcher buildUriMatcher(){

        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        String autority = DatabaseContract.CONTENT_AUTHORITY;

        uriMatcher.addURI(autority, DatabaseContract.PATH_CHERCHEUR, CHERCHEUR);

        uriMatcher.addURI(autority, DatabaseContract.PATH_CHERCHEUR+"/#", CHERCHEUR_VIA_ID);


        uriMatcher.addURI(autority, DatabaseContract.PATH_CV, CV);

        uriMatcher.addURI(autority, DatabaseContract.PATH_CV+"/#", CV_VIA_ID);


        uriMatcher.addURI(autority, DatabaseContract.PATH_OFFRE, OFFRE);

        uriMatcher.addURI(autority, DatabaseContract.PATH_OFFRE+"/#", OFFRE_VIA_ID);


        //uriMatcher.addURI(autority, DatabaseContract.PATH_POSTULER, POSTULER);


        return uriMatcher;
    }



    @Override
    public boolean onCreate() {
        BEWORKER_DATA_BASE = new BeworkerHelper(getContext());
        Log.e("alllo", "on cree le provider ");
        return true;
    }


    @Override
    public Cursor query( Uri uri, String[] projection, String selection,  String[] selectionArgs, String sortOrder) {

        Cursor cursor = null;


        Log.e("alllo", "allo recup");

        switch (uriMatcher.match(uri)){

            case CHERCHEUR:

                cursor = BEWORKER_DATA_BASE.getWritableDatabase().query(DatabaseContract.Chercheur.TABLE_NAME,projection,selection,selectionArgs,null,null,null);

                break;

            case CHERCHEUR_VIA_ID:

                selection = DatabaseContract.Chercheur._ID + " = ? ";

                selectionArgs[0] = uri.getLastPathSegment();

                cursor = BEWORKER_DATA_BASE.getReadableDatabase().query(DatabaseContract.Chercheur.TABLE_NAME,projection,selection,selectionArgs,null,null,null);

                break;

            case CV:

                cursor = BEWORKER_DATA_BASE.getWritableDatabase().query(DatabaseContract.CV.TABLE_NAME,projection,selection,selectionArgs,null,null,null);

                break;

            case CV_VIA_ID:

                selection = DatabaseContract.CV._ID + " = ? ";

                selectionArgs[0] = uri.getLastPathSegment();

                cursor = BEWORKER_DATA_BASE.getReadableDatabase().query(DatabaseContract.CV.TABLE_NAME,projection,selection,selectionArgs,null,null,null);

                break;

            case OFFRE:

                cursor = BEWORKER_DATA_BASE.getWritableDatabase().query(DatabaseContract.OffreLocal.TABLE_NAME,projection,selection,selectionArgs,null,null,null);

                break;

            case OFFRE_VIA_ID:

                selection = DatabaseContract.OffreLocal._ID + " = ? ";

                selectionArgs[0] = uri.getLastPathSegment();

                cursor = BEWORKER_DATA_BASE.getReadableDatabase().query(DatabaseContract.OffreLocal.TABLE_NAME,projection,selection,selectionArgs,null,null,null);

                break;

            /*case POSTULER:

                cursor = BEWORKER_DATA_BASE.getWritableDatabase().query(DatabaseContract.Postuler.TABLE_NAME,projection,selection,selectionArgs,null,null,null);

                break;*/

        }

        return cursor;
    }

    @Override
    public String getType( Uri uri) {
        return null;
    }


    @Override
    public Uri insert( Uri uri, ContentValues values) {


        Log.e("alllo", "on comence insert.");

        switch (uriMatcher.match(uri)){

            case CHERCHEUR:
                Log.e("alllo", "on est dan chercheur case ");
                long id_return_chercheur =  BEWORKER_DATA_BASE.getWritableDatabase().insert(DatabaseContract.Chercheur.TABLE_NAME, null,values);
                return uri.buildUpon().appendPath(String.valueOf(id_return_chercheur)).build();

            case CV:
                long id_return_cv =  BEWORKER_DATA_BASE.getWritableDatabase().insert(DatabaseContract.CV.TABLE_NAME, null,values);
                return uri.buildUpon().appendPath(String.valueOf(id_return_cv)).build();


            case OFFRE:
                long id_return_offre =  BEWORKER_DATA_BASE.getWritableDatabase().insert(DatabaseContract.OffreLocal.TABLE_NAME, null,values);
                return uri.buildUpon().appendPath(String.valueOf(id_return_offre)).build();


           /* case POSTULER:
                long id_return_postuler =  BEWORKER_DATA_BASE.getWritableDatabase().insert(DatabaseContract.Postuler.TABLE_NAME, null,values);
                return uri.buildUpon().appendPath(String.valueOf(id_return_postuler)).build();

*/


        }


        return null;
    }

    @Override
    public int delete( Uri uri, String selection, String[] selectionArgs) {



        switch (uriMatcher.match(uri)){

            case CHERCHEUR:

                return BEWORKER_DATA_BASE.getWritableDatabase().delete(DatabaseContract.Chercheur.TABLE_NAME, selection, selectionArgs);


            case CHERCHEUR_VIA_ID:

                selection = DatabaseContract.Chercheur._ID + " = ?";

                selectionArgs[0] = uri.getLastPathSegment();

                return BEWORKER_DATA_BASE.getWritableDatabase().delete(DatabaseContract.Chercheur.TABLE_NAME,selection,selectionArgs);

            case CV:

                return BEWORKER_DATA_BASE.getWritableDatabase().delete(DatabaseContract.CV.TABLE_NAME, selection, selectionArgs);


            case CV_VIA_ID:

                selection = DatabaseContract.CV._ID + " = ?";

                selectionArgs[0] = uri.getLastPathSegment();

                return BEWORKER_DATA_BASE.getWritableDatabase().delete(DatabaseContract.CV.TABLE_NAME,selection,selectionArgs);

            case OFFRE:

                return BEWORKER_DATA_BASE.getWritableDatabase().delete(DatabaseContract.OffreLocal.TABLE_NAME, selection, selectionArgs);


            case OFFRE_VIA_ID:

                selection = DatabaseContract.OffreLocal._ID + " = ?";

                selectionArgs[0] = uri.getLastPathSegment();

                return BEWORKER_DATA_BASE.getWritableDatabase().delete(DatabaseContract.OffreLocal.TABLE_NAME,selection,selectionArgs);

           /* case POSTULER:

                return BEWORKER_DATA_BASE.getWritableDatabase().delete(DatabaseContract.Postuler.TABLE_NAME, selection, selectionArgs);
*/
        }
        return 0;
    }

    @Override
    public int update( Uri uri, ContentValues values,  String selection,  String[] selectionArgs) {


        switch (uriMatcher.match(uri)){

            case CHERCHEUR:
                return  BEWORKER_DATA_BASE.getWritableDatabase().update(DatabaseContract.Chercheur.TABLE_NAME, values, selection, selectionArgs);

            case CHERCHEUR_VIA_ID:

                selection = DatabaseContract.Chercheur._ID + " = ?";

                selectionArgs[0] = uri.getLastPathSegment();

                return BEWORKER_DATA_BASE.getWritableDatabase().update(DatabaseContract.Chercheur.TABLE_NAME,values,selection,selectionArgs);

            case CV:
                return  BEWORKER_DATA_BASE.getWritableDatabase().update(DatabaseContract.CV.TABLE_NAME, values, selection, selectionArgs);

            case CV_VIA_ID:

                selection = DatabaseContract.CV._ID + " = ?";

                selectionArgs[0] = uri.getLastPathSegment();

                return BEWORKER_DATA_BASE.getWritableDatabase().update(DatabaseContract.CV.TABLE_NAME,values,selection,selectionArgs);


            case OFFRE:
                return  BEWORKER_DATA_BASE.getWritableDatabase().update(DatabaseContract.OffreLocal.TABLE_NAME, values, selection, selectionArgs);

            case OFFRE_VIA_ID:

                selection = DatabaseContract.OffreLocal._ID + " = ?";

                selectionArgs[0] = uri.getLastPathSegment();

                return BEWORKER_DATA_BASE.getWritableDatabase().update(DatabaseContract.OffreLocal.TABLE_NAME,values,selection,selectionArgs);


           /* case POSTULER:
                return  BEWORKER_DATA_BASE.getWritableDatabase().update(DatabaseContract.Postuler.TABLE_NAME, values, selection, selectionArgs);

*/
        }


        return 0;
    }
}
