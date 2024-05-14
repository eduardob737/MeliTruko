package com.example.melitruko.data.repositories;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;

import com.example.melitruko.domain.model.Player;

import java.util.List;

interface RepositoryTemp {

    void insert(String name, Bitmap photo);

    void update(int id, String name, Bitmap photo);

    void delete(int id);

    LiveData<List<Player>> getAllPlayers();
}
