package com.example.melitruko.presentation.ui.view.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.melitruko.R;
import com.example.melitruko.databinding.FragmentEndGameBinding;
import com.example.melitruko.domain.model.Player;
import com.example.melitruko.presentation.viewmodel.HomeViewModel;

public class EndGameFragment extends DialogFragment {

    private FragmentEndGameBinding binding;
    private HomeViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        binding = FragmentEndGameBinding.inflate(getLayoutInflater(), container, false);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        binding.btnToBack.setOnClickListener(view -> dismiss());
        binding.btnNewGame.setOnClickListener(view -> {
            dismiss();
            getParentFragmentManager().beginTransaction().replace(R.id.fragment_container_view_home, MenuFragment.class, null).commit();
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

        binding.ivPlayer1Winner.setImageBitmap(winnerPlayer1.getImageBitmap());
        binding.ivPlayer1Loser.setImageBitmap(loserPlayer1.getImageBitmap());
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

        binding.ivPlayer2Winner.setImageBitmap(winnerPlayer2.getImageBitmap());
        binding.ivPlayer2Loser.setImageBitmap(loserPlayer2.getImageBitmap());
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

        binding.ivPlayer3Winner.setImageBitmap(winnerPlayer3.getImageBitmap());
        binding.ivPlayer3Loser.setImageBitmap(loserPlayer3.getImageBitmap());
    }
}
