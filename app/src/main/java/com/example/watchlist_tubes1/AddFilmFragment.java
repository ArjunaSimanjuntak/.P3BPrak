package com.example.watchlist_tubes1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.watchlist_tubes1.databinding.FragmentAddFilmBinding;
import com.example.watchlist_tubes1.databinding.FragmentDaftarWatchlistBinding;

import java.util.ArrayList;
import java.util.List;

public class AddFilmFragment extends Fragment implements View.OnClickListener, MoviePresenter.IMoviePresenter {
    private static final String TAG = "debug fragment addfilm";
    private FragmentAddFilmBinding binding;
    private MoviePresenter presenter;
//    private WishlistFragment fragmentWishlist; // gaperlu, cb komunikasi aja pake fragment api

    public static AddFilmFragment newInstance() {
        AddFilmFragment addFilmFragment = new AddFilmFragment();
        Bundle args = new Bundle();
        addFilmFragment.setArguments(args);
        return addFilmFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddFilmBinding.inflate(inflater, container, false);
        View view =binding.getRoot();


//        // fragment wish list
//        this.fragmentWishlist = new WishlistFragment();

        //MoviePresenter
        this.presenter = new MoviePresenter(this);


        /*//Implement list adapter
        this.listView = binding.listMovie;
        this.adapter = new WishlistAdapter(this);
        this.listView.setAdapter(this.adapter);*/

        binding.btnAdd.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

        ///////tes nambah ke database
        // udah pasti add ditekan karna cuman satu button yg listen
        Movie newMovie = null;

        try {                                                                                       // manatau error (?)

            String newTitle = binding.etTitle.getText().toString();
            String newSynopsis = binding.etSynopsis.getText().toString();

            if (!newTitle.equals("") || !newSynopsis.equals("")) {
                newMovie = new Movie(newTitle, newSynopsis);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        DatabaseHelper dbHelper = new DatabaseHelper(this.getActivity());                           // gabisa pake this, butuh context. jd bisa FragmentActivity (?)

        boolean success = dbHelper.addMovie(newMovie);

        if (success) {
            Toast.makeText(this.getActivity(), "Movie Added!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this.getActivity(), "something went wrong", Toast.LENGTH_SHORT).show();
        }

        //////
        binding.etTitle.getText().clear();
        binding.etSynopsis.getText().clear();
        binding.etTitle.onEditorAction(EditorInfo.IME_ACTION_DONE);


        // kasi tau mau update ke presenter
        List<Movie> moviesFromDB = new ArrayList<>();

        if (dbHelper == null) {
            dbHelper = new DatabaseHelper(this.getContext());
        }
        moviesFromDB = dbHelper.getAllMovie();

        presenter.addMovies(moviesFromDB);                                                              // updateListMovie(moviesFromDB);


        Bundle result = new Bundle();
        result.putInt("page", 2);
        this.getParentFragmentManager()
                .setFragmentResult("changePage", result);
    }

    @Override
    public void updateListMovie(List<Movie> movieLists) {
                                                                                                    Log.d(TAG, "updateListMovie: masuk updateList si fragaddfilm");
//        this.fragmentWishlist.updateListMovie(movieLists);                                          // lempar movies yg dr DB, pas onclick td, terus addmovies di presenter, terus dr presenter sana manggil this.ui.update , nyampe sini... terus lempar movies nya biar diupdate di wishlist fragment sana.

        // todo:
        // kasi tau biar di update (ke method updateList nya dia, sambil bawa movie list dari sini
        // param movieLists ny jd gadipake, krn gabisa dikirim lewat komunikasi wishlist
        Bundle movieBundle = new Bundle();
        movieBundle.putBoolean("perluUpdate", true);

        this.getParentFragmentManager()
                .setFragmentResult("apaPerluDiUpdateListnya", movieBundle);
    }
}
