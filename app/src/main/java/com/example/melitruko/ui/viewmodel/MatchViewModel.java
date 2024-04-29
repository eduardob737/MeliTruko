package com.example.melitruko.ui.viewmodel;

import android.app.Application;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.melitruko.data.model.Match;
import com.example.melitruko.data.model.Team;

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
