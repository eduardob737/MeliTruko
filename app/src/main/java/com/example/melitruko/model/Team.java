package com.example.melitruko.model;

public class Team {

    public enum QtdTeamPlayers { ONE_PLAYER, TWO_PLAYERS, THREE_PLAYERS}
    public enum ColorTeam {BLUE, WHITE}

    private QtdTeamPlayers qtdPlayers;
    private Player player1;
    private Player player2;
    private Player player3;
    private ColorTeam color;
    private int score = 0;

    public Team (QtdTeamPlayers qtdPlayers){
        this.qtdPlayers = qtdPlayers;
    }

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
}
