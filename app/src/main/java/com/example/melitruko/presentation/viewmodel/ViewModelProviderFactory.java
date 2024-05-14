package com.example.melitruko.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.melitruko.data.repositories.PlayersRepository;
import com.example.melitruko.data.repositories.RepositoryTemp;

import java.lang.reflect.InvocationTargetException;

public class ViewModelProviderFactory implements ViewModelProvider.Factory {

    private final RepositoryTemp repositoryTemp;
    private final PlayersRepository playersRepository;

    public ViewModelProviderFactory(RepositoryTemp repositoryTemp, PlayersRepository playersRepository) {
        this.playersRepository = playersRepository;
        this.repositoryTemp = repositoryTemp;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(RepositoryTemp.class, PlayersRepository.class).newInstance(repositoryTemp, playersRepository);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
