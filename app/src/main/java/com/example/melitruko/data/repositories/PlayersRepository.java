package com.example.melitruko.data.repositories;

import android.net.Uri;

import com.example.melitruko.domain.model.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PlayersRepository {

    public List<Player> getPlayersList() {
        File imagem = new File("/data/data/com.example.melitruko/files/foto_isabela.jpeg");

        Uri foto = Uri.fromFile(imagem);

        List<Player> playerList = new ArrayList<>();

        Player player1 = new Player();
        player1.setName("Eduardo");
        player1.setPhoto(foto);
        playerList.add(player1);

        Player player2 = new Player();
        player2.setName("Wilson");
        player2.setPhoto(foto);
        playerList.add(player2);

        Player player3 = new Player();
        player3.setName("Bruno");
        player3.setPhoto(foto);
        playerList.add(player3);

        Player player4 = new Player();
        player4.setName("Leo");
        player4.setPhoto(foto);
        playerList.add(player4);

        Player player5 = new Player();
        player5.setName("Denis");
        player5.setPhoto(foto);
        playerList.add(player5);

        Player player6 = new Player();
        player6.setName("Lucas");
        player6.setPhoto(foto);
        playerList.add(player6);

        return playerList;
    }

}
