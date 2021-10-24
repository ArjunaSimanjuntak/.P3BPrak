package com.example.m0517057;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.m0517057.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding binding;
    PenyimpananNilaiDisplay penyimpananNilaiDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        penyimpananNilaiDisplay = new PenyimpananNilaiDisplay(getBaseContext());
        binding.btnAdd.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.penyimpananNilaiDisplay.saveBarang(binding.etBarang.getText().toString(),
                binding.etHarga.getText().toString(), binding.etKeterangan.getText().toString());
        /*this.penyimpananNilaiDisplay.saveHarga(binding.etHarga.getText().toString());
        this.penyimpananNilaiDisplay.saveKet(binding.etKeterangan.getText().toString());*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.binding.etBarang.setText(this.penyimpananNilaiDisplay.getBarang());
        this.binding.etHarga.setText(this.penyimpananNilaiDisplay.getHarga());
        this.binding.etKeterangan.setText(this.penyimpananNilaiDisplay.getKet());
    }

    @Override
    public void onClick(View view) {

    }
}