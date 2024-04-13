package com.example.melitruko.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.melitruko.R;
import com.example.melitruko.databinding.ActivityHomeBinding;
import com.example.melitruko.model.Match;
import com.example.melitruko.model.Team;
import com.example.melitruko.view.fragments.FourPlayersFragment;
import com.example.melitruko.view.fragments.SixPlayersFragment;
import com.example.melitruko.view.fragments.TwoPlayersFragment;
import com.example.melitruko.viewmodel.HomeViewModel;
import com.example.melitruko.viewmodel.MatchViewModel;
import com.example.melitruko.viewmodel.ViewModelProviderFactory;

import java.net.URI;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private HomeViewModel viewModel;
    private ViewModelProviderFactory factory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        factory = new ViewModelProviderFactory(getApplication());
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(getViewModelStore(), factory).get(HomeViewModel.class);
        setContentView(binding.getRoot());

        /*NavHostFragment navHost = NavHostFragment.create(R.navigation.nav_graph_home);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_view, navHost)
                .setPrimaryNavigationFragment(navHost)
                .commit();
*/
        configuraExibicaoLayouts();

        binding.ivTeam.setOnClickListener(view -> {
            startActivity(new Intent(this, PlayersListActivity.class));
        });

        binding.btnStartGame.setOnClickListener(view1 -> {
            if (binding.chipGroup.getCheckedChipId() == -1) {
                Toast.makeText(this, "Escolha a quantidade de jogadores", Toast.LENGTH_SHORT).show();
            } else {
                // TODO validar se configuração do jogo está completa e correta antes de prosseguir

                viewModel.setNumber(5);
                Intent intent = new Intent(this, MatchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void configuraExibicaoLayouts() {
        binding.chipGroup.setOnCheckedStateChangeListener((chipGroup, list) -> {
            if (binding.chipOneVersusOne.isChecked()){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, TwoPlayersFragment.class, null).commit();
                binding.chipOneVersusOne.setEnabled(false);
                binding.chipTwoVersusTwo.setEnabled(true);
                binding.chipThreeVersusThree.setEnabled(true);

            } else if (binding.chipTwoVersusTwo.isChecked()) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, FourPlayersFragment.class, null).commit();
                binding.chipTwoVersusTwo.setEnabled(false);
                binding.chipOneVersusOne.setEnabled(true);
                binding.chipThreeVersusThree.setEnabled(true);

            } else if (binding.chipThreeVersusThree.isChecked()){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, SixPlayersFragment.class, null).commit();
                binding.chipThreeVersusThree.setEnabled(false);
                binding.chipTwoVersusTwo.setEnabled(true);
                binding.chipOneVersusOne.setEnabled(true);

            } else {
                Toast.makeText(this, "Escolha uma opção", Toast.LENGTH_SHORT).show();
            }
        });
    }
}