package com.example.melitruko.ui.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.melitruko.R;
import com.example.melitruko.databinding.ActivityMatchBinding;
import com.example.melitruko.ui.view.fragments.MatchFragment;
import com.example.melitruko.ui.viewmodel.MatchViewModel;
import com.example.melitruko.ui.viewmodel.ViewModelProviderFactory;

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

        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish();
                startActivity(new Intent(MatchActivity.this, HomeActivity.class));
            }
        });
    }
}