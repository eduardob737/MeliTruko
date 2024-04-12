package com.example.melitruko.view.fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.melitruko.R;
import com.example.melitruko.Utils;
import com.example.melitruko.databinding.FragmentTwoPlayersBinding;
import com.example.melitruko.model.Team;
import com.example.melitruko.viewmodel.HomeViewModel;

import java.net.URI;
import java.util.Objects;

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
            viewModel.getTeam().setPlayer1(viewModel.getPlayer());
            viewModel.getTeam().setColor(Team.ColorTeam.BLUE);
            viewModel.getMatch().setTeam1(viewModel.getTeam());
        });

        binding.ivPlayer2.setOnClickListener(view1 -> {
            viewModel.setNewPlayer("mock1_white", uriMock);
            viewModel.getTeam().setPlayer1(viewModel.getPlayer());
            viewModel.getTeam().setColor(Team.ColorTeam.WHITE);
            viewModel.getMatch().setTeam2(viewModel.getTeam());
        });

        // TODO parei aqui...definir se o app terá uma ou duas VM e como implementar isso da melhor forma
    }
}