package com.example.melitruko.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.melitruko.databinding.FragmentMatchBinding;
import com.example.melitruko.viewmodel.MatchViewModel;

public class MatchFragment extends Fragment {

    private FragmentMatchBinding binding;
    private MatchViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(MatchViewModel.class);
        binding = FragmentMatchBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupTextViewTeamsScore();
        setupTextViewToAddPoints();
        setupButtonsActions();
    }

    private void setupTextViewTeamsScore() {
        binding.tvBlueTeamScore.setText(String.valueOf(getBlueTeamScore()));
        binding.tvWhiteTeamScore.setText(String.valueOf(getWhiteTeamScore()));
    }

    private void setupTextViewToAddPoints() {
        binding.tvToAddPointsBlueTeam.setText(getMatchValueWithPlusSign());
        binding.tvToAddPointsWhiteTeam.setText(getMatchValueWithPlusSign());
    }

    private void setupButtonsActions() {
        binding.btnToAddPointsBlueTeam.setOnClickListener(view1 -> {
            viewModel.getMatch().getBlueTeam().setScore(getBlueTeamScore() + getMatchValue());
            binding.tvBlueTeamScore.setText(String.valueOf(getBlueTeamScore()));
        });

        binding.btnToAddPointsWhiteTeam.setOnClickListener(view1 -> {
            viewModel.getMatch().getWhiteTeam().setScore(getWhiteTeamScore() + getMatchValue());
            binding.tvWhiteTeamScore.setText(String.valueOf(getWhiteTeamScore()));
        });

        binding.btnToAddMatchValue.setOnClickListener(view1 -> {
            if (getMatchValue() == 1){
                viewModel.getMatch().setMatchValue(3);
            } else {
                viewModel.getMatch().setMatchValue(getMatchValue() + 3);
            }
            setupTextViewToAddPoints();

            if (getMatchValue() == 12)
                binding.btnToAddMatchValue.setVisibility(View.GONE);
        });
    }

    private int getWhiteTeamScore() {
        return viewModel.getMatch().getWhiteTeam().getScore();
    }

    private int getBlueTeamScore() {
        return viewModel.getMatch().getBlueTeam().getScore();
    }

    private int getMatchValue() {
        return viewModel.getMatch().getMatchValue();
    }

    private String getMatchValueWithPlusSign() {
        return "+ " + viewModel.getMatch().getMatchValue();
    }
}