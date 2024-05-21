package com.example.melitruko.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.melitruko.data.repositories.PlayerRepository;
import com.example.melitruko.data.repositories.RepositoryTemp;

import java.lang.reflect.InvocationTargetException;

public class ViewModelProviderFactory implements ViewModelProvider.Factory {

    private final RepositoryTemp repositoryTemp;
    private final PlayerRepository playerRepository;

    public ViewModelProviderFactory(RepositoryTemp repositoryTemp, PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
        this.repositoryTemp = repositoryTemp;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(RepositoryTemp.class, PlayerRepository.class).newInstance(repositoryTemp, playerRepository);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
