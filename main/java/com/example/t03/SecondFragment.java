package com.example.t03;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.t03.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {
    FragmentSecondBinding bindingFSecond;

    public static SecondFragment newInstance() {

        Bundle args = new Bundle();

        SecondFragment fragment = new SecondFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.bindingFSecond = FragmentSecondBinding.inflate(inflater, container, false);
        View view = this.bindingFSecond.getRoot();

        return view;
    }
}
