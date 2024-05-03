package com.example.melitruko.presentation.viewmodel;

import android.app.Application;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.melitruko.R;
import com.example.melitruko.domain.model.Match;
import com.example.melitruko.domain.model.Team;

public class MatchViewModel extends AndroidViewModel {

    private Match match;

    private final MutableLiveData<Integer> mBlueTeamScore = new MutableLiveData<>();
    public LiveData<Integer> blueTeamScoreLiveData = mBlueTeamScore;

    private final MutableLiveData<Integer> mWhiteTeamScore = new MutableLiveData<>();
    public LiveData<Integer> whiteTeamScoreLiveData = mWhiteTeamScore;

    private final MutableLiveData<Integer> mMatchValue = new MutableLiveData<>();
    public LiveData<Integer> matchValueLiveData = mMatchValue;

    public MatchViewModel(@NonNull Application application) {
        super(application);
    }

    public void toAddPointsBlueTeam() {
        getMatch().getBlueTeam().setScore(getBlueTeamScore() + getMatchValue());
        mBlueTeamScore.postValue(getBlueTeamScore());
        if (getBlueTeamScore() == 12) {
            getMatch().setWinnerTeam(getMatch().getBlueTeam());
            getMatch().setLoserTeam(getMatch().getWhiteTeam());
        }
        getMatch().setValue(getMatch().getInitialValueMatch());
    }

    public void toAddPointsWhiteTeam() {
        getMatch().getWhiteTeam().setScore(getWhiteTeamScore() + getMatchValue());
        mWhiteTeamScore.postValue(getWhiteTeamScore());
        if (getWhiteTeamScore() == 12) {
            getMatch().setWinnerTeam(getMatch().getWhiteTeam());
            getMatch().setLoserTeam(getMatch().getBlueTeam());
        }
        getMatch().setValue(getMatch().getInitialValueMatch());
    }

    public void setupMatchValue() {
        if (getMatchValue() == getMatch().getInitialValueMatch()) {
            getMatch().setValue(getMatch().getAdditionalValueMatch());
        } else {
            getMatch().setValue(getMatchValue() + getMatch().getAdditionalValueMatch());
        }
        mMatchValue.postValue(getMatchValue());
    }

    public void resetMatchValue() {
        getMatch().setValue(getMatch().getInitialValueMatch());
        mMatchValue.postValue(getMatchValue());
    }

    public int getWhiteTeamScore() {
        return getMatch().getWhiteTeam().getScore();
    }

    public int getBlueTeamScore() {
        return getMatch().getBlueTeam().getScore();
    }

    public int getMatchValue() {
        return getMatch().getValue();
    }

    public void setNewMatch(Parcelable blueTeam, Parcelable whiteTeam) {
        match = new Match((Team) blueTeam, (Team) whiteTeam);
    }

    public int setMessageButtonToAddPoints() {
        switch (getMatchValue()) {
            case 3:
                return R.string.txt_btn_six;
            case 6:
                return R.string.txt_btn_nine;
            case 9:
                return R.string.txt_btn_twelve;
            default:
                return R.string.txt_btn_truco;
        }
    }

    public int maximumValueMatch(){
        return getMatch().getMaximumValue();
    }

    public boolean isTheMaximumValueOfTheMatch(){
        return getMatchValue() == maximumValueMatch();
    }

    public Match getMatch() {
        return match;
    }

    public int setLayoutMatch() {
        switch (getMatch().getQtdPlayersMatch()) {
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

    public int getWinnerTeamScore(){
        return getMatch().getWinnerTeam().getScore();
    }

    public int getLoserTeamScore(){
        return getMatch().getLoserTeam().getScore();
    }
}
