package com.example.melitruko.presentation.ui.view.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.melitruko.R;
import com.example.melitruko.databinding.FragmentMenuBinding;

public class MenuFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentMenuBinding binding = FragmentMenuBinding.inflate(getLayoutInflater(), container, false);

        binding.btnPlayersList.setOnClickListener(view -> transitionPlayerControl());
        binding.btnMatch.setOnClickListener(view -> transitionSetupMatch());
        setupHandleOnBackPressed();

        return binding.getRoot();
    }

    private void transitionPlayerControl() {
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_view_home, PlayersControlFragment.class, null)
                .addToBackStack(null)
                .commit();
    }

    private void transitionSetupMatch() {
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_view_home, SetupMatchFragment.class, null)
                .addToBackStack(null)
                .commit();
    }

    private void setupHandleOnBackPressed() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                setupAlertDialog();
            }
        });
    }

    private void setupAlertDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(requireContext());
        dialog.setCancelable(true);
        dialog.setTitle(R.string.txt_app_exit_confirmation);
        dialog.setIcon(R.drawable.ic_caution);
        dialog.setPositiveButton(R.string.dialog_btn_exit, (dialogInterface, i) -> requireActivity().finish());
        dialog.setNegativeButton(R.string.dialog_btn_cancel, null);
        dialog.show();
    }
}