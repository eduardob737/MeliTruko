package com.example.melitruko.domain;

import com.example.melitruko.data.repositories.PlayersRepository;
import com.example.melitruko.domain.model.Player;

import java.util.List;

public class GetPlayersListUseCase {
    private final PlayersRepository playersRepository;

    public GetPlayersListUseCase(PlayersRepository playersRepository) {
        this.playersRepository = playersRepository;
    }

    public List<Player> invoke(){
        return playersRepository.getPlayersList();
    }
}
