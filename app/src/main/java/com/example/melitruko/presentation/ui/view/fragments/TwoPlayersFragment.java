package com.example.melitruko.presentation.ui.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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
        viewModel.playersBlueTeamLiveData.observe(getViewLifecycleOwner(), this::setupPlayersBlueTeam);
        viewModel.playersWhiteTeamLiveData.observe(getViewLifecycleOwner(), this::setupPlayersWhiteTeam);
    }

    private void setupPlayersBlueTeam(List<Player> list) {
            binding.ivPlayer1.setContentPadding(0,0,0,0);
            binding.ivPlayer1.setImageBitmap(list.get(0).getImageBitmap());
            binding.tvPlayer1.setText(list.get(0).getName());
    }

    private void setupPlayersWhiteTeam(List<Player> list) {
            binding.ivPlayer2.setContentPadding(0,0,0,0);
            binding.ivPlayer2.setImageBitmap(list.get(0).getImageBitmap());
            binding.tvPlayer2.setText(list.get(0).getName());
    }

    private void setupPlayersButton() {
        binding.ivPlayer1.setOnClickListener(view1 -> {
            viewModel.setTeamAttributes(Team.ColorTeam.BLUE, 0);
            showPlayersListFragment();
        });

        binding.ivPlayer2.setOnClickListener(view1 -> {
            viewModel.setTeamAttributes(Team.ColorTeam.WHITE, 0);
            showPlayersListFragment();
        });
    }

    private void showPlayersListFragment() {
        PlayersListFragment playersListFragment = new PlayersListFragment();
        playersListFragment.show(requireActivity().getSupportFragmentManager(), "list");
    }
}