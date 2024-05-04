package com.example.melitruko.data.repositories;

import android.net.Uri;
import android.widget.Toast;

import com.example.melitruko.domain.model.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PlayersRepository {

    // Lista interna que futuramente receberá a lista do banco de dados
    List<Player> playerList = new ArrayList<>();

    public PlayersRepository() {
        createPlayersList();
    }

    public void createPlayersList() {
        File imagem = new File("/data/data/com.example.melitruko/files/foto_isabela.jpeg");

        Uri foto = Uri.fromFile(imagem);

        int id = 0;

        Player player1 = new Player();
        player1.setId(id++);
        player1.setName("Eduardo");
        player1.setPhoto(foto);
        playerList.add(player1);

        Player player2 = new Player();
        player2.setId(id++);
        player2.setName("Wilson");
        player2.setPhoto(foto);
        playerList.add(player2);

        Player player3 = new Player();
        player3.setId(id++);
        player3.setName("Bruno");
        player3.setPhoto(foto);
        playerList.add(player3);

        Player player4 = new Player();
        player4.setId(id++);
        player4.setName("Leo");
        player4.setPhoto(foto);
        playerList.add(player4);

        Player player5 = new Player();
        player5.setId(id++);
        player5.setName("Denis");
        player5.setPhoto(foto);
        playerList.add(player5);

        Player player6 = new Player();
        player6.setId(id++);
        player6.setName("Lucas");
        player6.setPhoto(foto);
        playerList.add(player6);
    }

    public List<Player> getPlayersList() {
        return playerList;
    }

    public void updateStatusPlayer(int id) {
        int position = getPlayerPosition(id);
        try {
            playerList.get(position).setPartOfATeam(!playerList.get(position).isPartOfATeam());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getPlayerPosition(int id) {
        for (int i = 0; i < getPlayersList().size(); i++) {
            if (id == playerList.get(i).getId()) {
                return i;
            }
        }
        return -1;
    }

    public void resetStatusPlayers(){
        for (int i=0; i < playerList.size(); i++) {
            playerList.get(i).setPartOfATeam(false);
        }
    }
}
