package com.example.melitruko.data.repositories;

import com.example.melitruko.domain.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerRepository {

    // Lista interna que futuramente receberá a lista do banco de dados
    List<Player> playerList = new ArrayList<>();

    public void resetStatusPlayers() {
        for (int i = 0; i < playerList.size(); i++) {
            playerList.get(i).setPartOfATeam(false);
        }
    }
}
