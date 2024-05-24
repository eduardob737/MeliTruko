package com.example.melitruko.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.melitruko.data.repositories.PlayerRepository;
import com.example.melitruko.domain.business.MainBusiness;

import java.lang.reflect.InvocationTargetException;

public class ViewModelProviderFactory implements ViewModelProvider.Factory {

    private final MainBusiness mainBusiness;
    private final PlayerRepository playerRepository;

    public ViewModelProviderFactory(MainBusiness mainBusiness, PlayerRepository playerRepository) {
        this.mainBusiness = mainBusiness;
        this.playerRepository = playerRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(MainBusiness.class, PlayerRepository.class).newInstance(mainBusiness, playerRepository);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
