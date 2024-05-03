package com.example.melitruko.domain.model;

public class Match {

    private int qtdPlayersMatch = 0;
    private int value = 1;
    private final int initialValueMatch = 1;
    private final int additionalValueMatch = 3;
    private final int maximumValue = 12;
    private Team blueTeam;
    private Team whiteTeam;
    private Team winnerTeam;
    private Team loserTeam;

    public Match (Team blueTeam, Team whiteTeam){
        this.blueTeam = blueTeam;
        this.whiteTeam = whiteTeam;
        this.qtdPlayersMatch = blueTeam.getQtdPlayers() * 2;
    }
    public Match (){}

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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

    public int getInitialValueMatch() {
        return initialValueMatch;
    }

    public int getAdditionalValueMatch() {
        return additionalValueMatch;
    }

    public int getMaximumValue() {
        return maximumValue;
    }

    public int getQtdPlayersMatch() {
        return qtdPlayersMatch;
    }

    public void setQtdPlayersMatch(int qtdPlayersMatch) {
        this.qtdPlayersMatch = qtdPlayersMatch;
    }

    public Team getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(Team winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    public Team getLoserTeam() {
        return loserTeam;
    }

    public void setLoserTeam(Team loserTeam) {
        this.loserTeam = loserTeam;
    }
}
