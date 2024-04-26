package com.example.melitruko.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Team implements Parcelable {

    public enum QtdTeamPlayers { ONE_PLAYER, TWO_PLAYERS, THREE_PLAYERS}
    public enum ColorTeam {BLUE, WHITE}

    private ColorTeam color;
    private Player player1;
    private Player player2;
    private Player player3;
    private int score = 0;

    public Team (){
    }

    protected Team(Parcel in) {
        player1 = in.readParcelable(Player.class.getClassLoader());
        player2 = in.readParcelable(Player.class.getClassLoader());
        player3 = in.readParcelable(Player.class.getClassLoader());
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

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getPlayer3() {
        return player3;
    }

    public void setPlayer3(Player player3) {
        this.player3 = player3;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeParcelable(player1, i);
        parcel.writeParcelable(player2, i);
        parcel.writeParcelable(player3, i);
        parcel.writeInt(score);
    }
}
