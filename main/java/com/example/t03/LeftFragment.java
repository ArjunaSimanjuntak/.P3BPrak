package com.example.t03;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.t03.databinding.FragmentLeftBinding;
import com.example.t03.databinding.FragmentRightBinding;

// left drawer
public class LeftFragment extends Fragment {
    String TAG = "debug LeftFrag";
    FragmentLeftBinding bindingFLeft;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.bindingFLeft = FragmentLeftBinding.inflate(inflater, container, false);  // disambungin ke layout pake binding
        View view = bindingFLeft.getRoot();

        Bundle args = this.getArguments();


        return  view;                                                                               //return super.onCreateView(inflater, container, savedInstanceState);
    }

    public static LeftFragment newInstance() {
                                                                                                    Log.d("debug LeftFragment", "newInstance: masuk");
        Bundle args = new Bundle();

        LeftFragment fragment = new LeftFragment();
        fragment.setArguments(args);
                                                                                                    Log.d("debug LeftFragment", "newInstance: end ");

        return fragment;
    }
}
