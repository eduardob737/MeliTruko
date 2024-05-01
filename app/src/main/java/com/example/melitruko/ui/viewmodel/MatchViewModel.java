package com.example.melitruko.ui.viewmodel;

import android.app.Application;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.melitruko.R;
import com.example.melitruko.domain.model.Match;
import com.example.melitruko.domain.model.Team;

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

    public int setLayoutMatch(){
        Log.i("isa", "num: " + getMatch().getQtdPlayersMatch());
        switch (getMatch().getQtdPlayersMatch()){
            case 2:
               return R.layout.layout_two_players_match;
            case 4:
                return R.layout.layout_four_players_match;
            case 6:
                return R.layout.layout_six_players_match;
            default:
                return 0;
        }
    }
}
