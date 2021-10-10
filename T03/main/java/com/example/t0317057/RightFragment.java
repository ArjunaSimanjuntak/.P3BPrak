package com.example.t0317057;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.t0317057.databinding.FragmentRightBinding;

public class RightFragment extends Fragment {
    FragmentRightBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("debug", "RF : oncreateView");
        binding = FragmentRightBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }
}
