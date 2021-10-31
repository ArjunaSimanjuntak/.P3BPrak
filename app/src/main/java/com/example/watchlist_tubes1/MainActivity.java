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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MoviePresenter.IMoviePresenter{
    private static final int WRITE_REQUEST_CODE = 1;
    String TAG = "debug MainAct";
    private ActivityMainBinding bindingMain;
    private DrawerLayout drawer;
    private HomeFragment homeFragment;
    private WishlistFragment wishlistFragment;
    private AddFilmFragment addFilmFragment;
    private FragmentManager fragmentManager;
    private DetailFragment detailFragment;
    private MoviePresenter presenter;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.bindingMain = ActivityMainBinding.inflate(this.getLayoutInflater());
        View layout = bindingMain.getRoot();
        setContentView(layout);


        this.presenter = new MoviePresenter(this);

        Toolbar toolbar = bindingMain.toolbar;                                                          // modul Praktikum_03 v2    & https://youtu.be/zYVEMCiDcmY
        setSupportActionBar(toolbar);

        drawer = bindingMain.drawerLayout;

        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.openDrawer, R.string.closeDrawer);
        drawer.addDrawerListener(abdt);
        abdt.syncState();

        NavigationView navigationView = bindingMain.navView;
        navigationView.setNavigationItemSelectedListener(this);


        this.homeFragment = new HomeFragment();
        this.wishlistFragment = new WishlistFragment();
        this.addFilmFragment = new AddFilmFragment();
        this.fragmentManager = this.getSupportFragmentManager();
        this.detailFragment = new DetailFragment();
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        ft.add(bindingMain.fragmentContainer.getId(), this.homeFragment)
                .addToBackStack(null).commit();


        this.getSupportFragmentManager().setFragmentResultListener("changePage", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                int page = result.getInt("page");
                changePage(page);
            }
        });






        // Permission                                                                               (cukup SEKALI)
//        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
//        this.requestPermissions(permissions, WRITE_REQUEST_CODE);

    }

    @Override
    public void onBackPressed() {
        if (this.drawer.isDrawerOpen(GravityCompat.START)) {
            this.drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        switch (item.getItemId()) {                                                                 // ? kl pake binding??
            case R.id.nav_home:                                                                     Log.d(TAG, "onNavigationItemSelected: item dr menu, 'landing page ditekan");
                changePage(1);
                break;
            case R.id.nav_filmlists:                                                                Log.d(TAG, "onNavigationItemSelected: item dr menu, 'page lists ditekan");
                changePage(2);
                break;
            case R.id.nav_exit:                                                                     Log.d(TAG, "onNavigationItemSelected: item dr menu, 'Exit ditekan");
                closeApplication();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
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
                                                                                                    Log.d("debug", "Masuk page 1 (landing page)");
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
                                                                                                    Log.d("debug", "masuk ke dalam page2 (wishlist fragment)");
            if(this.wishlistFragment.isAdded()){
                ft.show(this.wishlistFragment);
                wishlistFragment.updateListMovie(presenter.getMovies());                            // coba" pake presenter ambil movies, masi blm bisa
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
            if(this.detailFragment.isAdded()){
                ft.hide(this.detailFragment);
            }
        } else if(page==3){
                                                                                                    Log.d("debug", "masuk ke dalam page3 (addfilm fragment)");

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
                                                                                                    Log.d("debug", "masuk ke detail (page 4)");

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
                    Log.d("REQPERM", "Granted");
                } else {
                    Log.d("REQPERM", "Denied");
                }
                break;
        }


    }

    @Override
    public void updateListMovie(List<Movie> movieLists) {
        //
    }
}