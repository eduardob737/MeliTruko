package com.example.melitruko.domain;

import androidx.lifecycle.LiveData;

import com.example.melitruko.data.repositories.DatabaseDataSource;
import com.example.melitruko.data.repositories.PlayerRepository;
import com.example.melitruko.data.repositories.RepositoryTemp;
import com.example.melitruko.domain.model.Player;

import java.util.List;

public class GetPlayersListUseCase {
    private final RepositoryTemp repositoryTemp;

    public GetPlayersListUseCase(RepositoryTemp repositoryTemp) {
        this.repositoryTemp = repositoryTemp;
    }

    public LiveData<List<Player>> invoke(){
        return repositoryTemp.getAllPlayers();
    }
}
