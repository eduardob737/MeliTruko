package com.example.melitruko.data.repositories;

import android.database.Observable;
import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;

import com.example.melitruko.domain.model.Player;

import java.util.List;

public interface RepositoryTemp {

    void insert(String name, String photoPath);

    void update(int id, String name, String photoPath);

    void delete(int id);

    LiveData<List<Player>> getAllPlayers();
}
