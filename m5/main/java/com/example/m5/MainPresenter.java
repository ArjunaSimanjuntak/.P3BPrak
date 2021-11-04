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
    public MainPresenter (IMainActivity ui) {                                                       // or the 'View' as this parameter
        this.ui = ui;
        this.foods = new ArrayList<Food>();
    }



    public void loadData (Food[] arrFood) {                                                        // ambil data yg sdh dimasukkan dr MockFood
                                                                                                    Log.d(TAG, "masuk loadData");
        if (this.foods.isEmpty()) {
            for (Food food : arrFood) {
                                                                                                    Log.d(TAG, "loadData: , food.gettitle(): "+ food.getTitle() + ", .getdetail(): " + food.getDetails());
                this.foods.add(food);
            }
        }

        this.ui.updateList(foods);
    }

    public void deleteList (int position) {                                                         //hapus makanan dr struktur data sesuai dgn posisi yg dimasukkan
        Food theFood = this.foods.get(position);
        this.foods.remove(theFood);
//        this.foods.remove(position);

        this.ui.updateList(foods);                                                                  // waktu dipanggil dari main act, ini jadi ke main act bukan update di kelas adapter seharusnya
        this.ui.resetAddForm();
    }

    //khusus addList, panggil resetAddForm juga
    public void addList (String title, String detail) {                                             Log.d(TAG, "addList: baru masuk, Title: "+ title + ", Detail: " + detail + ",,,");
                                                                                                    //tambah makananbaru pd struktur data sesuai parameter. (favorite defaultnya false)
        Food newFood = new Food(title, detail);
        this.foods.add(newFood);

        this.ui.updateList(foods);                                                                  // waktu dipanggil dari main act, ini jadi ke main act bukan update di kelas adapter seharusnya
        this.ui.resetAddForm();                                                                     // pake si interface yg baru dibuat

    }

    public void toggleFav (int position) {                                                          //toggle favorite
//        Food theFood = this.foods.get(position);                                                    //this.foods.get() kembaliin food

//        if ( this.foods. ) {                                                // pake get (objek)
//            this.foods.get(position).setFavorite(false);
//        }else {this.foods.get(position).setFavorite(true);}


        if (this.foods.get(position).isFavorite()) {                                              // pake get (integer)
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

