//package "___________________";
package com.example.t03;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.t03.databinding.FragmentRightBinding;

public class RightFragment extends Fragment {
    FragmentRightBinding bindingFRight;
    String TAG = "debug RightFragment";

    public static RightFragment newInstance() {
                                                                                                    Log.d("debug RightFragment", "newInstance: masuk");
        Bundle args = new Bundle();

        RightFragment fragment = new RightFragment();
        fragment.setArguments(args);
                                                                                                    Log.d("debug RightFragment", "newInstance: end ");
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.bindingFRight = FragmentRightBinding.inflate(inflater, container, false);  // disambungin ke layout pake binding
        View view = bindingFRight.getRoot();

        Bundle args = this.getArguments();

        return  view;                                                                               //return FragmentRightBinding.inflate(inflater,container,false).getRoot();
    }
}