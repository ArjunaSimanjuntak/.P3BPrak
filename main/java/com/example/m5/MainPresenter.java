package com.example.m5;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// filled with 'UI logic'
public class MainPresenter {
    String TAG = "debug MainPresenter";
    protected List<Food> foods;                                                                     // (si model) struktur data penyimpanan, merepresentasikan daftar makanan
    protected IMainActivity ui;                                                                     // (si view) untuk memperbarui tampilan



    // constructor
    public MainPresenter (IMainActivity ui) {
        this.ui = ui;
        this.foods = new ArrayList<Food>();
    }


    public void loadData (Food[] arrFood) {                                                        // ambil data yg sdh dimasukkan dr MockFood
                                                                                                    Log.d(TAG, "masuk loadData");
        for (Food food : arrFood) {
                                                                                                    Log.d(TAG, "loadData: , food.gettitle(): "+ food.getTitle() + ", .getdetail(): " + food.getDetails());
            this.foods.add(food);
        }
    }

    public void deleteList (int position) {                                                         //hapus makanan dr struktur data sesuai dgn posisi yg dimasukkan
        this.foods.remove(position);
//        notifyDataSetChanged();
    }

    //khusus addList, panggil resetAddForm juga
    public void addList (String title, String detail) {                                             Log.d(TAG, "addList: baru masuk, Title: "+ title + ", Detail: " + detail + ",,,");
                                                                                                    //tambah makananbaru pd struktur data sesuai parameter. (favorite defaultnya false)
        Food newFood = new Food(title, detail);
        this.foods.add(newFood);

        this.ui.updateList(foods);
        this.ui.resetAddForm();                                                                     // pake si interface yg baru dibuat
        // biar masuk getView?

    }

    public void toggleFav (int position) {                                                          //toggle favorite
                                                                                                    //this.foods.get() kembaliin food
        if (this.foods.get(position).isFavorite()) {
            this.foods.get(position).setFavorite(false);
        }else {this.foods.get(position).setFavorite(true);}
    }

//    public void notifyObservers   // pake?


    // buat interface, untk update tampilan
    public interface IMainActivity {
        void updateList (List<Food> foods);

        void resetAddForm();                                                                            //kosongin input(edittext.clear) n tutup soft keyboard
    }
    // kl contract ??
}

