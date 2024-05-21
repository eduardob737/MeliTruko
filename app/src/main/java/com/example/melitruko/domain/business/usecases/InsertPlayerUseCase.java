package com.example.melitruko.domain.business.usecases;

import com.example.melitruko.data.repositories.PlayerRepository;
import com.example.melitruko.domain.model.Player;

public class InsertPlayerUseCase {

    private final PlayerRepository playerRepository;

    public InsertPlayerUseCase(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void invoke(String name, String photoPath){
        Player player = new Player(name, photoPath);
        playerRepository.insert(player);
    }
}
