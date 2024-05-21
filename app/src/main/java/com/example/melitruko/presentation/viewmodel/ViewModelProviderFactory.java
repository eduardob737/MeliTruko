package com.example.melitruko.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.melitruko.data.repositories.PlayerRepository;
import com.example.melitruko.domain.business.PlayerBusiness;

import java.lang.reflect.InvocationTargetException;

public class ViewModelProviderFactory implements ViewModelProvider.Factory {

    private final PlayerBusiness playerBusiness;
    private final PlayerRepository playerRepository;

    public ViewModelProviderFactory(PlayerBusiness playerBusiness, PlayerRepository playerRepository) {
        this.playerBusiness = playerBusiness;
        this.playerRepository = playerRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(PlayerBusiness.class, PlayerRepository.class).newInstance(playerBusiness, playerRepository);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
