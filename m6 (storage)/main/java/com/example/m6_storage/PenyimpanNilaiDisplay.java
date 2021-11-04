package com.example.m6_storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Scanner;

public class PenyimpanNilaiDisplay {
    private static final String TAG = "debug PenyimpanNDisplay";                                    // can only be most 23 characters
    private static final String FILE_NAME = "text_nya.txt";
    protected SharedPreferences sharedPref;
    protected final static String NAMA_SHARED_PREF = "sp_nilai_display";
    protected final static String KEY_BARANG = "BARANG";


    protected final static String KEY_HARGA = "HARGA";
    protected final static String KEY_KETERANGAN = "KETERANGAN";

//    private String sharedPrefFile = "com.example.android.hellosharedprefs";


    public PenyimpanNilaiDisplay(Context context) {
        this.sharedPref = context.getSharedPreferences(NAMA_SHARED_PREF, 0);

    }

    public void addWithInternal (int option) {
        //
        File file = null;
    }



    // simpan & get barang, harga, keterangan
    public void saveBarang (String barang) {
        SharedPreferences.Editor editor = this.sharedPref.edit();
        editor.putString(KEY_BARANG, barang);
        editor.commit();                                                                            // ga .apply() ?
    }

    public String getBarang () {
        return sharedPref.getString(KEY_BARANG, "");
    }

    public void saveHarga (String harga) { //integer?
        SharedPreferences.Editor editor = this.sharedPref.edit();
        editor.putString(KEY_HARGA, harga);
        editor.commit();
    }

    public String getHarga () {
        return sharedPref.getString(KEY_HARGA, "");
    }

    public void saveKeterangan (String keterangan) {
        SharedPreferences.Editor editor = this.sharedPref.edit();
        editor.putString(KEY_KETERANGAN, keterangan);
        editor.commit();
    }

    public String getKeterangan () {
        return sharedPref.getString(KEY_KETERANGAN, "");
    }


    // or getAll();
    public String ambilTigatiganya () {                                                            // barang,harga,keterangn
        StringBuilder semuanya = new StringBuilder(100);
        semuanya.append(getBarang() + ",");                                                        //terustambah"
        semuanya.append(getHarga() + ",");
        semuanya.append(getKeterangan());

        return semuanya.toString();
    }


//    public void save(int mode_private) {                                                            //
//        String text = ambilTigatiganya();
//        FileOutputStream fos = null;                                                                //import
//
//        try {
//            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);                                          // mesti di main activity (yg ada context nya) biar bisa pake ??
//            fos.write(text.getBytes());
//
//            mEditText.getText().clear();
//            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME,
//                    Toast.LENGTH_LONG).show();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (fos != null) {
//                try {
//                    fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public void load(View v) {
//        FileInputStream fis = null;
//
//        try {
//            fis = openFileInput(FILE_NAME);
//            InputStreamReader isr = new InputStreamReader(fis);
//            BufferedReader br = new BufferedReader(isr);
//            StringBuilder sb = new StringBuilder();
//            String text;
//
//            while ((text = br.readLine()) != null) {
//                sb.append(text).append("\n");
//            }
//
//            mEditText.setText(sb.toString());
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (fis != null) {
//                try {
//                    fis.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }







    // dr kelas
    // INTERNAL
    public void storeInternal (String param, String filename, int opt) {
        File file = null;
        if (opt == 1) {                                                                             //permanent storage : /data/user/0/com.pppb.pppb_all/cache/test.txt
            file = new File(this.getFilesDir(), filename);
        } else if (opt == 2) {                                                                      // temporary storage : /data/user/0/com.pppb.pppb_all/cache/test.txt
            file = new File(this.getCacheDir(), filename);
        }
        this.writeFile(file, param);
    }

    public String loadInternal (String filename, int opt) {
        File file = null;
        if (opt == 1) {                                                                             //permanent storage
            file = new File(this.getFilesDir(), filename);
        } else {                                                                                    // temporary storage
            file = new File(this.getCacheDir(), filename);
        }

        return this.readFile(file);
    }

    public String readFile(File file) {
        String data = "";
        try {                                                                                       // pake scanner bacanya
            Scanner scanner = new Scanner(file);
            data = scanner.next();
            scanner.close();
        } catch (FileNotFoundException e) {
            Log.d(TAG, "readFile: io error " + e.getMessage());
        }
        return data;
    }


    public void writeFile(File file, String param) {
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.append(param);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            Log.d(TAG, "writeFile: io error " + e.getMessage());                                // error di writefile nya! ??
        }
        Log.d(TAG, "writeFile: storage_path " + file.getAbsolutePath());
    }

    private File getCacheDir() {
        return null;
    }

    private File getFilesDir() {
        return null;
    }


    // EXTERNAL
    public void storeExternal (String param, String filename, int opt) {
        Log.d(TAG, "storeExternal: storage_path " + Environment.getExternalStorageState());
        File file = null;
        if (opt == 1) {                                                                             //permanent storage : /storage/emulated/0/Android/....
            file = new File(this.getExternalFilesDir(Environment.DIRECTORY_PICTURES), filename);
        } else if (opt == 2) {                                                                      // permanent > SD CARD
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//                File[] files = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//                file = new File(files[1], filename);
            }
        } else if (opt == 3) {                                                                      // temporary storage : /storage/emulated/0/Android/....
            file = new File(this.getExternalCacheDir(), filename);
        } else if (opt == 4) {                                                                      // temporary storage > SD card
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File[] files = this.getExternalCacheDirs();
                file = new File(files[1], filename);
            }
        } else if (opt == 5) {                                                                      // public root folder (deprecated, not recommended)
            File path = Environment.getExternalStorageDirectory();
            file = new File (path, filename);
        } else {                                                                                    // public picture folder
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            file = new File(path, filename);
        }

        this.writeFile(file, param);
    }

    private File getExternalCacheDir() {
        return null;
    }

    public String loadExternal (String filename, int opt) {
        File file = null;
        if (opt == 1) {                                                                             //permanent storage : /storage/emulated/0/Android/....
            file = new File(this.getExternalFilesDir(Environment.DIRECTORY_PICTURES), filename);
        } else if (opt == 2) {                                                                      // permanent > SD CARD
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//                File[] files = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//                file = new File(files[1], filename);
            }
        } else if (opt == 3) {                                                                      // temporary storage : /storage/emulated/0/Android/....
            file = new File(this.getExternalCacheDir(), filename);
        } else if (opt == 4) {                                                                      // temporary storage > SD card
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File[] files = this.getExternalCacheDirs();
                file = new File(files[1], filename);
            }
        } else if (opt == 5) {                                                                      // public root folder (deprecated, not recommended)
            File path = Environment.getExternalStorageDirectory();
            Log.d(TAG, "WRFILE  loadExternal: "+ path.getAbsolutePath());
            file = new File (path, filename);
        } else {                                                                                    // public picture folder
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            file = new File(path, filename);
        }

        return this.readFile(file);
    }


    private File[] getExternalCacheDirs() {
        return new File[0];
    }

    private File getExternalFilesDir(String directoryPictures) {
        return null;
    }



}
