package com.example.melitruko.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.melitruko.data.database.dao.PlayerDAO;
import com.example.melitruko.domain.model.Player;

@Database(entities = Player.class, version = 1, exportSchema = false)
abstract public class AppDatabase extends RoomDatabase {

    abstract PlayerDAO playerDAO();

    private static volatile AppDatabase appDatabase;

    static AppDatabase getDatabase(Context context) {
        if (appDatabase == null) {
            synchronized (AppDatabase.class) {
                if (appDatabase == null) {
                    appDatabase = Room.databaseBuilder(
                                    context,
                                    AppDatabase.class,
                                    "app_database")
                            .build();
                }
            }
        }
        return appDatabase;
    }
}
