package com.example.watchlist_tubes1;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // yg pertama kali dipanggil waktu DB (database) diakses
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //
    }
}
