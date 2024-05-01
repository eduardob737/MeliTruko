package com.example.melitruko.domain.model;

import java.util.ArrayList;
import java.util.function.Predicate;

public class Match {

    private int qtdPlayersMatch = 0;
    private int matchValue = 1;
    private final int initialValueMatch = 1;
    private final int additionalValueMatch = 3;
    private final int finalValueMatch = 12;
    private Team blueTeam;
    private Team whiteTeam;

    public Match (Team blueTeam, Team whiteTeam){
        this.blueTeam = blueTeam;
        this.whiteTeam = whiteTeam;
        this.qtdPlayersMatch = blueTeam.getQtdPlayers() * 2;
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

    public int getQtdPlayersMatch() {
        return qtdPlayersMatch;
    }

    public void setQtdPlayersMatch(int qtdPlayersMatch) {
        this.qtdPlayersMatch = qtdPlayersMatch;
    }
}
