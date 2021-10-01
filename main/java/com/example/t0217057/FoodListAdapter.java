package com.example.t0217057;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.t0217057.databinding.ActivityMainBinding;
import com.example.t0217057.databinding.ItemListFoodBinding;

import java.util.ArrayList;
import java.util.List;


public class FoodListAdapter extends BaseAdapter {
    private List<Food> listItems;
    private Activity activity;

    public FoodListAdapter(MainActivity activity) {
        this.activity = activity;
        this.listItems = new ArrayList<Food>();
    }

    public void addLine(Food newItem){
        this.listItems.add(newItem);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int i) {
        return listItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        /*//Menggunakan RecycleView(membutuhkan viewHolder)
        ViewHolder viewHolder;
        ItemListFoodBinding binding;
        ActivityMainBinding binding2;


        if(view==null){
            binding = ItemListFoodBinding.inflate(this.activity.getLayoutInflater(), viewGroup, false);
            binding2 = ActivityMainBinding.inflate(this.activity.getLayoutInflater());
            view = binding.getRoot();
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.updateView(this.getItem(i).toString(), this.getItem(i).toString());

        return view;*/
    }

    /*private class ViewHolder implements View.OnClickListener {
        protected ItemListFoodBinding binding;
        Food foodList;

        public ViewHolder(View view){
            this.binding = binding;
        }

        public void updateView(String food, String details){
            binding.tvListFood.setText(food);
            binding.tvListDetail.setText(details);
        }

        @Override
        public void onClick(View view) {
            if(view==binding.ivBtnStar){
                binding.ivBtnStar.setImageResource(android.R.drawable.btn_star_big_on);
            }
        }
    }*/
}
