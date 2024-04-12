package com.example.melitruko.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.melitruko.model.Match;
import com.example.melitruko.model.Player;
import com.example.melitruko.model.Team;

import java.net.URI;

public class MatchViewModel extends AndroidViewModel {

    private Player player;
    private Team team;
    private Match match;

    public MatchViewModel(@NonNull Application application) {
        super(application);
    }

    private void setNewMatch(Team team1, Team team2){
        match = new Match(team1, team2);
    }

    private void setNewTeam(Team.QtdTeamPlayers qtdTeamPlayer){
        team = new Team(qtdTeamPlayer);
    }

    private void setNewPlayer(String name, URI photo){
        player = new Player();
        player.setName(name);
        player.setPhoto(photo);
    }

}
