package com.example.m5;

import android.util.Log;

import java.util.Arrays;
import java.util.List;

public class MainPresenter {
    protected List<Food> foods;                                                                     //struktur data penyimpanan, merepresentasikan daftar makanan
    protected IMainActivity ui;                                                                     // untuk memperbarui tampilan

    public void loadData (Food[] arrFood) {                                                        // ambil data yg sdh dimasukkan dr MockFood
        Log.d("debug", "masuk initList");
        for (Food food : arrFood) {
            this.foods.add(food);
        }
    }

    public void deleteList (int position) {                                                         //hapus makanan dr struktur data sesuai dgn posisi yg dimasukkan
        this.foods.remove(position);
        //notifyDataSetChanged();
    }

    //khusus addList, panggil resetAddForm juga
    public void addList (String title, String detail) {                                             //tambah makananbaru pd struktur data sesuai parameter. (favorite defaultnya false)
        Food newFood = new Food(title, detail, false);
        this.foods.add(newFood);

        this.ui.resetAddForm();                                                                     // pake si interface yg baru dibuat
    }

    public void toggleFav (int position) {                                                          //toggle favorite
                                                                                                    //this.foods.get() kembaliin food
        if (this.foods.get(position).isFavorite()) {
            this.foods.get(position).setFavorite(false);
        }else {this.foods.get(position).setFavorite(true);}
    }


    // buat interface, untk update tampilan
    public interface IMainActivity {
        void updateList (List<Food> foods);

        void resetAddForm();                                                                            //kosongin input(edittext.clear) n tutup soft keyboard
    }
}

