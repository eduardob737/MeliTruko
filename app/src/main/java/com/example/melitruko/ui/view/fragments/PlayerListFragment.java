package com.example.melitruko.ui.view.fragments;

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
import com.example.melitruko.data.model.Player;
import com.example.melitruko.ui.view.activities.PlayersControlActivity;
import com.example.melitruko.ui.view.RecyclerItemClickListener;
import com.example.melitruko.ui.view.adapter.PlayerAdapter;
import com.example.melitruko.ui.viewmodel.HomeViewModel;

public class PlayerListFragment extends DialogFragment {
    private FragmentPlayersListBinding binding;
    private HomeViewModel viewModel;
    private String TEAM_BUNDLE;
    private int PLAYER_BUNDLE;
    private int position = -1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            TEAM_BUNDLE = bundle.getString("TEAM");
            PLAYER_BUNDLE = bundle.getInt("PLAYER");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
                if (binding.recyclerView.getChildViewHolder(view).itemView.isPressed()) {
                    binding.recyclerView.getChildViewHolder(view).itemView.setPressed(false);
                    setPosition(-1);
                } else {
                    binding.recyclerView.setPressed(false);
                    binding.recyclerView.getChildViewHolder(view).itemView.setPressed(true);
                    setPosition(position);
                }
                //TODO otimizar metodo setupPlayers
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

    private void setPosition(int position) {
        this.position = position;
    }

    private void setupPlayers(Player player) {
        viewModel.createNewPlayer(player);

        if ((TEAM_BUNDLE.equalsIgnoreCase("BLUE")) && (PLAYER_BUNDLE == 1)) {
            viewModel.getBlueTeam().setPlayer1(viewModel.getPlayer());
        } else if ((TEAM_BUNDLE.equalsIgnoreCase("WHITE")) && (PLAYER_BUNDLE == 1)) {
            viewModel.getWhiteTeam().setPlayer1(viewModel.getPlayer());
        }
    }

}
