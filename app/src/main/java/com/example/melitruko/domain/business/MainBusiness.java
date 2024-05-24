package com.example.melitruko.domain.business;

import com.example.melitruko.domain.model.Player;

import java.util.List;

public class MainBusiness {

    public Player getPlayer(List<Player> list, int position) {
        return list.get(position);
    }

    public boolean isPlayerChosen(List<Player> list, int position) {
        return list.get(position).isPartOfATeam();
    }

    public int getPositionPlayer(List<Player> list, int id) {
        for (int i = 0; i < list.size(); i++) {
            if (id == list.get(i).getId()) {
                return i;
            }
        }
        return -1;
    }

    public boolean nameValidation(String name) {
        return !name.isEmpty() && name.length() >= 2;
    }
}
