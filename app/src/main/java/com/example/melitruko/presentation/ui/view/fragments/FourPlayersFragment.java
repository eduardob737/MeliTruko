package com.example.melitruko.presentation.ui.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.melitruko.databinding.FragmentFourPlayersBinding;
import com.example.melitruko.domain.model.Player;
import com.example.melitruko.domain.model.Team;
import com.example.melitruko.presentation.viewmodel.HomeViewModel;

import java.util.List;

public class FourPlayersFragment extends Fragment {

    private FragmentFourPlayersBinding binding;
    private HomeViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFourPlayersBinding.inflate(getLayoutInflater(), container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        setupObservers();
        setupPlayersButton();
        return binding.getRoot();
    }

    private void setupObservers() {
        viewModel.playerLiveData.observe(getViewLifecycleOwner(), player -> {
            if (player != null) {
                setupChosenPlayers();
            }
        });
    }

    private void setupChosenPlayers() {
        List<Player> blueList = viewModel.getBlueTeam().getPlayers();
        List<Player> whiteList = viewModel.getWhiteTeam().getPlayers();

        if (blueList.get(0) != null){
            binding.ivPlayer1.setContentPadding(0,0,0,0);
            binding.ivPlayer1.setImageBitmap(blueList.get(0).getImageBitmap());
            binding.tvPlayer1.setText(blueList.get(0).getName());
        }

        if (blueList.get(1) != null){
            binding.ivPlayer2.setContentPadding(0,0,0,0);
            binding.ivPlayer2.setImageBitmap(blueList.get(1).getImageBitmap());
            binding.tvPlayer2.setText(blueList.get(1).getName());
        }

        if (whiteList.get(0) != null){
            binding.ivPlayer3.setContentPadding(0,0,0,0);
            binding.ivPlayer3.setImageBitmap(whiteList.get(0).getImageBitmap());
            binding.tvPlayer3.setText(whiteList.get(0).getName());
        }

        if (whiteList.get(1) != null){
            binding.ivPlayer4.setContentPadding(0,0,0,0);
            binding.ivPlayer4.setImageBitmap(whiteList.get(1).getImageBitmap());
            binding.tvPlayer4.setText(whiteList.get(1).getName());
        }
    }

    private void setupPlayersButton() {
        binding.ivPlayer1.setOnClickListener(view1 -> {
            viewModel.setTeamAtributes(Team.ColorTeam.BLUE, 0);
            showPlayersListFragment();
        });

        binding.ivPlayer2.setOnClickListener(view1 -> {
            viewModel.setTeamAtributes(Team.ColorTeam.BLUE, 1);
            showPlayersListFragment();
        });

        binding.ivPlayer3.setOnClickListener(view1 -> {
            viewModel.setTeamAtributes(Team.ColorTeam.WHITE, 0);
            showPlayersListFragment();
        });

        binding.ivPlayer4.setOnClickListener(view1 -> {
            viewModel.setTeamAtributes(Team.ColorTeam.WHITE, 1);
            showPlayersListFragment();
        });
    }

    private void showPlayersListFragment(){
        PlayerListFragment playerListFragment = new PlayerListFragment();
        playerListFragment.show(requireActivity().getSupportFragmentManager(), "list");
    }
}