package com.example.m0517057;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.File;

public class PenyimpananNilaiDisplay {
    protected SharedPreferences sharedPref;
    protected final static String NAMA_SHARED_PREF = "sp_nilai_display";
    protected final static String KEY_BARANG = "BARANG";
    protected final static String KEY_HARGA = "HARGA";
    protected final static String KEY_KET = "KET";
    Context context;

    public PenyimpananNilaiDisplay(Context context){
        this.sharedPref = context.getSharedPreferences(NAMA_SHARED_PREF, 0);
    }

    public void saveBarang (String barang, String harga, String ket){
       SharedPreferences.Editor editor = this.sharedPref.edit();
       editor.putString(KEY_BARANG, barang);
       editor.putString(KEY_HARGA, harga);
       editor.putString(KEY_KET, ket);
       editor.commit();
    }

   /* public void saveHarga (String harga){
        SharedPreferences.Editor editor = this.sharedPref.edit();
        editor.putString(KEY_HARGA, harga);
        editor.commit();
    }

    public void saveKet (String ket){
        SharedPreferences.Editor editor = this.sharedPref.edit();
        editor.putString(KEY_KET, ket);
        editor.commit();
    }*/

    public String getBarang(){
        //Belum coba pakai getAll()
        return sharedPref.getString(KEY_BARANG,"");
    }

    public String getHarga() {
        return sharedPref.getString(KEY_HARGA, "");
    }

    public String getKet() {
        return sharedPref.getString(KEY_KET, "");
    }

    private void storeInternal(String param, String filename, int opt){
        File file = null;
        if(opt == 1){
            file = new File(context.getFilesDir(), filename);
        }
    }
}
