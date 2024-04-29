package com.example.melitruko.ui.view.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.melitruko.databinding.ActivityPlayersControlBinding;

public class PlayersControlActivity extends AppCompatActivity {

    private ActivityPlayersControlBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayersControlBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        binding.icBack.setOnClickListener(view -> {
            finish();
        });
    }
}