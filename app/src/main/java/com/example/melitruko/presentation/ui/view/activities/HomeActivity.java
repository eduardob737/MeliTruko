package com.example.melitruko.presentation.ui.view.activities;

import android.os.Bundle;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.melitruko.R;
import com.example.melitruko.databinding.ActivityHomeBinding;
import com.example.melitruko.presentation.ui.view.fragments.MenuFragment;
import com.example.melitruko.presentation.ui.view.fragments.PlayersControlFragment;
import com.example.melitruko.presentation.ui.view.fragments.SetupMatchFragment;
import com.example.melitruko.presentation.viewmodel.HomeViewModel;
import com.example.melitruko.presentation.viewmodel.ViewModelProviderFactory;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewModelProviderFactory factory = new ViewModelProviderFactory(getApplication());
        HomeViewModel viewModel = new ViewModelProvider(getViewModelStore(), factory).get(HomeViewModel.class);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_home, MenuFragment.class, null).commit();
    }
}