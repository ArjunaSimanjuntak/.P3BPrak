package com.example.watchlist_tubes1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "debug DBHelper";

    private static final String DATABASE_NAME = "movie.db";                                         // nama db yg diset di constructor

    private static final String TABLE_NAME = "MOVIE_TABLE";                                         // nama" kolomnya
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title_movie";
    private static final String COLUMN_SYNOPSIS = "synopsis_movie";
    private static final String COLUMN_REVIEW = "review_movie";
    private static final String COLUMN_STATUS = "status_movie";
    private static final String COLUMN_STAR = "howmanyStar_movie";

    public DatabaseHelper(@Nullable Context context ) {                                             //, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, 1);
    }

    // yg pertama kali dipanggil waktu DB (database) diakses
    @Override
    public void onCreate(SQLiteDatabase db) {                                                       Log.d(TAG, "onCreate: ");
                                                                                                    // pastikan berspasi!
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_SYNOPSIS + " TEXT, " +
                COLUMN_REVIEW + " TEXT, " +
                COLUMN_STATUS + " BOOLEAN, " +
                COLUMN_STAR + " INTEGER);";

        db.execSQL(query);
    }
        /* seolah :
        CREATE TABLE
            TABLE_NAME
        ( COLUMN_ID         INTEGER PRIMARY KEY AUTOINCREMENT,
          COLUMN_TITLE      TEXT,
          COLUMN_SYNOPSIS   TEXT,
          COLUMN_REVIEW     TEXT,
          COLUMN_STATUS     BOOLEAN,
          COLUMN_STAR       INTEGER );
        * */



    // dipanggil waktu versi db beruba
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {                           // i oldVersion, i1 newVersion
        //
    }



    // ngasal
    public boolean addMovie (Movie theMovie) {                                                      Log.d(TAG, "addMovie: ");
        boolean apaBisa = true;

        if (theMovie == null) {
            Log.d(TAG, "addMovie: movie masukan belum diisi. null");
            return false;
        }

        // query....
        SQLiteDatabase db = this.getWritableDatabase();                                             // writable karna mau nambah

        ContentValues cv = new ContentValues();                                                     // prlu import. smcm hash map, atau array(?)

        cv.put(COLUMN_TITLE, theMovie.getTitle());                                                  // key, value...
        cv.put(COLUMN_SYNOPSIS, theMovie.getSynopsis());
        cv.put(COLUMN_REVIEW, theMovie.getReview());
        cv.put(COLUMN_STATUS, theMovie.getStatus());
        cv.put(COLUMN_STAR, theMovie.getStar());

                                                                                                    // insert.
        long result = db.insert(TABLE_NAME,null, cv);                                  // result + kl berhasil, - gagal


        if( result < 0) { apaBisa = false; }                                                        // atau lgsg return false

        return apaBisa;
    }
}
