package com.example.melitruko.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.melitruko.R;
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
        binding.btnBack.setOnClickListener(view ->
                requireActivity().finish()
        );

        binding.btnToAddPointsBlueTeam.setOnClickListener(view1 -> {
            viewModel.getMatch().getBlueTeam().setScore(getBlueTeamScore() + getMatchValue());
            binding.tvBlueTeamScore.setText(String.valueOf(getBlueTeamScore()));
            resetMatchValue();
        });

        binding.btnToAddPointsWhiteTeam.setOnClickListener(view1 -> {
            viewModel.getMatch().getWhiteTeam().setScore(getWhiteTeamScore() + getMatchValue());
            binding.tvWhiteTeamScore.setText(String.valueOf(getWhiteTeamScore()));
            resetMatchValue();
        });

        binding.btnToAddMatchValue.setOnClickListener(view1 -> {
            if (getMatchValue() == viewModel.getMatch().getInitialValueMatch()) {
                viewModel.getMatch().setMatchValue(viewModel.getMatch().getAdditionalValueMatch());
            } else {
                viewModel.getMatch().setMatchValue(getMatchValue() + viewModel.getMatch().getAdditionalValueMatch());
            }
            setupTextViewToAddPoints();
            setupTextBtnToAddMatchValue();

            if (getMatchValue() == viewModel.getMatch().getFinalValueMatch())
                binding.btnToAddMatchValue.setVisibility(View.GONE);
        });
    }

    private void setupTextBtnToAddMatchValue() {
        switch (getMatchValue()) {
            case 1:
                binding.btnToAddMatchValue.setText(R.string.txt_btn_truco);
                break;
            case 3:
                binding.btnToAddMatchValue.setText(R.string.txt_btn_six);
                break;
            case 6:
                binding.btnToAddMatchValue.setText(R.string.txt_btn_nine);
                break;
            case 9:
                binding.btnToAddMatchValue.setText(R.string.txt_btn_twelve);
                break;
        }
    }

    private void resetMatchValue() {
        viewModel.getMatch().setMatchValue(viewModel.getMatch().getInitialValueMatch());
        setupTextViewToAddPoints();
        setupTextBtnToAddMatchValue();
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