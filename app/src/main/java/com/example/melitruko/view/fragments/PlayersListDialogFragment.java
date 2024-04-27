package com.example.melitruko.view.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.melitruko.model.Player;
import com.example.melitruko.view.adapter.PlayerAdapter;

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
