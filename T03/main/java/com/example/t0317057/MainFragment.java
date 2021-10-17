package com.example.t0317057;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import com.example.t0317057.databinding.MainFragmentBinding;
import com.example.t0317057.databinding.SecondFragmentBinding;

public class MainFragment extends Fragment implements View.OnClickListener {
    MainFragmentBinding binding;
    ResultDialogFragment dialogFragment;


    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("debug", "MF : oncreateView");
        binding = MainFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.btnClick.setOnClickListener(this);
//        text = binding.etText.getText().toString();

        return view;
    }


    @Override
    public void onClick(View view) {
        if(view == this.binding.btnClick){
            String text = this.binding.etText.getText().toString();
            this.dialogFragment = ResultDialogFragment.newInstance("");

            FragmentManager fm = this.getParentFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            getText();
            this.dialogFragment.show(ft, "dialog");
            Log.d("debug", "muncul dialog");
        }
    }

    private void getText() {
        Bundle bundleText = new Bundle();
        String isiText = binding.etText.getText().toString();
        bundleText.putString("text", isiText);
        this.getParentFragmentManager().setFragmentResult("passingText", bundleText);
        Log.d("debug", "sudah sampai method getText");
    }
}
