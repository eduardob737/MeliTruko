package com.example.melitruko.view.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.melitruko.R;
import com.example.melitruko.databinding.ActivityMatchBinding;
import com.example.melitruko.view.fragments.MatchFragment;
import com.example.melitruko.viewmodel.MatchViewModel;
import com.example.melitruko.viewmodel.MatchViewModelProviderFactory;

public class MatchActivity extends AppCompatActivity {

    private ActivityMatchBinding binding;
    private MatchViewModel viewModel;
    private MatchViewModelProviderFactory factory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, factory).get(MatchViewModel.class);
        binding = ActivityMatchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, MatchFragment.class, null);
    }
}