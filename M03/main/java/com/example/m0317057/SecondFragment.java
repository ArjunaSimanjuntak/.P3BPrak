package com.example.m0317057;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;


import com.example.m0317057.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {
    FragmentSecondBinding binding;
    public static SecondFragment newInstance(String title) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return  fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        binding.tvTitle2.setText(this.getArguments().getString("title", ""));
        return view;
    }
}
