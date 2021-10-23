package com.example.watchlist_tubes1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        Bundle result = new Bundle();
        result.putInt("page", 2);
        this.getParentFragmentManager()
                .setFragmentResult("changePage", result);
    }
}
