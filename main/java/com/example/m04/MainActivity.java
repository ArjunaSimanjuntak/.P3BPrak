package com.example.m04;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.m04.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity{
    //fragment's
    FirstFragment fragment1;
    SecondFragment fragment2;
    FragmentManager fm;

    // binding's fragment
    ActivityMainBinding bindingMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);                                                  // dont use this setContentView when using binding


        Log.d("debug", "MAIN ACT onCrate() inflating bindingMain");
        // krn pake binding..
        this.bindingMain = ActivityMainBinding.inflate(this.getLayoutInflater());
        Log.d("debug", "                    habis inflate binding main");


        View layout = this.bindingMain.getRoot();
        this.setContentView(layout);
        Log.d("debug", "                    habis setContentView layout");


        Log.d("debug", "                    Instantiating fragment");
        // Instantiate the Fragment in Activity
        /*
        * Jika saat pembuatan fragment dibutuhkan pengiriman data tertentu, maka tidak bisa
            langsung menggunakan constructor yang kosong tersebut. Terdapat 2 alternatif yang dapat dilakukan yaitu :
                ● Overload constructor dengan constructor yang berparameter. (tidak disarankan)
                ● Membuat factory method. (design pattern factory
       */
        // 2. Instantiate the Fragment in Activity
        //this.fragment1 = FirstFragment.newInstance();                                               // Non-static method 'newInstance()' cannot be referenced from a static context
        Log.d("debug", "                    newInstance fragment1");
        this.fragment1 = FirstFragment.newInstance("New Fragment 1");
        Log.d("debug", "                    newInstance fragment2");
        this.fragment2 = SecondFragment.newInstance("New Fragment 2");



        Log.d("debug", "                    Instantiating fm, the manager now");
        // 3. Instantiate FragmentManager
        this.fm = this.getSupportFragmentManager();                                                 //variabel fm jadi atribut atau dibuat disini? variabel.. krn dipake di method lain
//        FragmentManager fm = this.getSupportFragmentManager();


        Log.d("debug", "                    Instantiating ft, transaction..");
        // 4. Use Fragment transaction
        FragmentTransaction ft = this.fm.beginTransaction();                                             // knp gapake f manager aja? ft mending dipake untuk yg ada / banyak conditional rumit nya, kl baru 1 baris aja, fm jg bisa

        Log.d("debug", "                                 ft, add..");
        ft.add(bindingMain.fragmentContainer.getId(), this.fragment1)                               // binding, jgn lupa ambl id pake getId()
                .addToBackStack(null)                                                               // untk nyimpan sementara fragment, pd back stack yg dikeluarkan saat tekan tombol back
                .commit();

        // panggil method untk change page
//        Log.d("debug", "                                 changePage(2)");
//        this.changePage(2);
//        Log.d("debug", "                                 changePage(1)");
//        this.changePage(1);


        Log.d("debug", "                                 siap-siap listen result");
        //                                                                                  ini sebuah anonymous class, bisa diubah ke lamda (->)
        fm.setFragmentResultListener("changePage", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Log.d("debug", "                                 masuk listener, requestKey kedengaran: mauKePage2");
                // String textBaru = result.getString("textBaru");                                  //conto dr ko keenan
//                MainActivity.this.bindingMain.tvMain


                int mauKePage = result.getInt("page");
                changePage(mauKePage);
            }
        });

        fm.setFragmentResultListener("mauSubmit", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Log.d("debug", "                                 masuk listener, requestKey kedengaran: mauSubmit");
                // String textBaru = result.getString("textBaru");                                  //conto dr ko keenan
//                MainActivity.this.bindingMain.tvMain


                int mauKePage = result.getInt("page");
                changePage(mauKePage);
            }
        });

        Log.d("debug", "MAIN ACT onCrate() end of oncreate");
    }


    // untk ganti halaman
    public void changePage(int page) {
        Log.d("debug", "MAIN ACT    changePage("+page+")");

        FragmentTransaction ft = this.fm.beginTransaction();
        if (page == 1) {                                                                            //ke fragment first
            ft.replace(this.bindingMain.fragmentContainer.getId(), this.fragment1)
                    .addToBackStack(null);
        } else if (page == 2) {
            ft.replace(this.bindingMain.fragmentContainer.getId(), this.fragment2)
                    .addToBackStack(null);
        }
        ft.commit();


        // changePage dari modul belum ditulis ulang semua disini
        




        Log.d("debug", "MAIN ACT    changePage("+page+").. end of changepage");
    }


}

/*
* Cobalah jalankan halaman ini, kemudian tekan tombol back. Apa yang terjadi? Mengapa?
* = krn saat onCreate mainact dijalankan, container fragment dijalankan, dan diisi fragment1. shng sewaktu menekan tombol back, isi fragment container menjadi kosong, jadi yg sebelumnya isi view ke3 (frame layout (si container)) diisi dengan fragment 1, menjadi kosong setelah di-back..
* Hapus method addToBackStack lalu coba untuk menekan tombol back. Apa yang terjadi?
* = langsung keluar dr aplikasi nya.
*
* Saat ini terdapat berapa fragment pada BackStack aplikasi?
* = 4
* Ketika kita menekan back 1 kali, fragment apa yang muncul? Berapa sisa fragment pada BackStack?
* = dia back ke fragment ke2, halaman utama (yg kosong), kemudian ditimpa fragment 1 di container, changepage 2 jd ditimpa fragmen 2, dan fragment 1 lagi.
* */