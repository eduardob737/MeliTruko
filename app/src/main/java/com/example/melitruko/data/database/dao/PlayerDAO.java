package com.example.melitruko.data.database.dao;

import android.database.Observable;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.melitruko.domain.model.Player;

import java.util.List;

@Dao
public interface PlayerDAO {
    @Insert
    void insert(Player player);

    @Update
    void update(Player player);

    @Query("DELETE FROM players WHERE id = :id")
    void delete(int id);

    @Query("SELECT * FROM players")
    LiveData<List<Player>> getAllPlayers();
}
