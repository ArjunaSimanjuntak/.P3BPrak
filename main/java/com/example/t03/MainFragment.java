package com.example.t03;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.t03.databinding.ActivityMainBinding;
import com.example.t03.databinding.FragmentMainBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements View.OnClickListener{
    FragmentMainBinding bindingFMain;
    String TAG = "debug";

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.bindingFMain = FragmentMainBinding.inflate(inflater, container, false);
        View view = this.bindingFMain.getRoot();

        //FragmentManager fm = this.getSupportFragmentManager();  //why cant resolve, but getParentFragment can ??

        this.bindingFMain.buttMainfrag.setOnClickListener(this);                                    // set buat pas ditekan ke onclick

        return view;
    }

    @Override
    public void onClick(View view) {
                                                                                                    Log.d(TAG, "MainFrag    onClick: ");

        if (view == this.bindingFMain.buttMainfrag) {                                               Log.d(TAG, "onClick:    button 'Click Me' ditekan");
            String teks = this.bindingFMain.etMainfrag.getText().toString();


                                                                                                    Log.d(TAG, "onClick: buat objek Res Dialg Frag, rdf");
                                                                                                    // buat objek dialog n pake newinstance/factory method supaya ngasih string dr edit text yg baru dapat
            ResultDialogFragment rdf = ResultDialogFragment.newInstance(teks);                      Log.d(TAG, "onClick: buat frag manager");
            FragmentManager fm = this.getParentFragmentManager();                                   Log.d(TAG, "onClick: buat frag transaction");
            FragmentTransaction ft = fm.beginTransaction();                                         // hrs gini dulu?


            // pastikan binding di kelas result dialog fragmentnya udah diinflate supaya bisa set                                                                                                    Log.d(TAG, "onClick: rdf.show() ....");
            rdf.show(ft, "dialog");

        }

                                                                                                    Log.d(TAG, "MainFrag    onClick: end of this method");

    }
}