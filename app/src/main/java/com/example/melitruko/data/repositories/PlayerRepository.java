package com.example.melitruko.data.repositories;

import androidx.lifecycle.LiveData;

import com.example.melitruko.domain.model.Player;

import java.util.List;

public interface PlayerRepository {

    void insert(Player player);

    void update(Player player);

    void delete(int id);

    LiveData<List<Player>> getAllPlayers();

    List<Player> getAllPlayersLikeList();

}
