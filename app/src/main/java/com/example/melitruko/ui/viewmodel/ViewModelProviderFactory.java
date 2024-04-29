package com.example.melitruko.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.melitruko.data.repositories.PlayersRepository;

import java.lang.reflect.InvocationTargetException;

public class ViewModelProviderFactory implements ViewModelProvider.Factory {

    Application application;

    public ViewModelProviderFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(Application.class).newInstance(application);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
