package com.example.melitruko.domain.model;

public class Match {

    private int matchValue = 1;
    private final int initialValueMatch = 1;
    private final int additionalValueMatch = 3;
    private final int finalValueMatch = 12;
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

    public int getInitialValueMatch() {
        return initialValueMatch;
    }

    public int getAdditionalValueMatch() {
        return additionalValueMatch;
    }

    public int getFinalValueMatch() {
        return finalValueMatch;
    }
}
