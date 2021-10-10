package com.example.t0317057;


import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

public class ResultDialogFragment extends DialogFragment {
    /*public static ResultDialogFragment newInstance(String text) {
        ResultDialogFragment fragment = new ResultDialogFragment();
        Bundle args = new Bundle();
        args.putString("text", text);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public int show( FragmentTransaction transaction, String tag) {
        return super.show(transaction, tag);
    }
}
