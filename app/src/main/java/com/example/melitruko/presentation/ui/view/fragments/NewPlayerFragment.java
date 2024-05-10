package com.example.melitruko.presentation.ui.view.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.melitruko.R;
import com.example.melitruko.databinding.FragmentNewPlayerBinding;

public class NewPlayerFragment extends DialogFragment {

    private FragmentNewPlayerBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNewPlayerBinding.inflate(inflater, container, false);

        this.getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        binding.btnDefaultImage.setOnClickListener(view -> {

        });

        binding.btnCamera.setOnClickListener(view -> {

        });

        binding.btnGallery.setOnClickListener(view -> {

        });

        binding.btnCancel.setOnClickListener(view -> dismiss());

        return binding.getRoot();
    }
}