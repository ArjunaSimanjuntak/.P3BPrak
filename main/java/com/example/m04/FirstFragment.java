package com.example.m04;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import com.example.m04.databinding.ActivityMainBinding;
import com.example.m04.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment implements View.OnClickListener{
    // pake binding
    FragmentFirstBinding bindingFFirst;

    // diharuskan ada constructor kosong
//    public FirstFragment() {
//    }

    // pake 'Factory' Design Pattern.                                                               why does it look like that?? why static? whats newIntance meant?
    public static FirstFragment newInstance(String title) {
        Log.d("debug", "FirstFragment.newInstance().. ");

        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();                                                                 //bundle ini untk nyimpan kumpulan data

        Log.d("debug", "                            ..ngisi Bundlenya, args");
        args.putString("title", title);
        fragment.setArguments(args);

        Log.d("debug", "FirstFragment.newInstance() end; ");
        return fragment;
    }

//    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("debug", "FirstFragment onCreateView.. ");
//        return super.onCreateView(inflater, container, savedInstanceState);

        Log.d("debug", "                onCreateView() inflating bindingFFirst");
        //  krn pake binding..
        //  biasanya gini di Main Act
        //                  = FragmentFirstBinding.inflate(this.getLayoutInflater());
        //  krn ini di onCreate view kelas lain, gunain paramterenya
        this.bindingFFirst = FragmentFirstBinding.inflate(inflater, container, false);

        // set onclick button.
        this.bindingFFirst.btnIdPage1.setOnClickListener(this);


        Log.d("debug", "                onCreateView, inflate view ny dengan layout fragment ");
        // Inflate the layout for this fragment         (dr modul)
//        View view = inflater.inflate(R.layout.fragment_first, container, false);

        // krn pake binding..
        Log.d("debug", "                onCreateView, or, isi view dengan getRoot() nya si binding td ");
        View view = bindingFFirst.getRoot();            // cukup ambil si layout yg disimpan td di binding sebelum



        Log.d("debug", "                onCreateView() masukin isi bundle(savedinstancestate?) ke variabel buatan args");
        // terus masukin bundle ke variabel
        Bundle args = this.getArguments();

        Log.d("debug", "                onCreateView, mau changeText ");
        // changeText (with binding) and with argument/bundle (?)
//        bindingFFirst.tvTitle.setText(this.getArguments().getString("title", ""));
        Log.d("debug", "                onCreateView, isi args.getString dgn key title: "+ this.getArguments().getString("title", ""));
        this.bindingFFirst.tvTitle.setText(this.getArguments().getString("title", ""));


        // untuk dengar kabar dari fragment 2
//        Log.d("debug", "                    fragment 1,, siap-siap ngelisten dari fragment 2 ");
        FragmentManager fm = this.getParentFragmentManager();                                       // tapi pas diganti jadi getChild, si fragmen2 resultnya bisa dibaca dari main activity
//        fm.setFragmentResultListener("mauSubmit", this, new FragmentResultListener() {
//            @Override
//            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
//                Log.d("debug", "                                 masuk listener, requestKey kedengaran: mauSubmit, listen dr fragmen1");
//
//                String inputMessage = result.getString("bundleKeyString");
//                if (inputMessage.equals("")) {
//                    bindingFFirst.tvMessage.setText(inputMessage);
//                }
//            }
//        });

        fm.setFragmentResultListener("mauSubmit", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Log.d("debug", "onFragmentResult: 'mauSubmit'");
                String messagenya = result.getString("message");
                setMessage(messagenya);
            }
        });


        Log.d("debug", "FirstFragment   onCreateView.. end of oncreateview");
        return view;
    }

    // dr onclick ini, biar komunikasi sama (main) activity
    @Override
    public void onClick(View view) {
        Log.d("debug", "FirstFragment   onClick");

        FragmentManager fm = this.getParentFragmentManager();                                       // biar bisa komunikasi td sama activity
        Bundle bundle = new Bundle();
        //bundle.putString("newTextklPerlu", "dari activity berubah karena dikirim dari fragment"); //conto ko keenan

        // untuk kirim info ke main act, supaya pindah halaman
        bundle.putInt("page", 2);
        fm.setFragmentResult("changePage", bundle);


        Log.d("debug", "FirstFragment   onClick.. end of onClick");
    }

    public void setMessage(String messagenya) {
        Log.d("debug", "setMessage: masuk..");
        this.bindingFFirst.tvMessage.setText(messagenya);
    }
}
