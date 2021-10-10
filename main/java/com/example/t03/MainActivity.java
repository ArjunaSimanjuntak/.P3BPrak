package com.example.t03;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.t03.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding bindingMain;

    DrawerLayout drawer;
    RightFragment fragmentRight;
    LeftFragment fragmentLeft;
    MainFragment fragmentMain;
    Toolbar toolbar;

    FragmentManager fm;                                                                             //mending jd atribut?

    // untk debug
    String TAG = "debug";

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

        FragmentTransaction ft = this.fm.beginTransaction();

        ft.add(this.bindingMain.fragmentContainer.getId(), this.fragmentMain)
                .addToBackStack(null)
                .commit();
        //
                                                                                                    Log.d(TAG, "onCreate:   pasang toolbar");
        // pasang toolbar sbg actionbar
        this.toolbar = bindingMain.toolbar;
        this.setSupportActionBar(this.toolbar);

        // nambah tombol 'hamburger' (ga mirip) (tombol menu biar buka nav bar nya)
        //                                                                  gaperlu this?               untuk apa?
        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawer.addDrawerListener(abdt);
        abdt.syncState();

                                                                                                    Log.d(TAG, "onCreate:   end of oncreate");
    }

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