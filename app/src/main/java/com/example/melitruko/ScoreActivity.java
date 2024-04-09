package com.example.melitruko;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.melitruko.databinding.ActivityScoreBinding;
import com.example.melitruko.fragments.ScoreFragment;

public class ScoreActivity extends AppCompatActivity {

    private ActivityScoreBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, ScoreFragment.class, null);
    }
}