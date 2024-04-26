package com.example.melitruko.view.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.melitruko.R;
import com.example.melitruko.databinding.ActivityMatchBinding;
import com.example.melitruko.model.Match;
import com.example.melitruko.view.fragments.MatchFragment;
import com.example.melitruko.viewmodel.MatchViewModel;
import com.example.melitruko.viewmodel.ViewModelProviderFactory;

import java.util.Objects;

public class MatchActivity extends AppCompatActivity {

    private ActivityMatchBinding binding;
    private MatchViewModel viewModel;
    private ViewModelProviderFactory factory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        factory = new ViewModelProviderFactory(getApplication());
        viewModel = new ViewModelProvider(this, factory).get(MatchViewModel.class);
        binding = ActivityMatchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle blueTeamBundle = Objects.requireNonNull(getIntent().getExtras());
        Bundle whiteTeamBundle = Objects.requireNonNull(getIntent().getExtras());

        viewModel.setNewMatch(blueTeamBundle.getParcelable("BLUE_TEAM"), whiteTeamBundle.getParcelable("WHITE_TEAM"));

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, MatchFragment.class, null).commit();
    }
}