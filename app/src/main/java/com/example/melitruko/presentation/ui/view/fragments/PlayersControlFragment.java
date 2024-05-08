package com.example.melitruko.presentation.ui.view.fragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.melitruko.R;
import com.example.melitruko.databinding.ActivityPlayersControlBinding;
import com.example.melitruko.databinding.FragmentNewPlayerBinding;
import com.example.melitruko.databinding.FragmentPlayersControlBinding;
import com.example.melitruko.databinding.FragmentSetupMatchBinding;
import com.example.melitruko.presentation.ui.view.adapter.PlayerAdapter;
import com.example.melitruko.presentation.viewmodel.HomeViewModel;
import com.example.melitruko.presentation.viewmodel.ViewModelProviderFactory;

import java.util.Objects;

public class PlayersControlFragment extends Fragment {

    private FragmentPlayersControlBinding binding;
    private HomeViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPlayersControlBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        setupRecyclerView();
        setupButtonsActions();

        return binding.getRoot();
    }

    private void setupRecyclerView() {
        PlayerAdapter adapter = new PlayerAdapter(viewModel.getPlayers());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(adapter);
    }

    private void setupButtonsActions() {
        binding.btnAddPlayer.setOnClickListener(view -> openDialogCreateNewPlayer());
    }

    private void openDialogCreateNewPlayer() {
        NewPlayerFragment fragment = new NewPlayerFragment();
        fragment.show(getParentFragmentManager(), "new_player");
    }
}