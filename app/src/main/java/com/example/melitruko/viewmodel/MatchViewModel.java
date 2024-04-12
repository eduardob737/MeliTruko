package com.example.melitruko.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.melitruko.model.Match;
import com.example.melitruko.model.Player;
import com.example.melitruko.model.Team;

import java.net.URI;

public class MatchViewModel extends AndroidViewModel {

    private Match match;

    public MatchViewModel(@NonNull Application application) {
        super(application);
    }

    private void setNewMatch(Team team1, Team team2){
        match = new Match(team1, team2);
    }



}
