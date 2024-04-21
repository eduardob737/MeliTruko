package com.example.melitruko.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.melitruko.model.Match;
import com.example.melitruko.model.Player;
import com.example.melitruko.model.Team;

import java.io.Serializable;
import java.net.URI;

public class MatchViewModel extends AndroidViewModel {

    private Match match;

    public MatchViewModel(@NonNull Application application) {
        super(application);
    }

    public void setNewMatch(Serializable blueTeam, Serializable whiteTeam){
        match = new Match((Team) blueTeam, (Team) whiteTeam);
    }

    public Match getMatch() {
        return match;
    }
}
