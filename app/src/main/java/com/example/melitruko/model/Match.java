package com.example.melitruko.model;
public class Match {

    public enum QtdPlayers {
        TWO_PLAYERS, FOUR_PLAYERS, SIX_PLAYERS;
    }

    private int matchValue = 1;
    private Team team1;
    private Team team2;

    public Match (Team team1, Team team2){
        this.team1 = team1;
        this.team2 = team2;
    }

    public int getMatchValue() {
        return matchValue;
    }

    public void setMatchValue(int matchValue) {
        this.matchValue = matchValue;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }
}
