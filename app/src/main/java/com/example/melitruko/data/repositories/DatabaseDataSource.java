package com.example.melitruko.data.repositories;

import androidx.lifecycle.LiveData;

import com.example.melitruko.data.database.dao.PlayerDAO;
import com.example.melitruko.domain.model.Player;

import java.util.List;

public class DatabaseDataSource implements RepositoryTemp{

    private final PlayerDAO playerDAO;
    private List<Player> listPlayersIntern = null;

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

    @Override
    public void setInternPlayersList(List<Player> list) {
        listPlayersIntern = list;
    }

    @Override
    public List<Player> getInternPlayersList() {
        return listPlayersIntern;
    }

    @Override
    public Player getPlayer(int position) {
        return listPlayersIntern.get(position);
    }

    @Override
    public boolean isPlayerChosen(int position) {
        return listPlayersIntern.get(position).isPartOfATeam();
    }

    @Override
    public void updateStatusPlayer(int id) {
        int position = getPositionPlayer(id);
        try {
            Player player = getInternPlayersList().get(position);
            player.setPartOfATeam(!player.isPartOfATeam());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getPositionPlayer(int id) {
        for (int i = 0; i < getInternPlayersList().size(); i++) {
            if (id == getInternPlayersList().get(i).getId()) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void resetStatusPlayer() {

    }
}
