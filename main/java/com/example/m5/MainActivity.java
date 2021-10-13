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

import java.util.List;

// M5
// ini modul 4 !!
public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainPresenter.IMainActivity {

    private ActivityMainBinding bindingMain;

    private FoodListAdapter foodLAdaptr;
    private ListView lvFoods;

    String TAG = "TAG";


    // ngaplikasi pake arsitektur MVP
    private MainPresenter presenter;


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


    }

    @Override
    public void onClick(View view) {
                                                                                                    Log.d(TAG, "onClick: ");
        if (view == this.bindingMain.buttonAdd) {
                                                                                                    Log.d(TAG, "onClick: kl view = buttonAdd");
            String title = this.bindingMain.etTitle.getText().toString();
            String details = this.bindingMain.etDetails.getText().toString();

            this.presenter.addList(title, details);                                                 //make presenter buat add

//            Food FoodBaru = new Food(title,
//                                    details,
//                            false);

            if (!title.equals("")) {                                                                Log.d(TAG, "onClick: kalo title gakosong");
//                this.foodLAdaptr.addLine(FoodBaru);

                // kosongin
                this.bindingMain.etTitle.getText().clear();
                this.bindingMain.etDetails.getText().clear();

                // challenge, hilangin soft keyboard nya habis itu
                // ini sebenarnya ngapain (?)
                // Check if no view has focus:
                View viewCheck = this.getCurrentFocus();                                            //masukin ke view ini apa yg lg difokusin
                if (viewCheck != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);    //perlu import input method manager dan context
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        }
    }


    // Override method" (abstrak) interface IMainActivity
    @Override
    public void updateList(List<Food> foods) {

    }

    @Override
    public void resetAddForm() {
        // kosongin
        this.bindingMain.etTitle.getText().clear();
        this.bindingMain.etDetails.getText().clear();

        this.bindingMain.etTitle.onEditorAction(EditorInfo.IME_ACTION_DONE);                        //krn gabisa ada view
    }
}