package com.example.watchlist_tubes1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.watchlist_tubes1.databinding.FragmentAddFilmBinding;
import com.example.watchlist_tubes1.databinding.FragmentDaftarWatchlistBinding;

public class AddFilmFragment extends Fragment implements View.OnClickListener {
    FragmentAddFilmBinding binding;

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
            newMovie = new Movie(newTitle, newSynopsis);
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

        Bundle result = new Bundle();
        result.putInt("page", 2);
        this.getParentFragmentManager()
                .setFragmentResult("changePage", result);
    }
}
