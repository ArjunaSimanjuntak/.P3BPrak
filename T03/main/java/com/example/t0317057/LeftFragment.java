package com.example.t0317057;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;

import com.example.t0317057.databinding.FragmentLeftBinding;

public class LeftFragment extends Fragment {
    FragmentLeftBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("debug", "LF : oncreateView");
        binding = FragmentLeftBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }
}
