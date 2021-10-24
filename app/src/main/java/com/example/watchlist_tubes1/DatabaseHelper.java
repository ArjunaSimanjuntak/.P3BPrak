package com.example.watchlist_tubes1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

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
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_SYNOPSIS + " TEXT, " +
                COLUMN_REVIEW + " TEXT, " +
                COLUMN_STATUS + " BOOLEAN, " +
                COLUMN_STAR + " INTEGER);";

        /*
        CREATE TABLE
            TABLE_NAME
        ( COLUMN_ID         INTEGER PRIMARY KEY AUTOINCREMENT,
          COLUMN_TITLE      TEXT,
          COLUMN_SYNOPSIS   TEXT,
          COLUMN_REVIEW     TEXT,
          COLUMN_STATUS     BOOLEAN,
          COLUMN_STAR       INTEGER );
        * */

        db.execSQL(query);
    }

    // dipanggil waktu versi db beruba
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {                           // i oldVersion, i1 newVersion
        //
    }
}
