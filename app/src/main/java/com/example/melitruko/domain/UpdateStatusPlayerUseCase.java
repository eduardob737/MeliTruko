package com.example.melitruko.domain;

import com.example.melitruko.data.repositories.PlayersRepository;


public class UpdateStatusPlayerUseCase {
    private final PlayersRepository playersRepository;

    public UpdateStatusPlayerUseCase(PlayersRepository playersRepository) {
        this.playersRepository = playersRepository;
    }

    public void invoke(int id){
        playersRepository.updateStatusPlayer(id);
    }
}

