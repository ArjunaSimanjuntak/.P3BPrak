package com.example.watchlist_tubes1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

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

    public List<Movie> getAllMovie(){
        List<Movie> allMovies = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);                                          // diisi data sama datanya
        }

        if (cursor.moveToFirst()) {
            //
        }


        return allMovies;                                                                              // ada kemungkinan ngembaliin null
    }


    public Cursor getMoviebyId (int idMovie) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME
                + " WHERE " + COLUMN_ID + " = " + idMovie;

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);                                          // diisi data sama datanya
        }


        return cursor;
    }



    // ngasal
    public boolean addMovie (Movie newMovie) {                                                      Log.d(TAG, "addMovie: ");
        boolean apaBisa = true;

        if (newMovie == null) {
            Log.d(TAG, "addMovie: movie masukan belum diisi. null");
            return false;
        }

        // query....
        SQLiteDatabase db = this.getWritableDatabase();                                             // writable karna mau nambah

        ContentValues values = new ContentValues();                                                     // prlu import. smcm hash map, atau array(?)

                                                                                                    Log.d(TAG, "addMovie: newMovie title nya: " + newMovie.getTitle());
        values.put(COLUMN_TITLE, newMovie.getTitle());                                                  // key, value...
                                                                                                    Log.d(TAG, "addMovie: newMovie synopsis nya: " + newMovie.getSynopsis());
        values.put(COLUMN_SYNOPSIS, newMovie.getSynopsis());
                                                                                                    Log.d(TAG, "addMovie: newMovie review nya: " + newMovie.getReview());
        values.put(COLUMN_REVIEW, newMovie.getReview());
                                                                                                    Log.d(TAG, "addMovie: newMovie status nya: " + newMovie.getStatus());
        values.put(COLUMN_STATUS, newMovie.getStatus());
                                                                                                    Log.d(TAG, "addMovie: newMovie star nya: " + newMovie.getStar());
        values.put(COLUMN_STAR, newMovie.getStar());

                                                                                                    Log.d(TAG, "addMovie: the movie toStringnya: " + newMovie.toString());
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


    void deleteRowbyID(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
//        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        long result = db.delete(TABLE_NAME, "id=?", new String[]{row_id});
        if(result == -1){
//            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
//            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    // sekali aja dipanggil buat ngapus
    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
