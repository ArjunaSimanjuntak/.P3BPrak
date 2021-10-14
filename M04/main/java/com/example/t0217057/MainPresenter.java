package com.example.t0217057;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter {
    protected List<Food> foods;
    protected IMainActivity ui;

    public MainPresenter(IMainActivity ui) {
        this.ui = ui;
        this.foods = new ArrayList<Food>();
    }

    public void loadData(Food[] arrFood){

    }

    public void deleteList(int position){
        this.foods.remove(position);
    }

    public void addList(String title, String detail){
        Food newFood = new Food(title, detail, false);
        this.foods.add(newFood);
    }

    public void toogleFav(int position){
        if(this.foods.get(position).isFavourite()){
            this.foods.get(position).setFavourite(false);
        }
        else{
            this.foods.get(position).setFavourite(true);
        }
    }
}
