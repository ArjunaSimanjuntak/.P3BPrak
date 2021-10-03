package com.example.m02_modul;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.m02_modul.databinding.ItemListFoodBinding;
import com.example.m02_modul.databinding.ItemListStringBinding;

import java.util.ArrayList;
import java.util.List;

//sebagai (container) sumber data untk ListView
                                    //BaseAdapter .. blm bisa
public class FoodListAdapter extends BaseAdapter {
    private List<String> listItems;                 //untk nyimpan data dr tiap baris (?)
    private Activity activity;

    //tambahan buat Prak m3, T02
    private List<Food> listFoods;

    //ditaro di getView
    private ItemListFoodBinding bindingILFoods;


    public FoodListAdapter (Activity actv) {
        //objk ref MainAct                          //harus pake this.. knp gabole lgsng activity = actv??? kan jelas" beda tulisan
        this.activity = actv;                       // agar kita dapat memperoleh dan menyimpan sebuah objek dari kelas LayoutInflater.
                                                    //Objek ini akan kita gunakan pada waktu kita perlu meng-inflate sebuah item_list_string.xml.
        this.listItems = new ArrayList<String>();

        //baru
        this.listFoods = new ArrayList<Food>();

        //inflate binding..
        // krn di adapter inflate nya di get view
        //this.bindingILFoods = ItemListFoodBinding.inflate(this.activity.getLayoutInflater());

    }

    //kl extends BaseAdapter, mesti pake initList dulu
    //agar nambah data ke linked list
    public void initList( String[] words){          //@NotNull gabisa dipake(?)
        Log.d("debug", "masuk initList");
        for(String word : words) {
            this.listItems.add(word);
        }
    }

    //method 'add' (dr slide) (bisa diganti sesuai kebutuhan)
    public void addLine (Food newFood) {
        Log.d("debug", "masuk addLine");
                                        //this.listItems.add(newItem);
        this.listFoods.add(newFood);
        this.notifyDataSetChanged();    //untuk 'refresh', krn perlu ngenotif tiap buat perubahan
                                        //Hal ini bertujuan agar ListView yang terhubung dengan Adapter ini meng-update tampilannya.
    }


    //implementasikan method getCount, getitem, getitemidm sesuai petunjuk
    @Override
    public int getCount() {
//        return 0;
//        return listItems.size();
        return listFoods.size();
    }

    @Override
    public Object getItem(int i) {
        Log.d("debug", "masuk getItem");
//        return null;
//        return listItems.get(i);
        return listFoods.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    //untuk modul ini diisi dengan return 0)



//    @Override   //ga dipake,,
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        //iflate layout item_list_string, buat jd objek
//        ItemListStringBinding binding = ItemListStringBinding.inflate(this.activity.getLayoutInflater());
//
//        //tanpa binding
//        //View itemView = this.activity.getLayoutInflater().inflate(R.layout.item_list_string, null);
//
//        //ngambil layout nya
//        View itemView = binding.getRoot();
//
//        //inisialisasi/masukin anak" layout itu
//        TextView tvListFood = itemView.findViewById(R.id.tvforListV);
//        tvListFood.setText(this.listItems.get(i));
//
//        //return layout
//        return itemView;
//    }


    //getView untk T02
    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        Log.d("debug", "masuk getView");
        //pake binding
        //ItemListFoodBinding bindingILFoods;           //dari atribut kelas ini aja? pas di else gabisa di inisialisasi, kurang jauh scope nya
        ViewHolder vh;

        if(convertView == null) {
            Log.d("debug", "convertView kosong");
            bindingILFoods = ItemListFoodBinding.inflate(this.activity.getLayoutInflater());
            convertView = bindingILFoods.getRoot();
            vh = new ViewHolder(bindingILFoods);
            convertView.setTag(vh);
        } else {
            Log.d("debug", "convertView ada");

            Log.d("debug", "set vh dengan getTag() convertVier");
            vh = (ViewHolder) convertView.getTag();                                         //krn udah ada sblmnya, isi viewholder dr info tag yg udah diset sblmnya
            Log.d("debug", "timpa view sekarang sama getRoot() dari vh.binding");
            convertView = vh.binding.getRoot();                                             //set view nya dgn bantuan vh
            //Log.d("debug", "isi bindingILFoods dgn getTag() dr view");
            //bindingILFoods = (ItemListFoodBinding) convertView.getTag();                    //kmdn isi binding dr view yg barusan diisi
        }

        //inflate the layout for each list row
        //convertView = LayoutInflater.from(this.activity).inflate(R.layout.item_list_food, parent, false); //nama layout id modul prak salah
        Log.d("debug", "buat currentFood tipe Food");
        Food currentFood = (Food) this.getItem(i); //ambil dari list<Food> ke i


        // Pasang-pasang ( pasang 1,2,3 udah cukup di updateView (dlm view holder))
        // 1. title
//        TextView tvTitle = convertView.findViewById(R.id.tvTitleFoods); //di modul prak idnya tv_food_title
//        tvTitle.setText(currentFood.getTitle());
        //with binding
//        this.bindingILFoods.tvTitleFoods.setText(currentFood.getTitle());

        // 2. detail
//        this.bindingILFoods.tvDetailFoods.setText(currentFood.getDetails());

        // 3. favourite if true star_on, false star off
        //ImageView ivStar = convertView.findViewById(R.id.ivStar);
        //set tag to it
//        if (currentFood.isFavorite()) {
////            ivStar.setImageResource(android.R.drawable.btn_star_big_on);
//            bindingILFoods.ivStar.setImageResource(android.R.drawable.btn_star_big_on);
//        }else {
//            bindingILFoods.ivStar.setImageResource(android.R.drawable.btn_star_big_off);
//        }

        Log.d("debug", "updateView.. ");
        //                                                                                          supaya ada food di vh, update view di viewHolder??
        vh.updateView(currentFood);

        // returns the view for the current row
        return convertView;
        //karna pake view holder, return layout root nya?
        //return bindingILFoods.getRoot();

    }



    //coba pake recycle view
    //dr slide
    /*
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        //kl blm ada
        if(view == null) {
            //kl gapake binding
            //view = LayoutInflater.from(this.activity).inflate(R.layout.item_list_string, viewGroup, false);

            //kl pake binding
            ItemListStringBinding binding = ItemListStringBinding.inflate(this.activity.getLayoutInflater(), viewGroup, false);
            view = binding.getRoot();
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }//kl sdh ada
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.updateView(this.getItem(i).toString());

        return view;
    }
     */

    //sesuain sama anak dari layout yg mau dipasang ke list view (item_list_food)
    private class ViewHolder implements View.OnClickListener{
//        protected TextView title;
//        protected TextView detail;
//        protected ImageView starStatus;
//        protected ImageButton garbageBin;

        ItemListFoodBinding binding;

        public Food food;

//        public ViewHolder(View view) {
//            this.tvForLv = view.findViewById(R.id.tvforListV);
//        }

        public ViewHolder(ItemListFoodBinding binding) {
            Log.d("debug", "masuk View Holder");
            this.binding = binding;

            this.binding.ivStar.setOnClickListener(this);
            this.binding.ibBin.setOnClickListener(this);
        }

        public void updateView (Food food) {
            Log.d("debug", "masuk updateView");
            this.food = food;

            //set Text untk tvs
            this.binding.tvTitleFoods.setText(food.getTitle());
            this.binding.tvDetailFoods.setText(food.getDetails());

            //setImageResource untk image view
            if(this.food.isFavorite()) {
                this.binding.ivStar.setImageResource(android.R.drawable.btn_star_big_on);
            }else { this.binding.ivStar.setImageResource(android.R.drawable.btn_star_big_off); }

            //untk image button

        }

        @Override
        public void onClick(View view) {
            Log.d("debug", "masuk onCLick vh");

            //cek isi food di view ini
            if(this.food == null) {
                Log.d("debug", "isi food kosong");
            }else {
                Log.d("debug", "isi food ada..");
                Log.d("debug", "isi title food di view skrng,,  " + this.food.getTitle());
            }


            if(view == this.binding.ivStar) {
                Log.d("debug", "view adlh binding.ivStar");
                if(this.food.isFavorite()) {
                    Log.d("debug", "favoritenya aktif");
                    this.binding.ivStar.setImageResource(android.R.drawable.btn_star_big_off);
                    this.food.setFavorite(false);
                }else {
                    Log.d("debug", "favoritenya gaaktif");
                    this.binding.ivStar.setImageResource(android.R.drawable.btn_star_big_on);
                    this.food.setFavorite(true);
                }
            }

            if(view == this.binding.ibBin) {
                Log.d("debug", "view adlh binding.ibBin");
                //delete view nya?
            }
        }
    }
}
