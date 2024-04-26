package com.example.melitruko.view.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.melitruko.R;
import com.example.melitruko.databinding.FragmentTwoPlayersBinding;
import com.example.melitruko.viewmodel.HomeViewModel;

public class TwoPlayersFragment extends Fragment {

    private FragmentTwoPlayersBinding binding;
    private HomeViewModel viewModel;
    private Uri uriMock;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTwoPlayersBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupChosenPlayers();
        setupPlayersButton();
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
        binding.ivPlayer1.setOnClickListener(view1 -> {
            Bundle bundle = new Bundle();
            bundle.putString("TEAM", "BLUE");
            bundle.putInt("PLAYER", 1);
            getParentFragmentManager().beginTransaction().replace(R.id.fragment_container_view, PlayerListFragment.class, bundle).addToBackStack(null).commit();
        });
        binding.ivPlayer2.setOnClickListener(view1 -> {
            Bundle bundle = new Bundle();
            bundle.putString("TEAM", "WHITE");
            bundle.putInt("PLAYER", 1);
            getParentFragmentManager().beginTransaction().replace(R.id.fragment_container_view, PlayerListFragment.class, bundle).addToBackStack(null).commit();
        });
    }
}