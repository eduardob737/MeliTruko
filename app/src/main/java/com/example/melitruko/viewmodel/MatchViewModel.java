package com.example.melitruko.viewmodel;

import android.app.Application;
import android.os.Parcelable;

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

    public void setNewMatch(Parcelable blueTeam, Parcelable whiteTeam){
        match = new Match((Team) blueTeam, (Team) whiteTeam);
    }

    public Match getMatch() {
        return match;
    }
}
