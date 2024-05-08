package com.example.melitruko.presentation.ui.view.fragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.melitruko.R;
import com.example.melitruko.databinding.ActivityHomeBinding;
import com.example.melitruko.databinding.FragmentMenuBinding;
import com.example.melitruko.presentation.viewmodel.HomeViewModel;

public class MenuFragment extends Fragment {

    private FragmentMenuBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMenuBinding.inflate(getLayoutInflater(), container, false);

        binding.btnPlayersList.setOnClickListener(view -> getParentFragmentManager().beginTransaction().replace(R.id.fragment_container_view_home, PlayersControlFragment.class, null).addToBackStack(null).commit());
        binding.btnMatch.setOnClickListener(view -> getParentFragmentManager().beginTransaction().replace(R.id.fragment_container_view_home, SetupMatchFragment.class, null).addToBackStack(null).commit());

        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Toast.makeText(requireActivity(), "Aperte mais uma vez para sair do app", Toast.LENGTH_SHORT).show();
                requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        requireActivity().finish();
                    }
                });
            }
        });

        return binding.getRoot();
    }
}