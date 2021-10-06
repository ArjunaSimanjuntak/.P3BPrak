package com.example.m0317057;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.m0317057.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private FirstFragment fragment1;
    private FragmentManager fragmentManager;
    private SecondFragment fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Inisialisasi fragment1 yang menggunakan method newInstance dari kelas FirstFragment
        this.fragment1 = FirstFragment.newInstance("New Fragment 1");
        this.fragment2 = SecondFragment.newInstance("New Fragment 2");
        //Instansiasi Fragment manager
        this.fragmentManager = this.getSupportFragmentManager();
        //Pakai Fragment Transaction
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        ft.add(binding.fragmentContainer.getId(), this.fragment1).addToBackStack(null).commit();
        this.changePage(2);
        this.changePage(1);

    }

    public void changePage(int page){
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if(page==1){
            ft.replace(binding.fragmentContainer.getId(), this.fragment1).addToBackStack(null);
        } else if(page==2){
            ft.replace(binding.fragmentContainer2.getId(), this.fragment2).addToBackStack(null);
        }
        ft.commit();
    }
}