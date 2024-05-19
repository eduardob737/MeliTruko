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
import com.example.melitruko.domain.model.Player;
import com.example.melitruko.presentation.ui.view.adapter.PlayerAdapter;
import com.example.melitruko.presentation.viewmodel.HomeViewModel;

import java.util.List;

public class PlayersControlFragment extends Fragment {

    private FragmentPlayersControlBinding binding;
    private HomeViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPlayersControlBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        setupButtonsActions();

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        setupObservers();
    }

    private void setupObservers() {
        viewModel.playersListLiveData.observe(getViewLifecycleOwner(), list -> {
            if (list.isEmpty()) {
                binding.ivPerson.setVisibility(View.VISIBLE);
                binding.tvNonePlayer.setVisibility(View.VISIBLE);
            } else {
                setupRecyclerView(list);
            }
        });
    }

    private void setupRecyclerView(List<Player> list) {
        PlayerAdapter adapter = new PlayerAdapter(list);
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