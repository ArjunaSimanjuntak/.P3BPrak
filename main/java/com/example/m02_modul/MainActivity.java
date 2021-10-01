package com.example.m02_modul;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import com.example.m02_modul.databinding.ActivityMainBinding;
import com.example.m02_modul.databinding.ItemListStringBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityMainBinding bindingLMain;           //variabel untuk layout activity main
    private ItemListStringBinding bindingLItemList;     //variabel untuk layout binding item_list_string
    private FoodListAdapter FoodListAdptr;
    private ListView lvFoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        this.bindingLMain = ActivityMainBinding.inflate(this.getLayoutInflater());

        View layout = bindingLMain.getRoot();
        this.setContentView(layout);

        String[] foods = {"Nasi Goreng Biasa","Nasi Goreng Telor","Nasi Goreng Ayam","Nasi Goreng Sapi",
                "Nasi Goreng Seafood","Mie Goreng Biasa","Mie Goreng Telor","Mie Goreng Ayam",
                "Mie Goreng Sapi","Mie Goreng Seafood","Baso","Baso","Baso","Baso","Baso","Baso","Baso","Baso"};
        //this.bindingLMain.lstFoods.setAdapter(new ArrayAdapter<String>(this, R.layout.item_list_string, foods));    //?

        //inisialisasi list view
        this.lvFoods = this.bindingLMain.lstFoods;

        //buat objek foodlistadapt..
        this.FoodListAdptr = new FoodListAdapter(this);



        //Sambungkan FoodListAdapter tersebut dengan ListView-nya
        this.lvFoods.setAdapter(this.FoodListAdptr);

        //Tambahan pada onCreate dari Activity perintah-perintah untuk menambahkan baris berisi “nasi goreng”, “baso”, dan “pisang goreng”
        //  pake init list...
        //(krn pake base adapter, di initList dulu)
        //this.FoodListAdptr = initList(foods);
        this.FoodListAdptr.initList(foods);

        //  or.. masukin satu" disini
//        for(int i=0; i< foods.length; i++) {
//            Log.d("debug", "added line with: "+foods[i]);
//            this.FoodListAdptr.addLine(foods[i]);       //(?)
//        }



        //skrng inisialisasi tombol" lain biar berfungsi
        bindingLMain.buttonAdd.setOnClickListener(this);






//        ---------------------
        //Array Adapter
//        this.lvWords.setAdapter(new ArrayAdapter<String>(this, R.layout.item_word, wordArr));

        //Custom Adapter    /base adapter
//        this.adapter = new WordAdapter(this);
//        this.adapter = initList(wordArr);
//        this.lvWords.setAdapter(this.adapter);

        //Grid View
//        GridView.gridview = this.findViewById(R.id.gridView);
//        gridView.setAdapter(this.adapter);
    }

    @Override
    public void onClick(View view) {
        Log.d("debug", "masuk onClick");

        if(view == this.bindingLMain.buttonAdd) {
            Log.d("debug", "onclick, button add");

            String title = this.bindingLMain.etTitle.getText().toString();
            String details = this.bindingLMain.etDetails.getText().toString();


            Log.d("debug", "title, detail: " + title + ", " + details);

            Food foodBaru = new Food(this.bindingLMain.etTitle.getText().toString()
                                    , this.bindingLMain.etDetails.getText().toString()
                                    , false);

            //== and != work on object identity. While the two Strings have the same value, they are actually two different objects.
            if(!title.equals("")) {
                Log.d("debug", "addLine foodBaru");
                this.FoodListAdptr.addLine(foodBaru);
                                                            //details taro dimana??
            }
        }
    }
}