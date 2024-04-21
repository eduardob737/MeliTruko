package com.example.melitruko.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.melitruko.databinding.FragmentTwoPlayersBinding;
import com.example.melitruko.viewmodel.HomeViewModel;

import java.net.URI;

public class TwoPlayersFragment extends Fragment {

    private FragmentTwoPlayersBinding binding;
    private HomeViewModel viewModel;
    private URI uriMock;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTwoPlayersBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configuraAcoesBotoesPlayers();

       /* Utils.setupActionButtonPlayer(requireContext(),binding.ivPlayer1);
        Utils.setupActionButtonPlayer(requireContext(),binding.ivPlayer2);*/
    }

    private void configuraAcoesBotoesPlayers() {
        binding.ivPlayer1.setOnClickListener(view1 -> {
            viewModel.setNewPlayer("mock1_blue", uriMock);
            viewModel.getBlueTeam().setPlayer1(viewModel.getPlayer());
        });

        binding.ivPlayer2.setOnClickListener(view1 -> {
            viewModel.setNewPlayer("mock1_white", uriMock);
            viewModel.getWhiteTeam().setPlayer1(viewModel.getPlayer());
        });
    }
}