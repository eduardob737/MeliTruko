package com.example.melitruko.domain;

import com.example.melitruko.data.repositories.PlayerRepository;


public class UpdateStatusPlayerUseCase {
    private final PlayerRepository playerRepository;

    public UpdateStatusPlayerUseCase(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void invoke(int id){
        playerRepository.updateStatusPlayer(id);
    }
}

