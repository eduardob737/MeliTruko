package com.example.melitruko.presentation.ui.view.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.melitruko.databinding.FragmentPlayersListBinding;
import com.example.melitruko.domain.model.Player;
import com.example.melitruko.presentation.ui.view.adapter.PlayerAdapter;
import com.example.melitruko.presentation.ui.view.activities.PlayersControlActivity;
import com.example.melitruko.presentation.ui.view.RecyclerItemClickListener;
import com.example.melitruko.presentation.viewmodel.HomeViewModel;

public class PlayerListFragment extends DialogFragment {
    private FragmentPlayersListBinding binding;
    private HomeViewModel viewModel;
    private int position = -1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        binding = FragmentPlayersListBinding.inflate(inflater, container, false);

        this.getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PlayerAdapter adapter = new PlayerAdapter(viewModel.getPlayers());

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.setAdapter(adapter);

        binding.recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(requireContext(), binding.recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (isChosenPlayer(position)){
                    Toast.makeText(requireContext(), "Esse jogador já foi escolhido", Toast.LENGTH_SHORT).show();
                } else {
                    if (binding.recyclerView.getChildViewHolder(view).itemView.isPressed()) {
                        binding.recyclerView.getChildViewHolder(view).itemView.setPressed(false);
                        setPosition(-1);
                    } else {
                        binding.recyclerView.setPressed(false);
                        binding.recyclerView.getChildViewHolder(view).itemView.setPressed(true);
                        setPosition(position);
                    }
                }
            }

            @Override
            public void onLongItemClick(View view, int position) {
            }
        }));

        binding.btnConfirme.setOnClickListener(view1 -> {
            if (position == -1) {
                Toast.makeText(requireActivity(), "Escolha um jogador", Toast.LENGTH_SHORT).show();
            } else {
                setupPlayers(viewModel.getPlayers().get(position));
                this.dismiss();
            }
        });
        binding.btnNewPlayer.setOnClickListener(view1 -> {
            startActivity(new Intent(requireActivity(), PlayersControlActivity.class));
        });
    }

    private boolean isChosenPlayer(int position) {
        return viewModel.isChosenPlayer(position);
    }

    private void setPosition(int position) {
        this.position = position;
    }

    private void setupPlayers(Player player) {
        viewModel.createNewPlayer(player);
    }
}
