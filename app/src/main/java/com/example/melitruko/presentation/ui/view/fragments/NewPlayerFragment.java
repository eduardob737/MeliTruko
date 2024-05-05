package com.example.melitruko.presentation.ui.view.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;

import com.example.melitruko.R;
import com.example.melitruko.databinding.FragmentNewPlayerBinding;

public class NewPlayerFragment extends DialogFragment {

    private FragmentNewPlayerBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNewPlayerBinding.inflate(inflater, container, false);

        this.getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        binding.btnCancel.setOnClickListener(view -> dismiss());

        return inflater.inflate(R.layout.fragment_new_player, container, false);
    }
}