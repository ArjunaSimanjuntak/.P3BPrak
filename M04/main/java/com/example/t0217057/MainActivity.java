package com.example.t0217057;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ListView;

import com.example.t0217057.databinding.ActivityMainBinding;
import com.example.t0217057.databinding.ItemListFoodBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IMainActivity {
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
        Food food = new Food(binding.etTitle.getText().toString(), binding.etDetails.getText().toString(), false);
        if (view==binding.btnAdd){
            Log.d("debug", food.getTitle());
            this.adapter.addLine(food);
            //atau bisa juga seperti ini
            //binding.etTitle.setText("");
        }
    }

    @Override
    public void updateList(List<Food> foods) {

    }

    @Override
    public void resetAddForm() {
        binding.etTitle.getText().clear();
        binding.etDetails.getText().clear();
        binding.etTitle.onEditorAction(EditorInfo.IME_ACTION_DONE);
    }
}