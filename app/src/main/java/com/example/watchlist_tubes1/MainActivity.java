package com.example.watchlist_tubes1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.watchlist_tubes1.databinding.ActivityMainBinding;
import com.example.watchlist_tubes1.databinding.FragmentDaftarWatchlistBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private static final int WRITE_REQUEST_CODE = 1;
    String TAG = "debug MainAct";
    private ActivityMainBinding bindingMain;
    private DrawerLayout drawer;
    private HomeFragment homeFragment;
    private WishlistFragment wishlistFragment;
    private AddFilmFragment addFilmFragment;
    private FragmentManager fragmentManager;
    private DetailFragment detailFragment;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.bindingMain = ActivityMainBinding.inflate(this.getLayoutInflater());                       // binding
        View layout = bindingMain.getRoot();
        setContentView(layout);



        Toolbar toolbar = bindingMain.toolbar;                                                          // modul Praktikum_03 v2    & https://youtu.be/zYVEMCiDcmY
        setSupportActionBar(toolbar);

        drawer = bindingMain.drawerLayout;

        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.openDrawer, R.string.closeDrawer);
        drawer.addDrawerListener(abdt);
        abdt.syncState();

        NavigationView navigationView = bindingMain.navView;                                        // ambil navview supaya item" di menu dalem nav view bisa dipake
        navigationView.setNavigationItemSelectedListener(this);                                     // jd implement onnavigation blabla


        // fragment manager..
        this.homeFragment = new HomeFragment();                                                     // awal onCreate ini, add ke stack si landing Page, tp di awal oncreate aja, karna cuman landing page
        this.wishlistFragment = new WishlistFragment();
        this.addFilmFragment = new AddFilmFragment();
        this.fragmentManager = this.getSupportFragmentManager();
        this.detailFragment = new DetailFragment();
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        ft.add(bindingMain.fragmentContainer.getId(), this.homeFragment)
                .addToBackStack(null).commit();

        //Fragment Result API changePage()
        this.getSupportFragmentManager().setFragmentResultListener("changePage", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                int page = result.getInt("page");
                changePage(page);
            }
        });






        // (?)
        // Permission                                                                               (cukup SEKALI)
//        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};                        // dan override onrequest permission result.. dan import package manager nya lagi                                                     // dan override onrequest permission result.. dan import package manager nya lagi
//        this.requestPermissions(permissions, WRITE_REQUEST_CODE);                                   // dan... tmbh permission di manifest

    }

    @Override
    public void onBackPressed() {                                                                   // kl nekan back button
        if (this.drawer.isDrawerOpen(GravityCompat.START)) {                                        // n drawer lg kebukak
            this.drawer.closeDrawer(GravityCompat.START);                                           // tutup
        } else {                                                                                    // kl engga, back kaya biasa
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        switch (item.getItemId()) {                                                                 // ? kl pake binding??
            case R.id.nav_home:                                                                     Log.d(TAG, "onNavigationItemSelected: item dr menu, 'landing page ditekan");
                // ke landing page..
                //                                                                                  ft.replace(this.bindingMain.fragmentContainer.getId(), new HomeFragment()).commit();
                changePage(1);
                break;
            case R.id.nav_filmlists:                                                                Log.d(TAG, "onNavigationItemSelected: item dr menu, 'page lists ditekan");
                // ke page film lists
                //                                                                                  ft.replace(this.bindingMain.fragmentContainer.getId(), new WishlistFragment()).commit();
                changePage(2);
                break;
            case R.id.nav_somepage:                                                                 Log.d(TAG, "onNavigationItemSelected: item dr menu, 'go to some page! ditekan");// ke fragment Some Page
                ft.replace(this.bindingMain.fragmentContainer.getId(),
                        new SomePage()).commit();
                break;
            case R.id.nav_exit:                                                                     Log.d(TAG, "onNavigationItemSelected: item dr menu, 'Exit ditekan");
                closeApplication();                                                                 // keluar aplikasi
                break;
        }



        drawer.closeDrawer(GravityCompat.START);                                                    // terus tutup lg drawer nya
        return true;
    }

    public void closeApplication () {
        this.moveTaskToBack(true);
        this.finish();
    }

    public void changePage(int page){
        Log.d("debug", "masuk method changePage");
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if(page==1){
            Log.d("debug", "Masuk page 1");
            //Menggunakan show and hide
            if(this.homeFragment.isAdded()){
                ft.show(this.homeFragment);
            }
            else{
                ft.add(bindingMain.fragmentContainer.getId(), this.homeFragment);
            }
            if(this.wishlistFragment.isAdded()){
                ft.hide(this.wishlistFragment);
            }

        } else if(page==2){
            Log.d("debug", "masuk ke dalam page2");
            //Menggunakan methode show and hide
            if(this.wishlistFragment.isAdded()){
                ft.show(this.wishlistFragment);
            }
            else{
                ft.add(bindingMain.fragmentContainer.getId(), this.wishlistFragment).addToBackStack(null);
            }
            if(this.homeFragment.isAdded()){
                ft.hide(this.homeFragment);
            }
            if(this.addFilmFragment.isAdded()){
                ft.hide(this.addFilmFragment);
            }
        } else if(page==3){
            Log.d("debug", "masuk ke dalam page2");
            //Menggunakan methode show and hide
            if(this.addFilmFragment.isAdded()){
                ft.show(this.addFilmFragment);
            }
            else{
                ft.add(bindingMain.fragmentContainer.getId(), this.addFilmFragment).addToBackStack(null);
            }
            if(this.wishlistFragment.isAdded()) {
                ft.hide(this.wishlistFragment);
            }

        }
        else if(page==4){
            Log.d("debug", "masuk ke detail");
            //Menggunakan methode show and hide
            if (this.detailFragment.isAdded()) {
                ft.show(this.detailFragment);
            } else {
                ft.add(bindingMain.fragmentContainer.getId(), this.detailFragment).addToBackStack(null);
            }
            if (this.wishlistFragment.isAdded()) {
                ft.hide(this.wishlistFragment);
            }
        }
        ft.commit();
        Log.d("debug", "commit changePage");
    }




    // untk permission
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