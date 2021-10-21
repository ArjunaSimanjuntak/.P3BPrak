package com.example.watchlist_tubes1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.watchlist_tubes1.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements View.OnClickListener {
    FragmentHomeBinding binding;

    public static HomeFragment newInstance(){
        HomeFragment homeFragment = new HomeFragment();
        Bundle args = new Bundle();
        homeFragment.setArguments(args);
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        binding.btnStart.setOnClickListener(this);

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
