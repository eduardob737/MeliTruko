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

        setupRecyclerView();
        setupButtonsActions();
    }

    private void setupRecyclerView() {
        //PlayerAdapter adapter = new PlayerAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //binding.recyclerView.setAdapter(adapter);
    }

    private void setupButtonsActions() {
        binding.btnAddPlayer.setOnClickListener(view -> openDialogCreateNewPlayer());
        binding.icBack.setOnClickListener(view -> finish());
    }

    private void openDialogCreateNewPlayer() {
        NewPlayerFragment fragment = new NewPlayerFragment();
        fragment.show(getSupportFragmentManager(), "new_player");
    }
}