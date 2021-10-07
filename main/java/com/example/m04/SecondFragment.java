package com.example.m04;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.m04.databinding.FragmentFirstBinding;
import com.example.m04.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment implements View.OnClickListener{
    FragmentSecondBinding bindingFSecond;

    // 'Factory' Design Pattern
    public static SecondFragment newInstance(String title) {
        Log.d("debug", "SecondFragment.newInstance().. ");

        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();                                                                 //bundle ini untk nyimpan kumpulan data

        Log.d("debug", "                            ..ngisi Bundlenya, args");
        args.putString("siKey_title", title);
        fragment.setArguments(args);

        Log.d("debug", "SecondFragment.newInstance() end; ");
        return fragment;
    }

    //    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("debug", "SecondFragment onCreateView.. ");

        Log.d("debug", "                onCreateView() inflating bindingFFirst");
        this.bindingFSecond = FragmentSecondBinding.inflate(inflater, container, false);

        // set onclick button DULU
        this.bindingFSecond.btnSubmit.setOnClickListener(this);


        Log.d("debug", "                onCreateView, inflate view ny dengan layout fragment ");

        // krn pake binding..
        Log.d("debug", "                onCreateView, or, isi view dengan getRoot() nya si binding td ");
        View view = bindingFSecond.getRoot();            // cukup ambil si layout yg disimpan td di binding sebelum


        Log.d("debug", "                onCreateView() masukin isi bundle(savedinstancestate?) ke variabel buatan args");
        Bundle args = this.getArguments();

        Log.d("debug", "                onCreateView, mau changeText ");
        Log.d("debug", "                onCreateView, isi args.getString dgn key title: "+ this.getArguments().getString("title", ""));
        this.bindingFSecond.tvTitle2.setText(args.getString("siKey_title", ""));

        Log.d("debug", "SecondFragment   onCreateView.. end of oncreateview");
        return view;
    }

    @Override
    public void onClick(View view) {
        Log.d("debug", "SecondFragment   onClick");

        //                                                                          Setelah tombol “Submit” ditekan maka, EditText dijadikan kosong kembali dan menyembunyikan
        //                                                                          soft keyboard.

        FragmentManager fm = this.getParentFragmentManager();
        Bundle bundle = new Bundle();

        // untuk kirim info ke fragment 1, supaya isi 'input message' disimpan ke tv message fragmen 1
        String dlmMessage = this.bindingFSecond.etMessage.getText().toString();
        Log.d("debug", "                onClick, isinya isiMessage: ");
        bundle.putString("message", dlmMessage);                                                    //simpan isi edit Text dr layout fragmen, ke bundle string
        this.bindingFSecond.etMessage.getText().clear();                                            //dikosongin
        //                                                                                          otomatis nutup soft keyboard?


        // untuk kirim info ke main act or fragment1?, supaya submit/pindah halaman
        bundle.putInt("page", 1);
        fm.setFragmentResult("changePage", bundle);
        fm.setFragmentResult("mauSubmit", bundle);


        Log.d("debug", "SecondFragment   onClick.. end of onClick or submit");
    }
}