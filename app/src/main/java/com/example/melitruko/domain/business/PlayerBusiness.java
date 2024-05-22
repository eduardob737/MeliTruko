package com.example.melitruko.domain.business;

import com.example.melitruko.domain.model.Player;

import java.util.List;

public class PlayerBusiness {

    private List<Player> internalListPlayer;
    private int positionList = -1;

    public void setPositionList(int position) {
        this.positionList = position;
    }

    public int getPositionList(){
        return positionList;
    }

    public void setInternalListPlayer(List<Player> internalListPlayer) {
        this.internalListPlayer = internalListPlayer;
    }

    public List<Player> getInternalListPlayer() {
        return internalListPlayer;
    }

    public Player getPlayer(int position) {
        return internalListPlayer.get(position);
    }

    public boolean isPlayerChosen(int position) {
        return internalListPlayer.get(position).isPartOfATeam();
    }

    public int getPositionPlayer(int id) {
        for (int i = 0; i < internalListPlayer.size(); i++) {
            if (id == internalListPlayer.get(i).getId()) {
                return i;
            }
        }
        return -1;
    }

    public void updateStatusPlayer(int position) {
        try {
            Player player = internalListPlayer.get(position);
            player.setPartOfATeam(!player.isPartOfATeam());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void resetStatusPlayer() {
        if (internalListPlayer != null) {
            for (int i = 0; i < internalListPlayer.size(); i++) {
                internalListPlayer.get(i).setPartOfATeam(false);
            }
        }
    }

    public boolean nameValidation(String name){
        return !name.isEmpty() && name.length() >= 2;
    }
}
