package com.example.melitruko.presentation.ui.view.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.melitruko.R;
import com.example.melitruko.databinding.FragmentPlayersListBinding;
import com.example.melitruko.domain.model.Player;
import com.example.melitruko.presentation.ui.view.RecyclerItemClickListener;
import com.example.melitruko.presentation.ui.view.adapter.PlayerAdapter;
import com.example.melitruko.presentation.viewmodel.HomeViewModel;

import java.util.List;

public class PlayersListFragment extends DialogFragment {
    private FragmentPlayersListBinding binding;
    private HomeViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        binding = FragmentPlayersListBinding.inflate(inflater, container, false);

        this.getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        getPlayersList();
        setupButtonsDialog();

        return binding.getRoot();
    }

    private void getPlayersList() {
        List<Player> playersList = viewModel.getList();
        if (playersList.isEmpty()) {
            binding.ivPerson.setVisibility(View.VISIBLE);
            binding.tvNonePlayer.setVisibility(View.VISIBLE);
        } else {
            setupRecyclerView(playersList);
        }
    }

    private void setupRecyclerView(List<Player> list) {
        PlayerAdapter adapter = new PlayerAdapter(list);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.setAdapter(adapter);

        binding.recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(requireContext(), binding.recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (viewModel.isPlayerChosen(position)) {
                    Toast.makeText(requireContext(), "Esse jogador já foi escolhido", Toast.LENGTH_SHORT).show();
                } else {
                    if (binding.recyclerView.getChildViewHolder(view).itemView.isPressed()) {
                        binding.recyclerView.getChildViewHolder(view).itemView.setPressed(false);
                        viewModel.setPositionList(-1);
                    } else {
                        binding.recyclerView.setPressed(false);
                        binding.recyclerView.getChildViewHolder(view).itemView.setPressed(true);
                        viewModel.setPositionList(position);
                    }
                }
            }

            @Override
            public void onLongItemClick(View view, int position) {
            }
        }));
    }

    private void setupButtonsDialog() {
        binding.btnConfirme.setOnClickListener(view1 -> {
            if (viewModel.getPositionList() == -1) {
                Toast.makeText(requireActivity(), "Escolha um jogador", Toast.LENGTH_SHORT).show();
            } else {
                setupPlayers(viewModel.getPlayerOfList(viewModel.getPositionList()));
                this.dismiss();
            }
        });

        binding.btnNewPlayer.setOnClickListener(view1 -> {
            this.dismiss();
            getParentFragmentManager().beginTransaction().replace(R.id.fragment_container_view_home, PlayersControlFragment.class, null).addToBackStack(null).commit();
        });
    }

    private void setupPlayers(Player player) {
        viewModel.createNewPlayer(player);
    }
}
