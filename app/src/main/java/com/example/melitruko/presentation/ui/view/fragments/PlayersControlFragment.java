package com.example.melitruko.presentation.ui.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.melitruko.databinding.FragmentPlayersControlBinding;
import com.example.melitruko.presentation.ui.view.adapter.PlayerAdapter;
import com.example.melitruko.presentation.viewmodel.HomeViewModel;

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
        if (viewModel.getPlayers().isEmpty()) {
            binding.ivPerson.setVisibility(View.VISIBLE);
            binding.tvNonePlayer.setVisibility(View.VISIBLE);
        } else {
            PlayerAdapter adapter = new PlayerAdapter(viewModel.getPlayers());
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            binding.recyclerView.setAdapter(adapter);
        }
    }

    private void setupButtonsActions() {
        binding.btnAddPlayer.setOnClickListener(view -> openDialogCreateNewPlayer());
    }

    private void openDialogCreateNewPlayer() {
        NewPlayerFragment fragment = new NewPlayerFragment();
        fragment.show(getParentFragmentManager(), "new_player");
    }
}