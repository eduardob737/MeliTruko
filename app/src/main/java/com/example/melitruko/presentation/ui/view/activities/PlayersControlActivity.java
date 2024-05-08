package com.example.melitruko.presentation.ui.view.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.melitruko.databinding.ActivityPlayersControlBinding;
import com.example.melitruko.presentation.ui.view.adapter.PlayerAdapter;
import com.example.melitruko.presentation.ui.view.fragments.EndGameFragment;
import com.example.melitruko.presentation.ui.view.fragments.NewPlayerFragment;

public class PlayersControlActivity extends AppCompatActivity {

    private ActivityPlayersControlBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayersControlBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

}