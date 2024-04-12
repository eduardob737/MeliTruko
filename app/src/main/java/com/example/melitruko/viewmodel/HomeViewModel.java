package com.example.melitruko.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.melitruko.model.Match;
import com.example.melitruko.model.Player;
import com.example.melitruko.model.Team;

import java.net.URI;

public class HomeViewModel extends AndroidViewModel {

    private Player player;
    private Team team = new Team();
    private Match match = new Match();

    public HomeViewModel(@NonNull Application application) {
        super(application);
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}
