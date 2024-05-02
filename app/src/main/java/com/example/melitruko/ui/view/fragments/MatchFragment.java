package com.example.melitruko.ui.view.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import java.util.Objects;

public class MatchFragment extends Fragment {

    private FragmentMatchBinding binding;
    private MatchViewModel viewModel;
    private View layoutTeams;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(MatchViewModel.class);
        binding = FragmentMatchBinding.inflate(getLayoutInflater(), container, false);

        setupPlayersTeams();
        setupTextViewTeamsScore();
        setupTextViewToAddPoints();
        setupButtonsActions();

        return binding.getRoot();
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

    private void setupTextViewTeamsScore() {
        binding.tvBlueTeamScore.setText(String.valueOf(getBlueTeamScore()));
        binding.tvWhiteTeamScore.setText(String.valueOf(getWhiteTeamScore()));
    }

    private void setupTextViewToAddPoints() {
        binding.tvToAddPointsBlueTeam.setText(getMatchValueWithPlusSign());
        binding.tvToAddPointsWhiteTeam.setText(getMatchValueWithPlusSign());
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

    private void setupAlertDialog(){
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
}