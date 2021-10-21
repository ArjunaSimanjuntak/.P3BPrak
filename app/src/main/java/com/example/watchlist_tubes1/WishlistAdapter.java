package com.example.watchlist_tubes1;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.fragment.app.Fragment;

import com.example.watchlist_tubes1.databinding.ItemListWatchlistBinding;

import java.util.ArrayList;
import java.util.List;

public class WishlistAdapter extends BaseAdapter {
    private List<Movie> listMovie;
    private Fragment fragment;

    public WishlistAdapter(Fragment fragment){
        this.fragment = fragment;
        this.listMovie = new ArrayList<Movie>();
    }

    public void addLine(Movie newItem){
        Log.d("debug", "masuk add line");
        this.listMovie.add(newItem);
        this.notifyDataSetChanged();
    }

    public void removeList(Movie item){
        this.listMovie.remove(item);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listMovie.size();
    }

    @Override
    public Object getItem(int i) {
        return listMovie.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //Menggunakan Recycle View dan membutuhkan ViewHolder

        ViewHolder viewHolder;
        ItemListWatchlistBinding itemListWatchlistBinding;
        Movie currentMovie = (Movie)this.getItem(i);

        if(view==null){
            itemListWatchlistBinding = ItemListWatchlistBinding.inflate(this.fragment.getLayoutInflater(), viewGroup, false);
            view = itemListWatchlistBinding.getRoot();
            viewHolder = new ViewHolder(itemListWatchlistBinding);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.updateView(currentMovie);

        return view;
    }

    private class ViewHolder implements View.OnClickListener {
        ItemListWatchlistBinding itemListWatchlistBinding;
        Movie currentMovie;

        public ViewHolder(ItemListWatchlistBinding itemListWatchlistBinding){
            Log.d("debug", "masuk ViewHolder");
            this.itemListWatchlistBinding = itemListWatchlistBinding;

            itemListWatchlistBinding.btnCheckbox.setOnClickListener(this);
            itemListWatchlistBinding.tvListJudul.setOnClickListener(this);
        }

        public void updateView(Movie currentMovie){
            Log.d("debug", "masuk updateView");
            itemListWatchlistBinding.tvListJudul.setText(currentMovie.getTitle());
            this.currentMovie = currentMovie;
        }

        @Override
        public void onClick(View view) {
            //checkbox dan tv_list_judul jika di click
        }
    }
}
