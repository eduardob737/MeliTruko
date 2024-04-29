package com.example.melitruko.ui.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.melitruko.databinding.FragmentTwoPlayersBinding;
import com.example.melitruko.ui.viewmodel.HomeViewModel;

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
        viewModel.playerLiveData.observe(getViewLifecycleOwner(), (Observer<Object>) obj -> {
            if (obj != null) {
                setupChosenPlayers();
            }
        });
    }

    private void setupChosenPlayers() {
        if (viewModel.getBlueTeam().getPlayer1() != null) {
            binding.ivPlayer1.setImageURI(viewModel.getBlueTeam().getPlayer1().getPhoto());
            binding.tvPlayer1.setText(viewModel.getBlueTeam().getPlayer1().getName());
        }

        if (viewModel.getWhiteTeam().getPlayer1() != null) {
            binding.ivPlayer2.setImageURI(viewModel.getWhiteTeam().getPlayer1().getPhoto());
            binding.tvPlayer2.setText(viewModel.getWhiteTeam().getPlayer1().getName());
        }
    }

    private void setupPlayersButton() {
        Bundle bundle = new Bundle();
        binding.ivPlayer1.setOnClickListener(view1 -> {
            bundle.putString("TEAM", "BLUE");
            bundle.putInt("PLAYER", 1);
            showPlayersListFragment(bundle);
        });

        binding.ivPlayer2.setOnClickListener(view1 -> {
            bundle.putString("TEAM", "WHITE");
            bundle.putInt("PLAYER", 1);
            showPlayersListFragment(bundle);
        });
    }

    private void showPlayersListFragment(Bundle bundle){
        PlayerListFragment playerListFragment = new PlayerListFragment();
        playerListFragment.setArguments(bundle);
        playerListFragment.show(requireActivity().getSupportFragmentManager(), "list");
    }
}