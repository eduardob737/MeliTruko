package com.example.melitruko.ui.view.fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.melitruko.R;
import com.example.melitruko.Utils;
import com.example.melitruko.databinding.FragmentFourPlayersBinding;

import java.util.Objects;

public class FourPlayersFragment extends Fragment {

    private FragmentFourPlayersBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFourPlayersBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Utils.setupActionButtonPlayer(requireContext(),binding.ivPlayer1);
        Utils.setupActionButtonPlayer(requireContext(),binding.ivPlayer2);
        Utils.setupActionButtonPlayer(requireContext(),binding.ivPlayer3);
        Utils.setupActionButtonPlayer(requireContext(),binding.ivPlayer4);
    }
}