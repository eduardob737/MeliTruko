package com.example.melitruko.data.repositories;

import androidx.lifecycle.LiveData;

import com.example.melitruko.data.database.dao.PlayerDAO;
import com.example.melitruko.domain.model.Player;

import java.util.List;

public class PlayerDatabaseDataSource implements PlayerRepository {

    private final PlayerDAO playerDAO;

    public PlayerDatabaseDataSource(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    @Override
    public void insert(Player player) {
        playerDAO.insert(player);
    }

    @Override
    public void update(Player player) {
        // TODO criar useCase para atualizar jogador com código comentado abaixo
        /*Player player = new Player(name, photoPath);
        player.setId(id);*/
        playerDAO.update(player);
    }

    @Override
    public void delete(int id) {
        playerDAO.delete(id);
    }

    @Override
    public LiveData<List<Player>> getAllPlayers() {
        return playerDAO.getAllPlayers();
    }

    @Override
    public List<Player> getAllPlayersLikeList() {
        return playerDAO.getAllPlayersLikeList();
    }
}
