package com.example.t0317057;



import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import com.example.t0317057.databinding.FragmentResultDialogBinding;

public class ResultDialogFragment extends DialogFragment {
    private FragmentResultDialogBinding binding;
    String result;

    public static ResultDialogFragment newInstance(String text) {
        Bundle args = new Bundle();
        args.putString("dialog", text);
        ResultDialogFragment fragment = new ResultDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentResultDialogBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        getParentFragmentManager().setFragmentResultListener("passingText", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle passGetText) {
                result = passGetText.getString("text");
                setTextView();
            }
        });
        return view;
    }

    public void setTextView(){
        binding.tvResult.setText("Your input : " + result);
    }
}
