package com.example.watchlist_tubes1;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

import com.example.watchlist_tubes1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.binding = ActivityMainBinding.inflate(this.getLayoutInflater());                       // binding
        View layout = binding.getRoot();
        setContentView(layout);


        Toolbar toolbar = binding.toolbar;                                                          // modul Praktikum_03 v2    & https://youtu.be/zYVEMCiDcmY
        setSupportActionBar(toolbar);

        drawer = binding.drawerLayout;

        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.openDrawer, R.string.closeDrawer);
        drawer.addDrawerListener(abdt);
        abdt.syncState();
    }

    @Override
    public void onBackPressed() {                                                                   // kl nekan back button
        if (this.drawer.isDrawerOpen(GravityCompat.START)) {                                        // n drawer lg kebukak
            this.drawer.closeDrawer(GravityCompat.START);                                           // tutup
        } else {                                                                                    // kl engga, back kaya biasa
            super.onBackPressed();
        }
    }
}