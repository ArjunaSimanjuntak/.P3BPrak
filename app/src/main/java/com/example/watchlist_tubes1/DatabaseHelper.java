package com.example.watchlist_tubes1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context ) {                                             //, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "movie.db", null, 1);
    }

    // yg pertama kali dipanggil waktu DB (database) diakses
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //
    }

    // dipanggil waktu versi db beruba
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {                           // i oldVersion, i1 newVersion
        //
    }
}
