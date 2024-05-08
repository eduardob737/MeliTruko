package com.example.melitruko.presentation.ui.view.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.melitruko.R;
import com.example.melitruko.databinding.FragmentMenuBinding;
import com.example.melitruko.presentation.ui.view.activities.HomeActivity;

public class MenuFragment extends Fragment {

    private FragmentMenuBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMenuBinding.inflate(getLayoutInflater(), container, false);

        binding.btnPlayersList.setOnClickListener(view -> getParentFragmentManager().beginTransaction().replace(R.id.fragment_container_view_home, PlayersControlFragment.class, null).addToBackStack(null).commit());
        binding.btnMatch.setOnClickListener(view -> getParentFragmentManager().beginTransaction().replace(R.id.fragment_container_view_home, SetupMatchFragment.class, null).addToBackStack(null).commit());

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                setupAlertDialog();
            }
        });

        return binding.getRoot();
    }

    private void setupAlertDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(requireContext());
        dialog.setCancelable(true);
        dialog.setTitle("Deseja sair do aplicativo?");
        dialog.setIcon(R.drawable.ic_caution);
        dialog.setPositiveButton("Sair", (dialogInterface, i) -> requireActivity().finish());
        dialog.setNegativeButton("Cancelar", null);
        dialog.show();
    }
}