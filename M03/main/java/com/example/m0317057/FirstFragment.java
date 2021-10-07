package com.example.m0317057;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.m0317057.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment implements View.OnClickListener {
    FragmentFirstBinding binding;
    String getMessage;
    public static FirstFragment newInstance(String title) {
        Log.d("debug", "masuk konstruktor");
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
        //Button untuk pindah ke fragment/page 2
        binding.btnPage2.setOnClickListener(this);
        Log.d("debug", "setelah binding btn");
        //Komunikasi antar fragment 1 ke fragment 2
        getParentFragmentManager().setFragmentResultListener("setMessage", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundleMessage) {
                Log.d("debug", "masuk fragment listener FF");
                getMessage = bundleMessage.getString("message");
                setMessage();
            }
        });

        /*getParentFragmentManager().setFragmentResultListener("setMessage", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Log.d("debug", "masuk fragment listener FF");
                getMessage = result.getString("message");
                setMessage();
            }
        });*/
        return view;
    }

    @Override
    public void onClick(View view) {
        Bundle result = new Bundle();
        result.putInt("page", 2);
        this.getParentFragmentManager().setFragmentResult("changePage", result);

    }

    public void setMessage(){
        Log.d("debug", "masuk method setMessage");
        binding.tvMessage.setText(getMessage);
    }
}
