package com.example.watchlist_tubes1;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.watchlist_tubes1.databinding.FragmentDaftarWatchlistBinding;

import java.util.ArrayList;
import java.util.List;

public class WishlistFragment extends Fragment implements View.OnClickListener, MoviePresenter.IMoviePresenter {
    private static final String TAG = "debug WishlistFrag";
    FragmentDaftarWatchlistBinding binding;
    private ListView listView;
    private WishlistAdapter adapter;
    private MoviePresenter moviePresenter;
    private DatabaseHelper dbHelper;

    // dummy data
    public static Movie[] movieObjectArr = {
            new Movie("WatchMen", "synopsis"),                                          //"status TRUE", "ini review watchmen", 5),
            new Movie("Fargo", "synopsis something something"),
            new Movie("Venom", "synopsis tentang venom "),
            new Movie("Game Of Thrones", "Sinopsis tentang GOT"),                       //16 got dummies
            new Movie("Game Of Thrones", "Sinopsis tentang GOT"),
            new Movie("Game Of Thrones", "Sinopsis tentang GOT"),
            new Movie("Game Of Thrones", "Sinopsis tentang GOT"),
            new Movie("Game Of Thrones", "Sinopsis tentang GOT"),
            new Movie("Game Of Thrones", "Sinopsis tentang GOT"),
            new Movie("Game Of Thrones", "Sinopsis tentang GOT"),
            new Movie("Game Of Thrones", "Sinopsis tentang GOT"),
            new Movie("Game Of Thrones", "Sinopsis tentang GOT"),
            new Movie("Game Of Thrones", "Sinopsis tentang GOT"),
            new Movie("Game Of Thrones", "Sinopsis tentang GOT"),
            new Movie("Game Of Thrones", "Sinopsis tentang GOT"),
            new Movie("Game Of Thrones", "Sinopsis tentang GOT"),
            new Movie("Game Of Thrones", "Sinopsis tentang GOT"),
            new Movie("Game Of Thrones", "Sinopsis tentang GOT"),
            new Movie("Game Of Thrones", "Sinopsis tentang GOT"),
            new Movie("Taxi Driver", "taxitaxitaxi")
    };



    public static WishlistFragment newInstance() {
        WishlistFragment wishlistFragmentAdapter = new WishlistFragment();
        Bundle args = new Bundle();
        wishlistFragmentAdapter.setArguments(args);
        return wishlistFragmentAdapter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDaftarWatchlistBinding.inflate(inflater, container, false);
        View view =binding.getRoot();

        //MoviePresenter
        this.moviePresenter = new MoviePresenter(this);

        //Implement list adapter
        this.listView = binding.listMovie;
        this.adapter = new WishlistAdapter(this);
        this.listView.setAdapter(this.adapter);

        this.moviePresenter.loadData(movieObjectArr);

                                                                                                    Log.d(TAG, "onCreateView: coba ambil data dr sqlite");
        // coba load dr sqlite
        if (dbHelper == null) {
            dbHelper = new DatabaseHelper(this.getContext());
        }

//        dbHelper.deleteAllData();

        Cursor cursor = dbHelper.getAllData();                                                         // datanya yg diambil

        List itemIds = new ArrayList<>();
        long itemId = 1;

        ArrayList<String> listMovieTitlesdrDB = new ArrayList<>();
        String curMovieTitle = "something!";

        while(cursor.moveToNext()) {
            itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(dbHelper.getColumnId()));                          //
            itemIds.add(itemId);
//            listMoviedrDB.add(cursor.get)



            // tes liat isinya
            curMovieTitle = cursor.getString(1);                                                // ambil string di kolom ke2 (COLUMN_TITLE) krn dari 0, 1, 2...
                                                                                                    Log.d(TAG, "onCreateView: isi titleMovie : " + curMovieTitle);
            listMovieTitlesdrDB.add(curMovieTitle);
        }




        cursor.close();



        binding.btnAddFab.setOnClickListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        List<Movie> moviesFromDB = new ArrayList<>();

        if (dbHelper == null) {
            dbHelper = new DatabaseHelper(this.getContext());
        }
        moviesFromDB = dbHelper.getAllMovie();

        updateListMovie(moviesFromDB);
    }

    @Override
    public void onClick(View view) {
        Bundle result = new Bundle();
        if(view==binding.btnAddFab){
            result.putInt("page", 3);
            this.getParentFragmentManager()
                    .setFragmentResult("changePage", result);
        }
    }

    @Override
    public void updateListMovie(List<Movie> movieLists) {
        this.adapter.updateListMovie(movieLists);
    }
}
