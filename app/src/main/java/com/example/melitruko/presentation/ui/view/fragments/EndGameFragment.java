package com.example.melitruko.presentation.ui.view.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.melitruko.R;
import com.example.melitruko.databinding.FragmentEndGameBinding;
import com.example.melitruko.domain.model.Player;
import com.example.melitruko.presentation.ui.view.activities.HomeActivity;
import com.example.melitruko.presentation.viewmodel.MatchViewModel;

public class EndGameFragment extends DialogFragment {

    private FragmentEndGameBinding binding;
    private MatchViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(MatchViewModel.class);
        binding = FragmentEndGameBinding.inflate(getLayoutInflater(), container, false);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        binding.btnToBack.setOnClickListener(view -> dismiss());
        binding.btnNewGame.setOnClickListener(view -> {
            requireActivity().finish();
            startActivity(new Intent(requireActivity(), HomeActivity.class));
        });

        setupView();

        return binding.getRoot();
    }

    private void setupView() {
        binding.tvWinnerTeamScore.setText(String.valueOf(viewModel.getWinnerTeamScore()));
        binding.tvLoserTeamScore.setText(String.valueOf(viewModel.getLoserTeamScore()));

        switch (viewModel.getMatch().getQtdPlayersMatch()) {
            case 2:
                setupTwoPlayers();
                break;

            case 4:
                setupTwoPlayers();
                setupFourPlayers();
                break;

            case 6:
                setupTwoPlayers();
                setupFourPlayers();
                setupSixPlayers();
                break;
        }
    }

    private void setupTwoPlayers() {
        Player winnerPlayer1 = viewModel.getMatch().getWinnerTeam().getPlayers().get(0);
        Player loserPlayer1 = viewModel.getMatch().getLoserTeam().getPlayers().get(0);

        binding.tvNamePlayer1Winner.setText(winnerPlayer1.getName());
        binding.tvNamePlayer1Loser.setText(loserPlayer1.getName());

        binding.ivPlayer1Winner.setImageURI(winnerPlayer1.getPhoto());
        binding.ivPlayer1Loser.setImageURI(loserPlayer1.getPhoto());
    }

    private void setupFourPlayers() {
        Player winnerPlayer2 = viewModel.getMatch().getWinnerTeam().getPlayers().get(1);
        Player loserPlayer2 = viewModel.getMatch().getLoserTeam().getPlayers().get(1);

        binding.tvNamePlayer2Winner.setVisibility(View.VISIBLE);
        binding.tvNamePlayer2Loser.setVisibility(View.VISIBLE);

        binding.tvNamePlayer2Winner.setText(winnerPlayer2.getName());
        binding.tvNamePlayer2Loser.setText(loserPlayer2.getName());

        binding.ivPlayer2Winner.setVisibility(View.VISIBLE);
        binding.ivPlayer2Loser.setVisibility(View.VISIBLE);

        binding.ivPlayer2Winner.setImageURI(winnerPlayer2.getPhoto());
        binding.ivPlayer2Loser.setImageURI(loserPlayer2.getPhoto());
    }

    private void setupSixPlayers() {
        Player winnerPlayer3 = viewModel.getMatch().getWinnerTeam().getPlayers().get(2);
        Player loserPlayer3 = viewModel.getMatch().getLoserTeam().getPlayers().get(2);

        binding.tvNamePlayer3Winner.setVisibility(View.VISIBLE);
        binding.tvNamePlayer3Loser.setVisibility(View.VISIBLE);

        binding.tvNamePlayer3Winner.setText(winnerPlayer3.getName());
        binding.tvNamePlayer3Loser.setText(loserPlayer3.getName());

        binding.ivPlayer3Winner.setVisibility(View.VISIBLE);
        binding.ivPlayer3Loser.setVisibility(View.VISIBLE);

        binding.ivPlayer3Winner.setImageURI(winnerPlayer3.getPhoto());
        binding.ivPlayer3Loser.setImageURI(loserPlayer3.getPhoto());
    }
}
