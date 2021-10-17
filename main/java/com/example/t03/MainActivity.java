package com.example.t03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.t03.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ActivityMainBinding bindingMain;

    DrawerLayout drawer;
    RightFragment fragmentRight;
    LeftFragment fragmentLeft;
    MainFragment fragmentMain;
    Toolbar toolbar;
    ResultDialogFragment rdf;

    FragmentManager fm;                                                                             //mending jd atribut?

    // untk debug
    String TAG = "debug MainAct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
                                                                                                    Log.d(TAG, "onCreate:   baru masuk");
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
                                                                                                    Log.d(TAG, "onCreate:   inflate binding main");
        this.bindingMain = ActivityMainBinding.inflate(this.getLayoutInflater());                   // pake binding
        View layout = this.bindingMain.getRoot();
        this.setContentView(layout);                                                                Log.d(TAG, "onCreate:   sambungin drawer layout");

        // sambungin drawer
        this.drawer = bindingMain.drawerLayout;                                                     // dr idnya drawer_layout
                                                                                                    Log.d(TAG, "onCreate:   sambungin fragment");
        // sambungin fragment
        this.fragmentMain = MainFragment.newInstance();
        this.fragmentRight = RightFragment.newInstance();
        this.fragmentLeft = LeftFragment.newInstance();

        this.fm = this.getSupportFragmentManager();

        //TextView tv = findViewById(R.id.tvResult);

        FragmentTransaction ft = this.fm.beginTransaction();


                                                                                                    // supaya awal dibuka app, ini ditampilkan awal
        if (savedInstanceState == null) {                                                           // to avoid re-'creating' this fragment when rotating, so we with this, by rotating our savedinstance wont be null
            ft.add(this.bindingMain.fragmentContainer.getId(), this.fragmentMain)                   // but this means when we rotate (it destroyed and recreated, n this will be called again
                    .addToBackStack(null)
                    .commit();
        }


        //
                                                                                                    Log.d(TAG, "onCreate:   pasang toolbar");
        // pasang toolbar sbg actionbar
        this.toolbar = bindingMain.toolbar;
        this.setSupportActionBar(this.toolbar);

        // nambah tombol 'hamburger' (ga mirip) (tombol menu biar buka nav bar nya)
        //                                                                  gaperlu this?               untuk apa?
        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.openDrawer, R.string.closeDrawer);
        drawer.addDrawerListener(abdt);
        abdt.syncState();                                                                           // "take care of rotating the hamburger together drawer itself"



        // untk menu berfungsi,
        // krn pake Navigation Drawer, sama kaya button di activity, kita listen kl ditekan
        NavigationView navigationView = bindingMain.navView;
        navigationView.setNavigationItemSelectedListener(this);                                     // bisa set anonymous funciton /callback disini untk tiap ditekan new NavigationView.OnNavigationItemSelectedListener() { ....
                                                                                                    // tp biar rapi, kita pake this (we pass this mainact), jd mesti implement lagi

                                                                                                    Log.d(TAG, "onCreate:   end of oncreate");
    }

    //
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {                                                                 // ? kl pake binding??
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(this.bindingMain.fragmentContainer.getId(),
                        new MainFragment()).commit();
                break;
            case R.id.nav_page2:
                getSupportFragmentManager().beginTransaction().replace(this.bindingMain.fragmentContainer.getId(),
                        new SecondFragment()).commit();
                break;
            case R.id.nav_exit:
                // 'are u sure'?
                // keluar aplikasi
                closeApplication();
                break;
        }

        // then we close our drawer
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

                                                                                                    // so when we go back, we dont leave the activity immediately, we just close our navigation drawer
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {                                             // kl mau tutup drawer yg sblh kanan, GravityCompat.END
            drawer.closeDrawer(GravityCompat.START);
        } else {                                                                                    // kl sdh tertutup, baru close activity nya
            super.onBackPressed();
        }
    }

    public void closeApplication () {
        this.moveTaskToBack(true);
        this.finish();
    }


    // kl biar pake ini gimana?
    public void changePage (int page) {
        FragmentTransaction ft = this.fm.beginTransaction();
        if (page == 1) {
//            if (this.fragmentLeft.isAdded()) {
//                ft.show(this.fragmentLeft);
//            }else {
//                ft.add(this.bindingMain.fragmentContainer, this.fragmentLeft);
//            }
//            if (this.fragmentRight.isAdded()) {
//                ft.hide(this.fragmentRight);
//            }
        }else if (page == 2) {

        }
        ft.commit();
    }

}