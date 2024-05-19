package com.example.melitruko.data.repositories;

import com.example.melitruko.domain.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayersRepository {

    // Lista interna que futuramente receberá a lista do banco de dados
    List<Player> playerList = new ArrayList<>();

    public PlayersRepository() {
        createPlayersList();
    }

    public void createPlayersList() {
    }

    public List<Player> getPlayersList() {
        return playerList;
    }

    public void updateStatusPlayer(int id) {
        int position = getPlayerPosition(id);
        try {
            playerList.get(position).setPartOfATeam(!playerList.get(position).isPartOfATeam());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getPlayerPosition(int id) {
        for (int i = 0; i < getPlayersList().size(); i++) {
            if (id == playerList.get(i).getId()) {
                return i;
            }
        }
        return -1;
    }

    public void resetStatusPlayers() {
        for (int i = 0; i < playerList.size(); i++) {
            playerList.get(i).setPartOfATeam(false);
        }
    }
}
