package com.example.melitruko.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.melitruko.R;
import com.example.melitruko.databinding.ActivityHomeBinding;
import com.example.melitruko.model.Team;
import com.example.melitruko.view.fragments.FourPlayersFragment;
import com.example.melitruko.view.fragments.SixPlayersFragment;
import com.example.melitruko.view.fragments.TwoPlayersFragment;
import com.example.melitruko.viewmodel.HomeViewModel;
import com.example.melitruko.viewmodel.ViewModelProviderFactory;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private HomeViewModel viewModel;
    private int qtdPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewModelProviderFactory factory = new ViewModelProviderFactory(getApplication());
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(getViewModelStore(), factory).get(HomeViewModel.class);
        setContentView(binding.getRoot());

        setupShowLayouts();

        binding.btnPlayers.setOnClickListener(view -> {
            startActivity(new Intent(this, PlayersListActivity.class));
        });

        binding.btnStartGame.setOnClickListener(view1 -> {
            if (binding.chipGroup.getCheckedChipId() == -1) {
                Toast.makeText(this, "Escolha a quantidade de jogadores", Toast.LENGTH_SHORT).show();
            } else {
                if (playerValidation()) {
                    Intent intent = new Intent(this, MatchActivity.class);
                    intent.putExtra("BLUE_TEAM", viewModel.getBlueTeam());
                    intent.putExtra("WHITE_TEAM", viewModel.getWhiteTeam());
                    startActivity(intent);
                }
            }
        });
    }

    private void setupShowLayouts() {
        binding.chipGroup.setOnCheckedStateChangeListener((chipGroup, list) -> {
            if (binding.chipOneVersusOne.isChecked()) {
                qtdPlayers = 2;
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, TwoPlayersFragment.class, null).commit();
                binding.chipOneVersusOne.setEnabled(false);
                binding.chipTwoVersusTwo.setEnabled(true);
                binding.chipThreeVersusThree.setEnabled(true);

            } else if (binding.chipTwoVersusTwo.isChecked()) {
                qtdPlayers = 4;
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, FourPlayersFragment.class, null).commit();
                binding.chipTwoVersusTwo.setEnabled(false);
                binding.chipOneVersusOne.setEnabled(true);
                binding.chipThreeVersusThree.setEnabled(true);

            } else if (binding.chipThreeVersusThree.isChecked()) {
                qtdPlayers = 6;
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, SixPlayersFragment.class, null).commit();
                binding.chipThreeVersusThree.setEnabled(false);
                binding.chipTwoVersusTwo.setEnabled(true);
                binding.chipOneVersusOne.setEnabled(true);
            }

            binding.textViewChoosePlayers.setVisibility(View.VISIBLE);
        });
    }

    private boolean playerValidation() {
        switch (qtdPlayers) {
            case 2:
                if ((viewModel.getBlueTeam().getPlayer1() == null) || (viewModel.getWhiteTeam().getPlayer1() == null)) {
                    Toast.makeText(this, "Escolha os 2 jogadores antes de iniciar a partida", Toast.LENGTH_SHORT).show();
                    return false;
                }
                break;
            case 4:
                if ((viewModel.getBlueTeam().getPlayer1() == null) || (viewModel.getWhiteTeam().getPlayer1() == null) ||
                        (viewModel.getBlueTeam().getPlayer2() == null) || (viewModel.getWhiteTeam().getPlayer2() == null)) {
                    Toast.makeText(this, "Escolha os 4 jogadores antes de iniciar a partida", Toast.LENGTH_SHORT).show();
                    return false;
                }
                break;
            case 6:
                if ((viewModel.getBlueTeam().getPlayer1() == null) || (viewModel.getWhiteTeam().getPlayer1() == null) ||
                        (viewModel.getBlueTeam().getPlayer2() == null) || (viewModel.getWhiteTeam().getPlayer2() == null) ||
                        (viewModel.getBlueTeam().getPlayer3() == null) || (viewModel.getWhiteTeam().getPlayer3() == null)) {
                    Toast.makeText(this, "Escolha os 6 jogadores antes de iniciar a partida", Toast.LENGTH_SHORT).show();
                    return false;
                }
                break;
        }
        return true;
    }
}