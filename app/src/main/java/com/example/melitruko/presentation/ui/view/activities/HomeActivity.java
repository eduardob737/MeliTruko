package com.example.melitruko.presentation.ui.view.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.melitruko.R;
import com.example.melitruko.data.database.AppDatabase;
import com.example.melitruko.data.database.dao.PlayerDAO;
import com.example.melitruko.data.repositories.DatabaseDataSource;
import com.example.melitruko.data.repositories.PlayerRepository;
import com.example.melitruko.data.repositories.RepositoryTemp;
import com.example.melitruko.databinding.ActivityHomeBinding;
import com.example.melitruko.presentation.ui.view.fragments.MenuFragment;
import com.example.melitruko.presentation.viewmodel.HomeViewModel;
import com.example.melitruko.presentation.viewmodel.ViewModelProviderFactory;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private RepositoryTemp repositoryTemp = null;
    private PlayerRepository playerRepository = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupDependencies();

        ViewModelProviderFactory factory = new ViewModelProviderFactory(repositoryTemp, playerRepository);
        HomeViewModel viewModel = new ViewModelProvider(this, factory).get(HomeViewModel.class);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_home, MenuFragment.class, null).commit();
    }

    private void setupDependencies() {
        PlayerDAO playerDAO = AppDatabase.getDatabase(getApplicationContext()).playerDAO();
        repositoryTemp = new DatabaseDataSource(playerDAO);
        playerRepository = new PlayerRepository();
    }
}