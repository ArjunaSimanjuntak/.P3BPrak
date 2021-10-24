package com.example.watchlist_tubes1;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MoviePresenter {
    private static final String TAG = "debug MoviePresenter";                                       // debug
    protected List<Movie> movies;
    protected IMoviePresenter ui;

    public MoviePresenter (IMoviePresenter ui) {
        this.ui = ui;
        this.movies = new ArrayList<Movie>();
    }

    public void loadData (Movie[] arrMovie) {
        //
        if (this.movies.isEmpty()) {
            for (Movie movie : arrMovie) {
                                                                                                    Log.d(TAG, "loadData: , movie.gettitle(): "+ movie.getTitle() + ", .getSynopsis(): " + movie.getSynopsis());
                this.movies.add(movie);
            }
        }

        this.ui.updateListMovie(movies);
    }


    public interface IMoviePresenter {
        void updateListMovie (List<Movie> movieLists);

//        void resetAddForm();
    }
}
