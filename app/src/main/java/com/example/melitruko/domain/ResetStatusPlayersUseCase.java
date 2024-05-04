package com.example.melitruko.domain;

import com.example.melitruko.data.repositories.PlayersRepository;

public class ResetStatusPlayersUseCase {
    private final PlayersRepository playersRepository;

    public ResetStatusPlayersUseCase(PlayersRepository playersRepository) {
        this.playersRepository = playersRepository;
    }

    public void invoke(){
        playersRepository.resetStatusPlayers();
    }
}
