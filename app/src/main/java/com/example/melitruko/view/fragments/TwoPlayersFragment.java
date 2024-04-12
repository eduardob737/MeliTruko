package com.example.melitruko.view.fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.melitruko.R;
import com.example.melitruko.Utils;
import com.example.melitruko.databinding.FragmentTwoPlayersBinding;

import java.util.Objects;

public class TwoPlayersFragment extends Fragment {

    private FragmentTwoPlayersBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTwoPlayersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Utils.setupActionButtonPlayer(requireContext(),binding.ivPlayer1);
        Utils.setupActionButtonPlayer(requireContext(),binding.ivPlayer2);
    }

    private void configuraAcoesBotoesPlayers(View view) {
        view.setOnClickListener(view1 -> {
            Dialog dialog = new Dialog(requireContext());
            dialog.setContentView(R.layout.layout_lista_jogadores);
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        });
    }
}