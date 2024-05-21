package com.example.melitruko.domain;

import com.example.melitruko.data.repositories.PlayerRepository;

public class ResetStatusPlayersUseCase {
    private final PlayerRepository playerRepository;

    public ResetStatusPlayersUseCase(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void invoke(){
        playerRepository.resetStatusPlayers();
    }
}
