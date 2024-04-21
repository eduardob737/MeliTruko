package com.example.melitruko.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.melitruko.model.Player;
import com.example.melitruko.model.Team;

import java.net.URI;

public class HomeViewModel extends AndroidViewModel {

    private Player player;
    private Team blueTeam = new Team();
    private Team whiteTeam = new Team();

    public HomeViewModel(@NonNull Application application) {
        super(application);
        blueTeam.setColor(Team.ColorTeam.BLUE);
        whiteTeam.setColor(Team.ColorTeam.WHITE);
    }

    public void setNewPlayer(String name, URI photo){
        player = new Player();
        player.setName(name);
        player.setPhoto(photo);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Team getBlueTeam() {
        return blueTeam;
    }

    public void setBlueTeam(Team blueTeam) {
        this.blueTeam = blueTeam;
    }

    public Team getWhiteTeam() {
        return whiteTeam;
    }

    public void setWhiteTeam(Team whiteTeam) {
        this.whiteTeam = whiteTeam;
    }
}
