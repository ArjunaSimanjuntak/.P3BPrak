package com.example.watchlist_tubes1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.watchlist_tubes1.databinding.FragmentSomepageBinding;

// kelas asal, untk coba arahin dari menu drawer ke layout page ini
public class SomePage extends Fragment {
    FragmentSomepageBinding binding;

    
    public static SomePage newInstance() {

        Bundle args = new Bundle();

        SomePage fragment = new SomePage();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSomepageBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();
        return view;
    }
}
