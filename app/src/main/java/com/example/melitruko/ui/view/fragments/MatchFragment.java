package com.example.melitruko.ui.view.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.melitruko.R;
import com.example.melitruko.databinding.FragmentMatchBinding;
import com.example.melitruko.domain.model.Player;
import com.example.melitruko.ui.view.activities.HomeActivity;
import com.example.melitruko.ui.viewmodel.MatchViewModel;

public class MatchFragment extends Fragment {

    private FragmentMatchBinding binding;
    private MatchViewModel viewModel;
    private View layoutTeams;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(MatchViewModel.class);
        binding = FragmentMatchBinding.inflate(getLayoutInflater(), container, false);

        setupPlayersTeams();
        setupButtonsActions();
        setupDefaultScore();
        setupObservers();

        return binding.getRoot();
    }

    private void setupDefaultScore(){
        binding.tvBlueTeamScore.setText(String.valueOf(viewModel.getBlueTeamScore()));
        binding.tvWhiteTeamScore.setText(String.valueOf(viewModel.getWhiteTeamScore()));
        binding.tvToAddPointsBlueTeam.setText(formatValueMatch(viewModel.getMatchValue()));
        binding.tvToAddPointsWhiteTeam.setText(formatValueMatch(viewModel.getMatchValue()));
        binding.btnToAddMatchValue.setText(viewModel.setMessageButtonToAddPoints());
    }

    private void setupPlayersTeams() {
        binding.viewStub.setLayoutResource(viewModel.setLayoutMatch());
        layoutTeams = binding.viewStub.inflate();

        switch (viewModel.getMatch().getQtdPlayersMatch()) {
            case 2:
                setupTwoPlayers();
                break;

            case 4:
                setupFourPlayers();
                break;

            case 6:
                setupSixPlayers();
                break;
        }

    }

    private void setupTwoPlayers() {
        TextView tvNamePlayer1Blue = layoutTeams.findViewById(R.id.tv_name_player1_blue);
        TextView tvNamePlayer1White = layoutTeams.findViewById(R.id.tv_name_player1_white);

        ImageView ivPlayer1Blue = layoutTeams.findViewById(R.id.iv_player1_blue);
        ImageView ivPlayer1White = layoutTeams.findViewById(R.id.iv_player1_white);

        Player bluePlayer1 = viewModel.getMatch().getBlueTeam().getPlayers().get(0);
        Player whitePlayer1 = viewModel.getMatch().getWhiteTeam().getPlayers().get(0);

        tvNamePlayer1Blue.setText(bluePlayer1.getName());
        tvNamePlayer1White.setText(whitePlayer1.getName());

        ivPlayer1Blue.setImageURI(bluePlayer1.getPhoto());
        ivPlayer1White.setImageURI(whitePlayer1.getPhoto());
    }

    private void setupFourPlayers() {
        TextView tvNamePlayer1Blue = layoutTeams.findViewById(R.id.tv_name_player1_blue);
        TextView tvNamePlayer2Blue = layoutTeams.findViewById(R.id.tv_name_player2_blue);
        TextView tvNamePlayer1White = layoutTeams.findViewById(R.id.tv_name_player1_white);
        TextView tvNamePlayer2White = layoutTeams.findViewById(R.id.tv_name_player2_white);

        ImageView ivPlayer1Blue = layoutTeams.findViewById(R.id.iv_player1_blue);
        ImageView ivPlayer2Blue = layoutTeams.findViewById(R.id.iv_player2_blue);
        ImageView ivPlayer1White = layoutTeams.findViewById(R.id.iv_player1_white);
        ImageView ivPlayer2White = layoutTeams.findViewById(R.id.iv_player2_white);

        Player bluePlayer1 = viewModel.getMatch().getBlueTeam().getPlayers().get(0);
        Player bluePlayer2 = viewModel.getMatch().getBlueTeam().getPlayers().get(1);
        Player whitePlayer1 = viewModel.getMatch().getWhiteTeam().getPlayers().get(0);
        Player whitePlayer2 = viewModel.getMatch().getWhiteTeam().getPlayers().get(1);

        tvNamePlayer1Blue.setText(bluePlayer1.getName());
        tvNamePlayer2Blue.setText(bluePlayer2.getName());

        tvNamePlayer1White.setText(whitePlayer1.getName());
        tvNamePlayer2White.setText(whitePlayer2.getName());

        ivPlayer1Blue.setImageURI(bluePlayer1.getPhoto());
        ivPlayer2Blue.setImageURI(bluePlayer2.getPhoto());

        ivPlayer1White.setImageURI(whitePlayer1.getPhoto());
        ivPlayer2White.setImageURI(whitePlayer2.getPhoto());
    }

    private void setupSixPlayers() {
        TextView tvNamePlayer1Blue = layoutTeams.findViewById(R.id.tv_name_player1_blue);
        TextView tvNamePlayer2Blue = layoutTeams.findViewById(R.id.tv_name_player2_blue);
        TextView tvNamePlayer3Blue = layoutTeams.findViewById(R.id.tv_name_player3_blue);
        TextView tvNamePlayer1White = layoutTeams.findViewById(R.id.tv_name_player1_white);
        TextView tvNamePlayer2White = layoutTeams.findViewById(R.id.tv_name_player2_white);
        TextView tvNamePlayer3White = layoutTeams.findViewById(R.id.tv_name_player3_white);

        ImageView ivPlayer1Blue = layoutTeams.findViewById(R.id.iv_player1_blue);
        ImageView ivPlayer2Blue = layoutTeams.findViewById(R.id.iv_player2_blue);
        ImageView ivPlayer3Blue = layoutTeams.findViewById(R.id.iv_player3_blue);
        ImageView ivPlayer1White = layoutTeams.findViewById(R.id.iv_player1_white);
        ImageView ivPlayer2White = layoutTeams.findViewById(R.id.iv_player2_white);
        ImageView ivPlayer3White = layoutTeams.findViewById(R.id.iv_player3_white);

        Player bluePlayer1 = viewModel.getMatch().getBlueTeam().getPlayers().get(0);
        Player bluePlayer2 = viewModel.getMatch().getBlueTeam().getPlayers().get(1);
        Player bluePlayer3 = viewModel.getMatch().getBlueTeam().getPlayers().get(2);

        Player whitePlayer1 = viewModel.getMatch().getWhiteTeam().getPlayers().get(0);
        Player whitePlayer2 = viewModel.getMatch().getWhiteTeam().getPlayers().get(1);
        Player whitePlayer3 = viewModel.getMatch().getWhiteTeam().getPlayers().get(2);

        tvNamePlayer1Blue.setText(bluePlayer1.getName());
        tvNamePlayer2Blue.setText(bluePlayer2.getName());
        tvNamePlayer3Blue.setText(bluePlayer3.getName());

        tvNamePlayer1White.setText(whitePlayer1.getName());
        tvNamePlayer2White.setText(whitePlayer2.getName());
        tvNamePlayer3White.setText(whitePlayer3.getName());

        ivPlayer1Blue.setImageURI(bluePlayer1.getPhoto());
        ivPlayer2Blue.setImageURI(bluePlayer2.getPhoto());
        ivPlayer3Blue.setImageURI(bluePlayer3.getPhoto());

        ivPlayer1White.setImageURI(whitePlayer1.getPhoto());
        ivPlayer2White.setImageURI(whitePlayer2.getPhoto());
        ivPlayer3White.setImageURI(whitePlayer3.getPhoto());
    }

    private void setupObservers() {
        viewModel.blueTeamScoreLiveData.observe(getViewLifecycleOwner(), score -> {
            binding.tvBlueTeamScore.setText(String.valueOf(score));
            if (score == viewModel.maximumValueMatch())
                endGame();
        });
        viewModel.whiteTeamScoreLiveData.observe(getViewLifecycleOwner(), score -> {
            binding.tvWhiteTeamScore.setText(String.valueOf(score));
            if (score == viewModel.maximumValueMatch())
                endGame();
        });

        viewModel.matchValueLiveData.observe(getViewLifecycleOwner(), matchValue -> {
            binding.tvToAddPointsBlueTeam.setText(formatValueMatch(matchValue));
            binding.tvToAddPointsWhiteTeam.setText(formatValueMatch(matchValue));
            binding.btnToAddMatchValue.setText(viewModel.setMessageButtonToAddPoints());

            if (viewModel.isTheMaximumValueOfTheMatch()) {
                binding.btnToAddMatchValue.setVisibility(View.GONE);
            }
        });
    }

    private StringBuilder formatValueMatch(int value){
        StringBuilder text = new StringBuilder();
        text.append("+ ");
        text.append(value);
        return text;
    }

    private void setupButtonsActions() {
        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                setupAlertDialog();
            }
        });

        binding.btnBack.setOnClickListener(view -> {
            setupAlertDialog();
        });

        binding.btnToAddPointsBlueTeam.setOnClickListener(view1 -> {
            viewModel.toAddPointsBlueTeam();
            viewModel.resetMatchValue();
        });

        binding.btnToAddPointsWhiteTeam.setOnClickListener(view1 -> {
            viewModel.toAddPointsWhiteTeam();
            viewModel.resetMatchValue();
        });

        binding.btnToAddMatchValue.setOnClickListener(view1 -> {
            viewModel.setupMatchValue();
        });
    }

    private void setupAlertDialog() {
        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(R.layout.dialog_confirmation);
        dialog.setCancelable(true);

        dialog.findViewById(R.id.positive_button).setOnClickListener(view -> {
            requireActivity().finish();
            startActivity(new Intent(requireActivity(), HomeActivity.class));
        });

        dialog.findViewById(R.id.negative_button).setOnClickListener(view -> {
            dialog.dismiss();
        });

        dialog.show();
    }

    private void endGame() {
            EndGameFragment fragment = new EndGameFragment();
            fragment.show(requireActivity().getSupportFragmentManager(), "end_game");
    }
}