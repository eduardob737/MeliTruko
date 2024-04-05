package com.example.melitruko;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.melitruko.databinding.ActivityHomeBinding;
import com.example.melitruko.fragments.FourPlayersFragment;
import com.example.melitruko.fragments.SixPlayersFragment;
import com.example.melitruko.fragments.TwoPlayersFragment;


public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
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
            if (binding.chipGroup.getCheckedChipId() == -1)
                Toast.makeText(this, "Escolha a quantidade de jogadores", Toast.LENGTH_SHORT).show();
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