package com.example.m0617057;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.m0617057.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    protected ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==this.binding.btnStart.getId()){
            Intent intent = new Intent(this, Main2Activity.class);
            startActivity(intent);
        }
    }
}