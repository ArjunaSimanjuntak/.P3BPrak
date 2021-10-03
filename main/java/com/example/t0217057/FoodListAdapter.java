package com.example.t0217057;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
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
        Log.d("debug", "masuk add line");
        this.listItems.add(newItem);
        this.notifyDataSetChanged();
    }

    public void removeList(Food item){
        this.listItems.remove(item);
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
        Log.d("debug", "Masuk getView");
        /*ItemListFoodBinding binding = ItemListFoodBinding.inflate(this.activity.getLayoutInflater(), viewGroup, false);
        //View itemView = this.activity.getLayoutInflater().inflate(R.layout.item_list_food, null);
        Food currentFood = (Food)this.getItem(i);
        View itemView = binding.getRoot();

        //1. Title
        TextView tvTitle = binding.tvListFood;
        tvTitle.setText(currentFood.getTitle());

        //2. Details
        TextView tvDetails = binding.tvListDetail;
        tvDetails.setText(currentFood.getDetails());

        //3. isFavourite
        if(currentFood.isFavourite()) {
            binding.ivBtnStar.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            binding.ivBtnStar.setImageResource(android.R.drawable.btn_star_big_off);
        }

        return itemView;*/



        //Menggunakan RecycleView(membutuhkan viewHolder)
        ViewHolder viewHolder;
        ItemListFoodBinding binding;
        Food currentFood = (Food)this.getItem(i);

        if(view==null){
            binding = ItemListFoodBinding.inflate(this.activity.getLayoutInflater(), viewGroup, false);
            view = binding.getRoot();
            viewHolder = new ViewHolder(binding);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.updateView(currentFood);

        return view;
    }

    private class ViewHolder implements View.OnClickListener {
        ItemListFoodBinding binding;
        Food currentFood;

        public ViewHolder(ItemListFoodBinding binding){
            Log.d("debug", "masuk ViewHolder");
            this.binding = binding;

            binding.ivBtnStar.setOnClickListener(this);
            binding.ibBtnDelete.setOnClickListener(this);
        }
        //Method updateView untuk set nilai dari informasi yang sudah ada(dalam kasus ini dari currentFood)
        public void updateView(Food currentFood){
            Log.d("debug", "masuk updateView");
            binding.tvListFood.setText(currentFood.getTitle());
            binding.tvListDetail.setText(currentFood.getDetails());
            this.currentFood = currentFood;
        }

        @Override
        public void onClick(View view) {
            Log.d("debug", "masuk onclick");
            if(view==binding.ivBtnStar){
                Log.d("debug", "masuk condition if view == ivBTN");
                if(currentFood.isFavourite()) {
                    Log.d("debug", "masuk condition if true");
                    currentFood.setFavourite(false);
                    binding.ivBtnStar.setImageResource(android.R.drawable.btn_star_big_off);

                } else{
                    Log.d("debug", "masuk condition if false");
                    currentFood.setFavourite(true);
                    binding.ivBtnStar.setImageResource(android.R.drawable.btn_star_big_on);

                }
            }
            else if(view==binding.ibBtnDelete){
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Delete Item");
                builder.setMessage("Are you sure want to delete this item?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        removeList(currentFood);
                    }
                });
                //cara 1
                builder.setNegativeButton("No", null);
                //cara 2
                /*builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });*/
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                /*currentFood.isFavourite = false;*/
            }
        }
    }
}
