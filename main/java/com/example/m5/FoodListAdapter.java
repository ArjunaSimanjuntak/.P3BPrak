package com.example.m5;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.m5.databinding.ActivityMainBinding;
import com.example.m5.databinding.ItemListFoodBinding;

import java.util.ArrayList;
import java.util.List;

//sebagai (container) sumber data untk ListView
//BaseAdapter .. blm bisa
public class FoodListAdapter extends BaseAdapter implements MainPresenter.IMainActivity{
    String TAG = "debug FoodListAdapt";
    private List<String> listItems;                 //untk nyimpan data dr tiap baris (?)


    private Activity activity;
    //tambahan buat Prak m3, T02
    private List<Food> foods;   // listFoods
    private ItemListFoodBinding bindingILFoods;

    private MainPresenter presenter;
    private ActivityMainBinding bindingMain;    //biar bisa resetAddForm() dr sini


    public FoodListAdapter(Activity actv) {
        //objk ref MainAct                          //harus pake this.. knp gabole lgsng activity = actv??? kan jelas" beda tulisan
        this.activity = actv;

        this.listItems = new ArrayList<String>();

        //baru
        this.foods = new ArrayList<Food>();


        // MVP
        // set presenter
        this.presenter = new MainPresenter(this);
    }

    // method dr diagram modul
    public void update (List<Food> getFoods) {
        this.presenter.ui.updateList(getFoods);
        notifyDataSetChanged();
    }

//    //method 'add' (dr slide) (bisa diganti sesuai kebutuhan)
//    public void addLine(Food newFood) {
//                                                                                                    Log.d(TAG, "masuk addLine");
//        this.foods.add(newFood);
//        this.notifyDataSetChanged();
//    }


    //implementasikan method getCount, getitem, getitemidm sesuai petunjuk
    @Override
    public int getCount() {
//        return 0;
//        return listItems.size();
        return foods.size();
    }

    @Override
    public Object getItem(int i) {
                                                                                                    Log.d(TAG, "masuk getItem");
//        return null;
//        return listItems.get(i);
        return foods.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    //untuk modul ini diisi dengan return 0)


    // method tambahan buat pas nekan tombol sampah
//    private void removeFromList(Food food) {
//        this.foods.remove(food);                                                                // .remove(object) keluarannya boolean
//        this.notifyDataSetChanged();
//    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {                                Log.d(TAG, "masuk getView");
        ViewHolder vh;

        if (convertView == null) {                                                                  Log.d("debug", "convertView kosong");
            bindingILFoods = ItemListFoodBinding.inflate(this.activity.getLayoutInflater());
            convertView = bindingILFoods.getRoot();
            vh = new ViewHolder(bindingILFoods);                                                    // vh dibuatkan
            convertView.setTag(vh);
        } else {                                                                                    Log.d("debug", "convertView ada");
                                                                                                    Log.d("debug", "set vh dengan getTag() convertVier");
            vh = (ViewHolder) convertView.getTag();                                                 // vh dibuatkan brdsrkn tag terpasang dr convertView
                                                                                                    Log.d("debug", "timpa view sekarang sama getRoot() dari vh.binding");
            convertView = vh.binding.getRoot();                                                     // getRoot ini isinya sama kaya baris 98? bindingILFoods.getRoot ??
        }
                                                                                                    Log.d("debug", "buat currentFood tipe Food");
        Food currentFood = (Food) this.getItem(i);                                                  //ambil dari list<Food> ke i
                                                                                                    Log.d("debug", "updateView.. ");
        vh.updateView(currentFood);

        return convertView;
    }

    // method dr interface (dr presenter)
    @Override
    public void updateList(List<Food> foods) {                                                      Log.d(TAG, "updateList: ");
        // update ?? this.listFoods.
        notifyDataSetChanged();
    }

    @Override
    public void resetAddForm() {                                                                    Log.d(TAG, "resetAddForm: ");
        this.bindingMain.etTitle.getText().clear();
        this.bindingMain.etDetails.getText().clear();

        this.bindingMain.etTitle.onEditorAction(EditorInfo.IME_ACTION_DONE);
        // sama kaya di main activity
    }

    //kelas ViewHolder
    private class ViewHolder implements View.OnClickListener {
        ItemListFoodBinding binding;

        // krn pake mvp
        MainPresenter presenter;

        public Food food;

        public ViewHolder(ItemListFoodBinding binding) {
                                                                                                    Log.d(TAG, "masuk View Holder");
            this.binding = binding;

            this.binding.ivStar.setOnClickListener(this);
            this.binding.ibBin.setOnClickListener(this);

            // set mvp presenter
//            this.presenter = new MainPresenter(this);
        }

        public void updateView(Food food) {
                                                                                                    Log.d(TAG, "masuk updateView");
            this.food = food;

            // update disini ? di presenter?
            this.binding.tvTitleFoods.setText(food.getTitle());
            this.binding.tvDetailFoods.setText(food.getDetails());

        }

        @Override
        public void onClick(View view) {
                                                                                                    Log.d(TAG, "masuk onCLick vh");
        }
    }
}
