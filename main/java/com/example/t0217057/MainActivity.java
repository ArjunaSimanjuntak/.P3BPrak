package com.example.t0217057;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.t0217057.databinding.ActivityMainBinding;
import com.example.t0217057.databinding.ItemListFoodBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;
    private ListView listView;
    private FoodListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        this.listView = binding.lstFoods;
        this.adapter = new FoodListAdapter(this);
        this.listView.setAdapter(this.adapter);

        binding.btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view==binding.btnAdd){
            this.adapter.addLine((Food) binding.etTitle.getText());
            binding.etTitle.getText().clear();
            binding.etDetails.getText().clear();
            //atau bisa juga seperti ini
            //binding.etTitle.setText("");
        }
    }
}