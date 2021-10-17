package com.example.t0317057;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;

import com.example.t0317057.databinding.FragmentLeftBinding;

public class LeftFragment extends Fragment implements View.OnClickListener {
    FragmentLeftBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("debug", "LF : oncreateView");
        binding = FragmentLeftBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        binding.btnHome.setOnClickListener(this);
        binding.btnPage2.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
       Bundle result = new Bundle();
        if(view == binding.btnHome){
           result.putInt("page", 1);
           this.getParentFragmentManager().setFragmentResult("changePage", result);
       }
        else if(view == binding.btnPage2){
            result.putInt("page", 2);
            this.getParentFragmentManager().setFragmentResult("changePage", result);
        }
        else if(view == binding.btnExit){

        }
    }
}
