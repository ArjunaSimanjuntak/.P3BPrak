package com.example.m0317057;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;

import com.example.m0317057.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {
    FragmentFirstBinding binding;
    public static FirstFragment newInstance(String title) {
        //Factory method menggunakan newInstance method
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        binding.tvTitle.setText(this.getArguments().getString("title",""));
        return view;
    }
}
