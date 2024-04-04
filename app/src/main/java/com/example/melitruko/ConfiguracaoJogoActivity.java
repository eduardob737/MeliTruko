package com.example.melitruko;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.melitruko.databinding.ActivityConfiguracaoJogoBinding;

public class ConfiguracaoJogoActivity extends AppCompatActivity {

    private ActivityConfiguracaoJogoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfiguracaoJogoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

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

        binding.btnStartGame.setOnClickListener(view1 -> {
            if (binding.chipGroup.getCheckedChipId() == -1)
                Toast.makeText(this, "Escolha a quantidade de jogadores", Toast.LENGTH_SHORT).show();
        });
    }
}