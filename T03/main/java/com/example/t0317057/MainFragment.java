package com.example.t0317057;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.t0317057.databinding.MainFragmentBinding;
import com.example.t0317057.databinding.SecondFragmentBinding;

public class MainFragment extends Fragment implements View.OnClickListener {
    MainFragmentBinding binding;
    public static MainFragment newInstance(String text) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString("text", text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("debug", "MF : oncreateView");
        binding = MainFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.btnClick.setOnClickListener(this);

        //Komunikasi antara Main Fragment dengan Rsult Dialog Fragment
        //menggunakan Fragment Result API
        getParentFragmentManager().setFragmentResultListener("getText", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundleText) {
                String getText = bundleText.getString("text");
                getText();
            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {
        Log.d("debug","onClick berjalan");
        binding.etText.getText();
        DialogFragment dialogFragment = new DialogFragment();
        dialogFragment.show(this.getParentFragmentManager(), "dialog");
    }

    private void getText() {
        binding.etText.getText();
    }
}
