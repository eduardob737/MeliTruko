package com.example.melitruko.ui.view.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.melitruko.databinding.FragmentEndGameBinding;
import com.example.melitruko.ui.viewmodel.MatchViewModel;

public class EndGameFragment extends DialogFragment {

    private FragmentEndGameBinding binding;
    private MatchViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(MatchViewModel.class);
        binding = FragmentEndGameBinding.inflate(getLayoutInflater(), container, false);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        binding.btnToBack.setOnClickListener(view -> dismiss());

        binding.tvBlueTeamScore.setText(String.valueOf(viewModel.getWinnerTeamScore()));
        binding.tvWhiteTeamScore.setText(String.valueOf(viewModel.getLoserTeamScore()));

        return binding.getRoot();
    }
}
