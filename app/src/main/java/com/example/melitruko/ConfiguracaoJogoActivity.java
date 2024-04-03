package com.example.melitruko;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.melitruko.databinding.ActivityConfiguracaoJogoBinding;
import com.google.android.material.chip.ChipGroup;

public class ConfiguracaoJogoActivity extends AppCompatActivity {

    private ActivityConfiguracaoJogoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfiguracaoJogoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.chipGroup.setOnCheckedStateChangeListener((chipGroup, list) -> {
            Toast.makeText(this, "oi", Toast.LENGTH_SHORT).show();
            Log.i("chip", "oi 1");
        });

       /* binding.chipOneVersusOne.setOnCheckedChangeListener((compoundButton, b) ->
            Log.i("chip", "oi 1")
        );

        binding.chipTwoVersusTwo.setOnClickListener(view -> {
            Log.i("chip", "oi 2");
        });

        binding.chipThreeVersusThree.setOnClickListener(view -> {
            Log.i("chip", "oi 3");
        });*/

    }
}