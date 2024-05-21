package com.example.melitruko.domain.business.usecases;

import com.example.melitruko.data.repositories.PlayerRepository;
import com.example.melitruko.domain.model.Player;

import java.util.List;

public class GetInternalPlayersListUseCase {

    private final PlayerRepository playerRepository;

    public GetInternalPlayersListUseCase(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> invoke(){
        return playerRepository.getAllPlayersLikeList();
    }
}
