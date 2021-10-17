package com.example.t0317057;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.example.t0317057.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private MainFragment mainFragment;
    private FragmentManager fragmentManager;
    private SecondFragment secondFragment;
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        this.mainFragment = new MainFragment();
        this.secondFragment = new SecondFragment();
        Log.d("debug", "main sec frag inisiasi done");
        this.fragmentManager = this.getSupportFragmentManager();
        Log.d("debug", "FM done");
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        Log.d("debug", "FT done");
        ft.add(binding.fragmentContainer.getId(), this.mainFragment).addToBackStack(null).commit();
        Log.d("debug", "commit mainFrag");

        //Memasang Custom Toolbar
        this.setSupportActionBar(binding.toolbar);
        Log.d("debug", "masang custom toolbar");
        //Tombol garis tiga atau Hamburger button
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.openDrawer, R.string.closedDrawer);
        Log.d("debug", "Buat tombol hamburger baru");
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle);
        Log.d("debug", "nambah listener baru di tombol hamburger");
        actionBarDrawerToggle.syncState();
        Log.d("debug", "syncState");

        //Fragment Result API for communicating between Activity - MainFragment - SecondFragment
        this.getSupportFragmentManager().setFragmentResultListener("changePage", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                int page = result.getInt("page");
                changePage(page);
            }
        });
    }

    public void changePage(int page){
        Log.d("debug", "masuk method changePage");
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if(page==1){
            //Menggunakan show and hide
            if(this.mainFragment.isAdded()){
                ft.show(this.mainFragment);
            }
            else{
                ft.add(binding.fragmentContainer.getId(), this.mainFragment);
            }
            if(this.secondFragment.isAdded()){
                ft.hide(this.secondFragment);
            }

        } else if(page==2){
            //Menggunakan methode show and hide
            if(this.secondFragment.isAdded()){
                ft.show(this.secondFragment);
            }
            else{
                ft.add(binding.fragmentContainer.getId(), this.secondFragment).addToBackStack(null);
            }
            if(this.mainFragment.isAdded()){
                ft.hide(this.mainFragment);
            }
        }
        ft.commit();
        Log.d("debug", "commit changePage");
    }

    public void closeApplication(){
        this.moveTaskToBack(true);
        this.finish();
    }
}