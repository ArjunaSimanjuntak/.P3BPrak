package com.example.m5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;

import com.example.m5.databinding.ActivityMainBinding;

import java.util.Arrays;
import java.util.List;

// M5
// ini modul 4 !!
public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainPresenter.IMainActivity {
    String TAG = "debug MainAct";

    private ActivityMainBinding bindingMain;

    private FoodListAdapter foodLAdaptr;
    private ListView lvFoods;

    private MainPresenter presenter;                                                                // ngaplikasi pake arsitektur MVP
                                                                                                    // we use this presenter object..
                                                                                                    // to listen the user input and update the data as well as view.

    //dr mock food
    public static String[] foodStringArr = { "Nasi Goreng Biasa", "Nasi Goreng Telor", "Nasi Goreng Ayam",
            "Nasi Goreng Sapi", "Nasi Goreng Seafood", "Mie Goreng Biasa", "Mie Goreng Telor", "Mie Goreng Ayam",
            "Mie Goreng Sapi", "Mie Goreng Seafood", "Baso", "Baso", "Baso", "Baso", "Baso", "Baso", "Baso", "Baso" };

    public static Food[] foodObjectArr = {
            new Food("Nasi Goreng","pake nasi"),                         // jd tambah false krn Food pake masukan itu
            new Food("Mie Goreng","pake mie"),
            new Food("Makanan 1","detail makanan 1"),
            new Food("Makanan 2","detail makanan 2"),
            new Food("Makanan 3","detail makanan 3"),
            new Food("Makanan 4","detail makanan 4"),
            new Food("Makanan 5","detail makanan 5")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

                                                                                                    Log.d(TAG, "onCreate: inflate binding");
        this.bindingMain = ActivityMainBinding.inflate(this.getLayoutInflater());
        View layout = bindingMain.getRoot();
        this.setContentView(layout);

                                                                                                    Log.d(TAG, "onCreate: initiate listview");
        this.lvFoods = this.bindingMain.lstFoods;
                                                                                                    Log.d(TAG, "onCreate: make object FoodListAdapter");
        this.foodLAdaptr = new FoodListAdapter(this);
                                                                                                    Log.d(TAG, "onCreate: konek adapter td sama listview");
        this.lvFoods.setAdapter(this.foodLAdaptr);

                                                                                                    Log.d(TAG, "onCreate: set button kalo diclick");
        this.bindingMain.buttonAdd.setOnClickListener(this);


        // mulai pake MVP architecture
        presenter = new MainPresenter(this);                                                    // presenter object, to listen to user inputs, so it can update data / view or do other things

        // load data dr mock food
        this.presenter.loadData(foodObjectArr);                                                     // kl mau jd list,, Arrays.asList()
    }


    @Override
    public void onClick(View view) {
        Log.d(TAG, "masuk onClick");

        // kalo tekan add...
        if (view == this.bindingMain.buttonAdd) {                                                   Log.d(TAG, "onclick, button add");

            String title = this.bindingMain.etTitle.getText().toString();
            String details = this.bindingMain.etDetails.getText().toString();


                                                                                                    Log.d(TAG, "title, detail: " + title + ", " + details);

            Food foodBaru = new Food(this.bindingMain.etTitle.getText().toString()
                    , this.bindingMain.etDetails.getText().toString());

            if (!title.equals("")) {                                                                Log.d(TAG, "addLine foodBaru");
//                this.foodLAdaptr.addLine(foodBaru);

                // pake presenter MVP
                this.presenter.addList(title, details);


                this.bindingMain.etTitle.getText().clear();
                this.bindingMain.etDetails.getText().clear();



                View viewCheck = this.getCurrentFocus();                                            //masukin ke view ini apa yg lg difokusin
                if (viewCheck != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);    //perlu import input method manager dan context
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }


        }
    }
//    @Override
//    public void onClick(View view) {
//                                                                                                    Log.d(TAG, "onClick: ");
//        if (view == this.bindingMain.buttonAdd) {
//                                                                                                    Log.d(TAG, "onClick: kl view = buttonAdd");
//            String title = this.bindingMain.etTitle.getText().toString();
//            String details = this.bindingMain.etDetails.getText().toString();
//
//            if (!title.equals("")) {                                                                Log.d(TAG, "onClick: kalo title gakosong");
//
//                this.presenter.addList(title, details);                                             //make presenter buat add
//            }
//        }
//    }


    // Override method" (abstrak) interface IMainActivity
    @Override
    public void updateList(List<Food> foods) {                                                      Log.d(TAG, "updateList: ");
        //
        this.foodLAdaptr.updateList(foods);                                                         // baru dari sini dioper lagi ke kelas adapter, dimana seharusnya diupdate
    }

    @Override
    public void resetAddForm() {                                                                    Log.d(TAG, "resetAddForm: ");
        // kosongin
//        this.foodLAdaptr.resetAddForm();

        this.bindingMain.etTitle.getText().clear();
        this.bindingMain.etDetails.getText().clear();

        this.bindingMain.etTitle.onEditorAction(EditorInfo.IME_ACTION_DONE);                        //krn gabisa ada view
    }

}