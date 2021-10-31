package com.example.watchlist_tubes1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.watchlist_tubes1.databinding.FragmentDetailBinding;

public class DetailFragment extends Fragment implements View.OnClickListener {
    FragmentDetailBinding binding;
    public static DetailFragment newInstance() {
        DetailFragment detailFragment = new DetailFragment();
        Bundle args = new Bundle();
        detailFragment.setArguments(args);
        return detailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        View view =binding.getRoot();
        binding.btnSave.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        Bundle result = new Bundle();
        if(view==binding.btnSave){
            result.putInt("page", 2);
            this.getParentFragmentManager()
                    .setFragmentResult("changePage", result);
        }
    }
}
