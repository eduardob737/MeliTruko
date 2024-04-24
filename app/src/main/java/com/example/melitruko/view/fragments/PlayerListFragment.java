package com.example.melitruko.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class PlayerListFragment extends Fragment {
    private FragmentPlayersListBinding binding;
    private HomeViewModel viewModel;
    private String TEAM_BUNDLE;
    private int PLAYER_BUNDLE;

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
                // TODO voltar a tela anterior e passar o objeto Player escolhido
                metodoTeste(viewModel.getList().get(position));
            }

            @Override
            public void onLongItemClick(View view, int position) {
            }
        }));
    }

    private void metodoTeste(Player player) {
        if ((TEAM_BUNDLE.equalsIgnoreCase("BLUE")) && (PLAYER_BUNDLE == 1)){
            viewModel.getBlueTeam().setPlayer1(player);
        } else if ((TEAM_BUNDLE.equalsIgnoreCase("WHITE")) && (PLAYER_BUNDLE == 1)){
            viewModel.getWhiteTeam().setPlayer1(player);
        }
    }

}
