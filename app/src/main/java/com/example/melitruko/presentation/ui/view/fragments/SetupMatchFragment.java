package com.example.melitruko.presentation.ui.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.melitruko.R;
import com.example.melitruko.databinding.FragmentSetupMatchBinding;
import com.example.melitruko.presentation.ui.view.activities.MatchActivity;
import com.example.melitruko.presentation.viewmodel.HomeViewModel;

public class SetupMatchFragment extends Fragment {

    private FragmentSetupMatchBinding binding;
    private HomeViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSetupMatchBinding.inflate(getLayoutInflater(), container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        setupButtonsActions();
        setupShowLayouts();

        return binding.getRoot();
    }

    private void setupShowLayouts() {
        binding.chipGroup.setOnCheckedStateChangeListener((chipGroup, list) -> {
            viewModel.clearViewModel();

            if (binding.chipOneVersusOne.isChecked()) {
                viewModel.getBlueTeam().setQtdPlayers(1);
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container_view, TwoPlayersFragment.class, null).commit();
                binding.chipOneVersusOne.setEnabled(false);
                binding.chipTwoVersusTwo.setEnabled(true);
                binding.chipThreeVersusThree.setEnabled(true);

            } else if (binding.chipTwoVersusTwo.isChecked()) {
                viewModel.getBlueTeam().setQtdPlayers(2);
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container_view, FourPlayersFragment.class, null).commit();
                binding.chipTwoVersusTwo.setEnabled(false);
                binding.chipOneVersusOne.setEnabled(true);
                binding.chipThreeVersusThree.setEnabled(true);

            } else if (binding.chipThreeVersusThree.isChecked()) {
                viewModel.getBlueTeam().setQtdPlayers(3);
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container_view, SixPlayersFragment.class, null).commit();
                binding.chipThreeVersusThree.setEnabled(false);
                binding.chipTwoVersusTwo.setEnabled(true);
                binding.chipOneVersusOne.setEnabled(true);
            }

            binding.textViewChoosePlayers.setVisibility(View.VISIBLE);
        });
    }

    private boolean playerValidation() {
        switch (viewModel.getBlueTeam().getQtdPlayers()) {
            case 1:
                if ((viewModel.getBlueTeam().getPlayers().get(0) == null) || (viewModel.getWhiteTeam().getPlayers().get(0) == null)) {
                    Toast.makeText(requireActivity(), "Escolha os 2 jogadores antes de iniciar a partida", Toast.LENGTH_SHORT).show();
                    return false;
                }
                break;
            case 2:
                if ((viewModel.getBlueTeam().getPlayers().get(1) == null) || (viewModel.getWhiteTeam().getPlayers().get(1) == null)) {
                    Toast.makeText(requireActivity(), "Escolha os 4 jogadores antes de iniciar a partida", Toast.LENGTH_SHORT).show();
                    return false;
                }
                break;
            case 3:
                if ((viewModel.getBlueTeam().getPlayers().get(2) == null) || (viewModel.getWhiteTeam().getPlayers().get(2) == null)) {
                    Toast.makeText(requireActivity(), "Escolha os 6 jogadores antes de iniciar a partida", Toast.LENGTH_SHORT).show();
                    return false;
                }
                break;
        }
        return true;
    }

    private void setupButtonsActions() {
        binding.btnStartGame.setOnClickListener(view1 -> {
            if (binding.chipGroup.getCheckedChipId() == -1) {
                Toast.makeText(requireActivity(), "Escolha a quantidade de jogadores", Toast.LENGTH_SHORT).show();
            } else {
                if (playerValidation()) {
                    Intent intent = new Intent(requireActivity(), MatchActivity.class);
                    intent.putExtra("BLUE_TEAM", viewModel.getBlueTeam());
                    intent.putExtra("WHITE_TEAM", viewModel.getWhiteTeam());
                    startActivity(intent);
                    requireActivity().finish();
                }
            }
        });
    }


}