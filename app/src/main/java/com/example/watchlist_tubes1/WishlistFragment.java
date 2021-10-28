package com.example.watchlist_tubes1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.watchlist_tubes1.databinding.FragmentDaftarWatchlistBinding;

import java.util.List;

public class WishlistFragment extends Fragment implements View.OnClickListener, MoviePresenter.IMoviePresenter {
    FragmentDaftarWatchlistBinding binding;
    private ListView listView;
    private WishlistAdapter adapter;
    private MoviePresenter moviePresenter;

    public static Movie[] movieObjectArr = {
            new Movie("WatchMen", "synopsis"),                                          //"status TRUE", "ini review watchmen", 5),
            new Movie("Fargo", "synopsis something something"),
            new Movie("Venom", "synopsis tentang venom "),
            new Movie("Game Of Thrones", "Sinopsis tentang GOT"),                       //19
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

        binding.btnAddFab.setOnClickListener(this);

        return view;
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
