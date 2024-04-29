package com.example.melitruko.ui.view.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class PlayersListDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("TÍTULO")
                .setPositiveButton("CONFIRMAR", (dialogInterface, i) -> {

                })
                .setNegativeButton("CANCELAR", (dialogInterface, i) -> {

                });

        return builder.create();
    }
}
