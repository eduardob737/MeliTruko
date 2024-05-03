package com.example.melitruko.presentation.ui.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.melitruko.databinding.FragmentTwoPlayersBinding;
import com.example.melitruko.domain.model.Player;
import com.example.melitruko.domain.model.Team;
import com.example.melitruko.presentation.viewmodel.HomeViewModel;

import java.util.List;

public class TwoPlayersFragment extends Fragment {

    private FragmentTwoPlayersBinding binding;
    private HomeViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTwoPlayersBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        setupObservers();
        setupPlayersButton();
        return binding.getRoot();
    }

    private void setupObservers() {
        viewModel.playerLiveData.observe(getViewLifecycleOwner(), (Observer<Object>) player -> {
            if (player != null) {
                setupChosenPlayers();
            }
        });
    }

    private void setupChosenPlayers() {
        List<Player> blueList = viewModel.getBlueTeam().getPlayers();
        List<Player> whiteList = viewModel.getWhiteTeam().getPlayers();

        if ((!blueList.isEmpty()) && (blueList.get(0) != null)) {
            binding.ivPlayer1.setImageURI(blueList.get(0).getPhoto());
            binding.tvPlayer1.setText(blueList.get(0).getName());
        }

        if ((!whiteList.isEmpty()) && (whiteList.get(0) != null)) {
            binding.ivPlayer2.setImageURI(whiteList.get(0).getPhoto());
            binding.tvPlayer2.setText(whiteList.get(0).getName());
        }
    }

    private void setupPlayersButton() {
        binding.ivPlayer1.setOnClickListener(view1 -> {
            viewModel.setTeamAtributes(Team.ColorTeam.BLUE, 0);
            showPlayersListFragment();
        });

        binding.ivPlayer2.setOnClickListener(view1 -> {
            viewModel.setTeamAtributes(Team.ColorTeam.WHITE, 0);
            showPlayersListFragment();
        });
    }

    private void showPlayersListFragment() {
        PlayerListFragment playerListFragment = new PlayerListFragment();
        playerListFragment.show(requireActivity().getSupportFragmentManager(), "list");
    }
}