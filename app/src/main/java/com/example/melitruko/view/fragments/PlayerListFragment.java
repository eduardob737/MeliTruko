package com.example.melitruko.view.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.melitruko.databinding.FragmentPlayersListBinding;
import com.example.melitruko.model.Player;
import com.example.melitruko.view.RecyclerItemClickListener;
import com.example.melitruko.view.adapter.PlayerAdapter;
import com.example.melitruko.viewmodel.HomeViewModel;

import java.util.Objects;

public class PlayerListFragment extends Fragment {
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
        binding = FragmentPlayersListBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PlayerAdapter adapter = new PlayerAdapter(viewModel.getList());

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setHasFixedSize(true);

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
                setupPlayers(viewModel.getList().get(position));
                requireActivity().getOnBackPressedDispatcher().onBackPressed();
            }
        });
    }

    private void setPosition(int position) {
        this.position = position;
    }

    private void setupPlayers(Player player) {
        viewModel.setPlayer(player);
        if ((TEAM_BUNDLE.equalsIgnoreCase("BLUE")) && (PLAYER_BUNDLE == 1)) {
            viewModel.getBlueTeam().setPlayer1(viewModel.getPlayer());
        } else if ((TEAM_BUNDLE.equalsIgnoreCase("WHITE")) && (PLAYER_BUNDLE == 1)) {
            viewModel.getWhiteTeam().setPlayer1(viewModel.getPlayer());
        }
    }

}
