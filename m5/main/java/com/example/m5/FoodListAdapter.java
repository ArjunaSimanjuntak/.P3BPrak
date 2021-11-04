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
    private List<String> listItems;                                                                 //untk nyimpan data dr tiap baris (?)


    private Activity activity;
    //tambahan buat Prak m3, T02
    protected List<Food> foods;                                                                       // listFoods
                                                                                                    // jadi protected? supaya bisa dipake di viewholder?
    private ItemListFoodBinding bindingILFoods;

    protected MainPresenter presenter;                                                                // biar bisa dipake di viewholder jg
    private ActivityMainBinding bindingMain;                                                        //biar bisa resetAddForm() dr sini?


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
        this.foods = getFoods;
        notifyDataSetChanged();
    }

    //method 'add' (dr slide) (bisa diganti sesuai kebutuhan)
//    public void addLine(Food newFood) {
//                                                                                                    Log.d(TAG, "masuk addLine");
//        this.foods.add(newFood);
//        this.notifyDataSetChanged();
//    }

    // method tambahan buat pas nekan tombol sampah
//    private void removeFromList(Food food) {
//        this.foods.remove(food);                                                                // .remove(object) keluarannya boolean
//        notifyDataSetChanged();
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


    @Override
    public View getView(int i, View convertView, ViewGroup parent) {                                Log.d(TAG, "masuk getView");
        ViewHolder vh;

                                                                                                    // 1. What is exactly the function of the LayoutInflater? : The LayoutInflater takes your layout XML-files and creates different View-objects from its contents.
                                                                                                    // 2. Why do all the articles that I've read check if convertview is null or not first? What does it mean when it is null and what does it mean when it isn't? : The adapters are built to reuse Views, when a View is scrolled so that is no longer visible, it can be used for one of the new Views appearing. This reused View is the convertView. If this is null it means that there is no recycled View and we have to create a new one, otherwise we should use it to avoid creating a new.
                                                                                                    // 3. What is the parent parameter that this method accepts? : The parent is provided so you can inflate your view into that for proper layout parameters.
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
    public void updateList(List<Food> newFoods) {                                                      Log.d(TAG, "updateList: ");
        // update ?? this.listFoods.
        this.foods = newFoods;                                                                         // ambil list of foods dr presenter, dan set ke sini
        this.notifyDataSetChanged();
    }

    @Override
    public void resetAddForm() {                                                                    Log.d(TAG, "resetAddForm: ");
                                                                                                    // masuk kesini error, binding di viewholder error
//        this.bindingMain.etTitle.getText().clear();
//        this.bindingMain.etDetails.getText().clear();

//        this.bindingMain.etTitle.onEditorAction(EditorInfo.IME_ACTION_DONE);
        // sama kaya di main activity
    }

    //kelas ViewHolder
    private class ViewHolder implements View.OnClickListener, MainPresenter.IMainActivity{
        String TAG = "debug ViewHolder";
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
            this.presenter = FoodListAdapter.this.presenter;
        }

        public void updateView(Food food) {                                                         Log.d(TAG, "masuk updateView");
            this.food = food;

            // update disini ? di presenter?
            this.binding.tvTitleFoods.setText(food.getTitle());
            this.binding.tvDetailFoods.setText(food.getDetails());

        }

        @Override
        public void onClick(View view) {                                                            Log.d(TAG, "masuk onCLick vh");
            //cek isi food di view ini
            if (this.food == null) {
                Log.d(TAG, "isi food kosong");
            } else {
                Log.d(TAG, "isi food ada..");
                Log.d(TAG, "isi title food di view skrng,,  " + this.food.getTitle());
            }


            // ambil posisi si food yg diklik
            int positionFood = foods.indexOf(this.food);                                            // protected foods dr kelas adapter
                                                                                                    Log.d(TAG, "onClick: posisi: " + positionFood);

            if (view == this.binding.ivStar) {                                                      Log.d(TAG, "view adlh binding.ivStar");
                if (this.food.isFavorite()) {                                                       Log.d(TAG, "favoritenya aktif,, ngubah ke gaaktif");
                    this.binding.ivStar.setImageResource(android.R.drawable.btn_star_big_off);      // nyetel view biar mati

                    this.presenter.toggleFav(positionFood);                                                  //food yg ditekan ini urutan keberapa?
                    // this.food.setFavorite(false);                                                // kl pake togle bakal otomatis di set disana
                } else {                                                                            Log.d(TAG, "favoritenya gaaktif,, ngubah ke aktif");
                    this.binding.ivStar.setImageResource(android.R.drawable.btn_star_big_on);       // nyetel view biar nyala

                    this.presenter.toggleFav(positionFood);
                    // this.food.setFavorite(true);
                }
            }

            if (view == this.binding.ibBin) {                                                       Log.d(TAG, "view adlh binding.ibBin");
                // Food theFoodInQuestion = this.food;



                // dari StackOverFlow
                // https://stackoverflow.com/questions/2115758/how-do-i-display-an-alert-dialog-on-android
                new AlertDialog.Builder(view.getContext())
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")

                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {    // mksudnya apa new DialogInterface ??
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation

//                                removeFromList(theFoodInQuestion);                                          // knp gabisa manggil this.food dr sini ??
                                presenter.deleteList(positionFood);
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)                           // DialogInterface.cancel ??
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        }

        @Override
        public void updateList(List<Food> foods) {

        }

        @Override
        public void resetAddForm() {

        }
    }
}
