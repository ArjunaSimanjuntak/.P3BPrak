package com.example.m6_storage;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.m6_storage.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "debug MainAct";
    ActivityMainBinding bindingMain;
    PenyimpanNilaiDisplay pencatat;

    // buat permission
    private static final int WRITE_REQUEST_CODE = 1;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        this.bindingMain = ActivityMainBinding.inflate(this.getLayoutInflater());
        View layout = this.bindingMain.getRoot();
        this.setContentView(layout);

        this.bindingMain.buttId.setOnClickListener(this);                                           //button add ditekan


        this.pencatat = new PenyimpanNilaiDisplay(this);


        // Permission   (cukup SEKALI)                                                              //dan add import androidx.annotation.RequiresApi; blabla alt enter buat liat nanti
//        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};                        // dan override onrequest permission result.. dan import package manager nya lagi
//        this.requestPermissions(permissions, WRITE_REQUEST_CODE);                                 // dan... tmbh permission di manifest

    }

    @Override
    protected void onPause() {
        super.onPause();
        this.pencatat.saveBarang(bindingMain.etBarang.getText().toString());                        // ambil apa yg ada di editText
        this.pencatat.saveHarga(bindingMain.etHarga.getText().toString());
        this.pencatat.saveKeterangan(bindingMain.etKeterangan.getText().toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.bindingMain.etBarang.setText(this.pencatat.getBarang());                               // terus dipasang balik
        this.bindingMain.etHarga.setText(this.pencatat.getHarga());
        this.bindingMain.etKeterangan.setText(this.pencatat.getKeterangan());
    }

    @Override
    public void onClick(View view) {
        if (view == this.bindingMain.buttId) {                                                      // button "Add" ditekan

            // internal
//            this.pencatat.addWithInternal(opt);

            // set isi etBarang, harga, keterangn


            StringBuilder theString = new StringBuilder(100);
            theString.append(this.pencatat.getBarang());                                            //terustambah"
            theString.append(this.pencatat.getHarga());
            theString.append(this.pencatat.getKeterangan());

            Log.d(TAG, "onClick: isinya theString:  " + theString);


            // Option 1
            int opt = 1; // 1 or 2
            // coba simpan di Internal
            this.pencatat.storeInternal(theString.toString(), "isiTheString_file", opt);
            Log.d(TAG, "onClick: yg tersimpan di internal sekarang mestinya: " + this.pencatat.loadInternal("isiTheString_file", 1));

            // di External

            this.pencatat.storeExternal(theString.toString(), "isiTheString_file", opt);

            Log.d(TAG, "onClick: yg tersimpan di external skrng mestinya: " + this.pencatat.loadExternal("isiTheString_file", 1));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case WRITE_REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Granted
                    Log.d("REQPERM", "Granted");
                } else {                                                                            // masih denied
                    // Denied
                    Log.d("REQPERM", "Denied");
                }
                break;
        }
    }

}