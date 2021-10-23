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

public class WishlistFragment extends Fragment implements View.OnClickListener {
    FragmentDaftarWatchlistBinding binding;
    private ListView listView;
    private WishlistAdapter adapter;


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

        //Implement list adapter
        this.listView = binding.listMovie;
        this.adapter = new WishlistAdapter(this);
        this.listView.setAdapter(this.adapter);

        binding.btnAddFab.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        Bundle result = new Bundle();
        result.putInt("page", 3);
        this.getParentFragmentManager()
                .setFragmentResult("changePage", result);
    }
}
