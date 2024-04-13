package com.example.melitruko.view.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.melitruko.R;
import com.example.melitruko.databinding.ActivityMatchBinding;
import com.example.melitruko.view.fragments.MatchFragment;
import com.example.melitruko.viewmodel.HomeViewModel;
import com.example.melitruko.viewmodel.MatchViewModel;
import com.example.melitruko.viewmodel.ViewModelProviderFactory;

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

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, MatchFragment.class, null).commit();
    }
}