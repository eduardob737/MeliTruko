package com.example.melitruko.ui.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.melitruko.R;
import com.example.melitruko.databinding.ActivityHomeBinding;
import com.example.melitruko.domain.model.Team;
import com.example.melitruko.ui.view.fragments.FourPlayersFragment;
import com.example.melitruko.ui.view.fragments.SixPlayersFragment;
import com.example.melitruko.ui.view.fragments.TwoPlayersFragment;
import com.example.melitruko.ui.viewmodel.HomeViewModel;
import com.example.melitruko.ui.viewmodel.ViewModelProviderFactory;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private HomeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewModelProviderFactory factory = new ViewModelProviderFactory(getApplication());
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(getViewModelStore(), factory).get(HomeViewModel.class);
        setContentView(binding.getRoot());

        setupShowLayouts();

        binding.btnPlayers.setOnClickListener(view -> {
            startActivity(new Intent(this, PlayersControlActivity.class));
        });

        binding.btnStartGame.setOnClickListener(view1 -> {
            if (binding.chipGroup.getCheckedChipId() == -1) {
                Toast.makeText(this, "Escolha a quantidade de jogadores", Toast.LENGTH_SHORT).show();
            } else {
                if (playerValidation()) {
                    Intent intent = new Intent(this, MatchActivity.class);
                    intent.putExtra("BLUE_TEAM",  viewModel.getBlueTeam());
                    intent.putExtra("WHITE_TEAM", viewModel.getWhiteTeam());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    private void setupShowLayouts() {
        binding.chipGroup.setOnCheckedStateChangeListener((chipGroup, list) -> {
            viewModel.clearViewModel();

            if (binding.chipOneVersusOne.isChecked()) {
                viewModel.getBlueTeam().setQtdPlayers(1);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, TwoPlayersFragment.class, null).commit();
                binding.chipOneVersusOne.setEnabled(false);
                binding.chipTwoVersusTwo.setEnabled(true);
                binding.chipThreeVersusThree.setEnabled(true);

            } else if (binding.chipTwoVersusTwo.isChecked()) {
                viewModel.getBlueTeam().setQtdPlayers(2);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, FourPlayersFragment.class, null).commit();
                binding.chipTwoVersusTwo.setEnabled(false);
                binding.chipOneVersusOne.setEnabled(true);
                binding.chipThreeVersusThree.setEnabled(true);

            } else if (binding.chipThreeVersusThree.isChecked()) {
                viewModel.getBlueTeam().setQtdPlayers(3);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, SixPlayersFragment.class, null).commit();
                binding.chipThreeVersusThree.setEnabled(false);
                binding.chipTwoVersusTwo.setEnabled(true);
                binding.chipOneVersusOne.setEnabled(true);
            }

            binding.textViewChoosePlayers.setVisibility(View.VISIBLE);
        });
    }

    private boolean playerValidation() {
        switch (viewModel.getBlueTeam().getQtdPlayers()) {
            case 1:
                if ((viewModel.getBlueTeam().getPlayers().get(0) == null) || (viewModel.getWhiteTeam().getPlayers().get(0) == null)) {
                    Toast.makeText(this, "Escolha os 2 jogadores antes de iniciar a partida", Toast.LENGTH_SHORT).show();
                    return false;
                }
                break;
            case 2:
                if ((viewModel.getBlueTeam().getPlayers().get(1) == null) || (viewModel.getWhiteTeam().getPlayers().get(1) == null)){
                    Toast.makeText(this, "Escolha os 4 jogadores antes de iniciar a partida", Toast.LENGTH_SHORT).show();
                    return false;
                }
                break;
            case 3:
                if ((viewModel.getBlueTeam().getPlayers().get(2) == null) || (viewModel.getWhiteTeam().getPlayers().get(2) == null)){
                    Toast.makeText(this, "Escolha os 6 jogadores antes de iniciar a partida", Toast.LENGTH_SHORT).show();
                    return false;
                }
                break;
        }
        return true;
    }

}