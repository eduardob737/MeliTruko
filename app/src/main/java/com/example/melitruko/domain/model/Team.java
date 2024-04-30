package com.example.melitruko.domain.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Team implements Parcelable {

    public enum QtdTeamPlayers { ONE_PLAYER, TWO_PLAYERS, THREE_PLAYERS}
    public enum ColorTeam {BLUE, WHITE}

    private QtdTeamPlayers qtdPlayers;
    private ColorTeam color;
    private ArrayList<Player> players;
    private int score = 0;

    public Team (){
        players = new ArrayList<>();
        players.add(0, null);
        players.add(1, null);
        players.add(2, null);
    }

    protected Team(Parcel in) {
        players = in.createTypedArrayList(Player.CREATOR);
        score = in.readInt();
    }

    public static final Creator<Team> CREATOR = new Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeTypedList(players);
        parcel.writeInt(score);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ColorTeam getColor() {
        return color;
    }

    public void setColor(ColorTeam color) {
        this.color = color;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public QtdTeamPlayers getQtdPlayers() {
        return qtdPlayers;
    }

    public void setQtdPlayers(QtdTeamPlayers qtdPlayers) {
        this.qtdPlayers = qtdPlayers;
    }
}
