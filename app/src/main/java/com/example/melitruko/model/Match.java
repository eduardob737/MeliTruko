package com.example.melitruko.model;

public class Match {

    public enum QtdPlayers {
        TWO_PLAYERS, FOUR_PLAYERS, SIX_PLAYERS;
    }

    private int matchValue = 1;
    private Team blueTeam;
    private Team whiteTeam;

    public Match (Team blueTeam, Team whiteTeam){
        this.blueTeam = blueTeam;
        this.whiteTeam = whiteTeam;
    }
    public Match (){}

    public int getMatchValue() {
        return matchValue;
    }

    public void setMatchValue(int matchValue) {
        this.matchValue = matchValue;
    }

    public Team getBlueTeam() {
        return blueTeam;
    }

    public void setBlueTeam(Team blueTeam) {
        this.blueTeam = blueTeam;
    }

    public Team getWhiteTeam() {
        return whiteTeam;
    }

    public void setWhiteTeam(Team whiteTeam) {
        this.whiteTeam = whiteTeam;
    }
}
