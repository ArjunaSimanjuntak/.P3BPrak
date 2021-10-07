package com.example.m0317057;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;


import com.example.m0317057.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment implements View.OnClickListener {
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
        binding.btnSubmit.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View view) {
        Bundle result = new Bundle();
        Bundle bundleMessage = new Bundle();
        result.putInt("page", 1);
        String isiMessage = binding.etMessage.getText().toString();
        bundleMessage.putString("message", isiMessage);
        binding.etMessage.getText().clear();
        binding.etMessage.onEditorAction(EditorInfo.IME_ACTION_DONE);
        this.getParentFragmentManager().setFragmentResult("setMessage", bundleMessage);
        this.getParentFragmentManager().setFragmentResult("changePage", result);
    }
}
