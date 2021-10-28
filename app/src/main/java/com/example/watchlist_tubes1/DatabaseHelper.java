package com.example.watchlist_tubes1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "debug DBHelper";

    private static final String DATABASE_NAME = "movie.db";                                         // nama db yg diset di constructor

    private static final String TABLE_NAME = "MOVIE_TABLE";

    private static final String COLUMN_ID = "id";                                                   // nama" kolomnya
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
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {                           // i oldVersion, i1 newVersion
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public static String getColumnId() {
        return COLUMN_ID;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);                                          // diisi data sama datanya
        }
        return cursor;                                                                              // ada kemungkinan ngembaliin null
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

        ContentValues values = new ContentValues();                                                     // prlu import. smcm hash map, atau array(?)

        values.put(COLUMN_TITLE, theMovie.getTitle());                                                  // key, value...
        values.put(COLUMN_SYNOPSIS, theMovie.getSynopsis());
        values.put(COLUMN_REVIEW, theMovie.getReview());
        values.put(COLUMN_STATUS, theMovie.getStatus());
        values.put(COLUMN_STAR, theMovie.getStar());

                                                                                                    Log.d(TAG, "addMovie: the movie: " + theMovie.toString());
                                                                                                    // insert.
                                                                                                    //  long result = db.insert(TABLE_NAME,null, values);                                  // result + kl berhasil, - gagal
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_NAME, null, values);


        if( newRowId < 0) { apaBisa = false; }                                                        // atau lgsg return false

        return apaBisa;
    }


    // waktu mau review
                                                                            // status movie shrsnya gabisa diganti ?
    void updateData(String row_id, String title, String synopsis, String review, String status, int star){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_TITLE, title);                                                            // key, value...
        values.put(COLUMN_SYNOPSIS, synopsis);
        values.put(COLUMN_REVIEW, review);
        values.put(COLUMN_STATUS, status);
        values.put(COLUMN_STAR, star);

        long result = db.update(TABLE_NAME, values, "_id=?", new String[]{row_id});
        if(result == -1){
//            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
//            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }


    void deleteARow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
//            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
//            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }
}
