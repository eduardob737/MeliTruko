package com.example.melitruko.data.repositories;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;

import com.example.melitruko.data.database.dao.PlayerDAO;
import com.example.melitruko.domain.model.Player;

import java.util.List;

public class DatabaseDataSource implements RepositoryTemp{

    private PlayerDAO playerDAO;

    @Override
    public void insert(String name, Bitmap photo) {
        Player player = new Player(name, photo);
        playerDAO.insert(player);
    }

    @Override
    public void update(int id, String name, Bitmap photo) {
        Player player = new Player(name, photo);
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
