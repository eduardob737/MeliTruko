package com.example.melitruko.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.melitruko.R;
import com.example.melitruko.Utils;
import com.example.melitruko.databinding.FragmentSixPlayersBinding;

public class SixPlayersFragment extends Fragment {

    private FragmentSixPlayersBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSixPlayersBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Utils.setupActionButtonPlayer(requireContext(), binding.ivPlayer1);
        Utils.setupActionButtonPlayer(requireContext(), binding.ivPlayer2);
        Utils.setupActionButtonPlayer(requireContext(), binding.ivPlayer3);
        Utils.setupActionButtonPlayer(requireContext(), binding.ivPlayer4);
        Utils.setupActionButtonPlayer(requireContext(), binding.ivPlayer5);
        Utils.setupActionButtonPlayer(requireContext(), binding.ivPlayer6);

    }
}