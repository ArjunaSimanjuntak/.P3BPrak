package com.example.watchlist_tubes1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.watchlist_tubes1.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    String TAG = "debug MainAct";
    private ActivityMainBinding bindingMain;
    private DrawerLayout drawer;
    private HomeFragment homeFragment;
    private WishlistFragment wishlistFragment;
    private AddFilmFragment addFilmFragment;
    private FragmentManager fragmentManager;

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

        this.addFilmFragment = new AddFilmFragment();


        // fragment manager..
        this.homeFragment = new HomeFragment();                                                     // awal onCreate ini, add ke stack si landing Page, tp di awal oncreate aja, karna cuman landing page
        this.wishlistFragment = new WishlistFragment();
        this.fragmentManager = this.getSupportFragmentManager();
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
        switch (item.getItemId()) {                                                                 // ? kl pake binding??
            case R.id.nav_home:                                                                     Log.d(TAG, "onNavigationItemSelected: item dr menu, 'landing page ditekan");
                // ke landing page..
                break;
            case R.id.nav_filmlists:                                                                Log.d(TAG, "onNavigationItemSelected: item dr menu, 'page lists ditekan");
                // ke page film lists
                break;
            case R.id.nav_somepage:                                                                 Log.d(TAG, "onNavigationItemSelected: item dr menu, 'go to some page! ditekan");// ke fragment Some Page
                getSupportFragmentManager().beginTransaction().replace(this.bindingMain.fragmentContainer.getId(),
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
                ft.add(bindingMain.fragmentContainer.getId(), this.wishlistFragment);
            }
            if(this.homeFragment.isAdded()){
                ft.hide(this.homeFragment);
            }
        } else if(page==3){
            Log.d("debug", "masuk ke page add");
            if(this.addFilmFragment.isAdded()){
                ft.show(this.addFilmFragment);
            } else{
                ft.add(bindingMain.fragmentContainer.getId(), this.addFilmFragment).addToBackStack(null);
            }
            if(this.addFilmFragment.isAdded()){
                Log.d("debug", "wishliastFragment ke hide");
                ft.hide(this.wishlistFragment);
            }

        } else if(page==4){
            Log.d("debug", "ke click");
            closeApplication();
            Log.d("debug", "harusnya ke tutup");
        }
        ft.commit();
        Log.d("debug", "commit changePage");
    }
}