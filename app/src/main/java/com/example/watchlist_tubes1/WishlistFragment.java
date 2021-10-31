package com.example.watchlist_tubes1;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.watchlist_tubes1.databinding.FragmentDaftarWatchlistBinding;
import com.example.watchlist_tubes1.databinding.ItemListWatchlistBinding;

import java.util.ArrayList;
import java.util.List;

public class WishlistFragment extends Fragment implements View.OnClickListener, MoviePresenter.IMoviePresenter, WishlistAdapter.IKeDetail {
    private static final String TAG = "debug WishlistFrag";
    private FragmentDaftarWatchlistBinding binding;
    private ListView listView;
    private WishlistAdapter adapter;
    private ItemListWatchlistBinding watchlistBinding;
    private MoviePresenter moviePresenter;
    private DatabaseHelper dbHelper;


    // dummy data (pas loaddata(), gadipake lg)
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
        this.adapter = new WishlistAdapter(this, this );
        this.listView.setAdapter(this.adapter);
        this.watchlistBinding = ItemListWatchlistBinding.inflate(getLayoutInflater());


                                                                                                    Log.d(TAG, "onCreateView: coba ambil data dr sqlite");
        // coba sqlite
        if (dbHelper == null) {
            dbHelper = new DatabaseHelper(this.getContext());
        }

//        dbHelper.deleteAllData();                                                                 // kl mau bersihin sqlite

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
        else if(view==watchlistBinding.btnCheckbox){
            Log.d("debug", "masuk btn_checklist link fragment detail");

        }
    }

    @Override
    public void updateListMovie(List<Movie> movieLists) {                                           Log.d(TAG, "updateListMovie: masuk updateList si fragwishlist");
        Toast.makeText(this.getContext(), "recreate this fragment to see the Updated List!", Toast.LENGTH_SHORT).show();
        this.adapter.updateListMovie(movieLists);
    }

    @Override
    public void changePageDetail(String page, int detailPage) {
        Bundle result = new Bundle();
        result.putInt(page, detailPage);
        this.getParentFragmentManager()
                .setFragmentResult("changePage", result);
    }
}
