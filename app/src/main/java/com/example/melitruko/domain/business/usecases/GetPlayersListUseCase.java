package com.example.melitruko.domain.business.usecases;

import androidx.lifecycle.LiveData;

import com.example.melitruko.data.repositories.PlayerRepository;
import com.example.melitruko.domain.business.PlayerBusiness;
import com.example.melitruko.domain.model.Player;

import java.util.List;

public class GetPlayersListUseCase {
    private final PlayerRepository playerRepository;

    public GetPlayersListUseCase(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public LiveData<List<Player>> invoke(){
        return playerRepository.getAllPlayers();
    }
}
