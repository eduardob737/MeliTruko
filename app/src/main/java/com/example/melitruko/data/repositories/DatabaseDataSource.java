package com.example.melitruko.data.repositories;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;

import com.example.melitruko.data.database.dao.PlayerDAO;
import com.example.melitruko.domain.model.Player;

import java.util.List;

public class DatabaseDataSource implements RepositoryTemp{

    private final PlayerDAO playerDAO;

    public DatabaseDataSource(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    @Override
    public void insert(String name, String photoPath) {
        Player player = new Player(name, photoPath);
        playerDAO.insert(player);
    }

    @Override
    public void update(int id, String name, String photoPath) {
        Player player = new Player(name, photoPath);
        player.setId(id);
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
}
