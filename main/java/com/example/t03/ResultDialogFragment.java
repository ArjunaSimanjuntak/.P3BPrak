package com.example.t03;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.t03.databinding.FragmentMainBinding;
import com.example.t03.databinding.FragmentResultDialogBinding;

public class ResultDialogFragment extends DialogFragment {
    FragmentResultDialogBinding bindingFResultDialog;
    String TAG = "debug RDFrag";


    public static ResultDialogFragment newInstance(String teksnya) {
                                                                                                    Log.d("debug", "ResDialgFrag newInstance: ");
        Bundle args = new Bundle();

        args.putString("teks", teksnya);                                                            // masukin masukan teksnya

        ResultDialogFragment fragment = new ResultDialogFragment();
        fragment.setArguments(args);

                                                                                                    Log.d("debug", "ResDialgFrag newInstance: end of newinstance");
        return fragment;
    }


    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
                                                                                                    Log.d(TAG, "resultdialogfragment    onCreateView: ");

        this.bindingFResultDialog = FragmentResultDialogBinding.inflate(inflater, container, false);
        View view = this.bindingFResultDialog.getRoot();                                            // pake layout yg uda disediakan


                                                                                                    Log.d(TAG, "resultdialogfragment    onCreateView: end of resultdfragment");
        return view;
    }

    // GAPERLU DI
    // method dr dialog fragment
//    @Override
//    public int show(@NonNull FragmentTransaction transaction, @Nullable String tag) {
//                                                                                                    Log.d(TAG, "ResultDialogFragment    show: ");
////        Bundle args = this.getArguments();
////        this.bindingFResultDialog.tvResult.setText(args.getString("teks", ""));
//        // error krn belum dibuat dulu binding nya
//
//                                                                                                    Log.d(TAG, "show: setText()!!");
//        this.bindingFResultDialog.tvResult.setText(tag);                                            // java.lang.NullPointerException: Attempt to read from field 'android.widget.TextView com.example.t03.databinding.FragmentResultDialogBinding.tvResult' on a null object reference
//                                                                                                    //at com.example.t03.ResultDialogFragment.show(ResultDialogFragment.java:59)
//
//        // pasang tv cara lama, gabisa
//        //this.tv = findViewById
//                                                                                   Log.d(TAG, "ResultDialogFragment    show: end of show");
//        return super.show(transaction, tag);
//    }

    @Override
    public void onResume() {        // knp tetap
        super.onResume();
    }
}
