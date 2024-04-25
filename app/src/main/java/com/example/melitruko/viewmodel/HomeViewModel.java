package com.example.melitruko.viewmodel;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.melitruko.model.Player;
import com.example.melitruko.model.Team;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private Player player = null;
    private Team blueTeam = new Team();
    private Team whiteTeam = new Team();

    public HomeViewModel(@NonNull Application application) {
        super(application);
        blueTeam.setColor(Team.ColorTeam.BLUE);
        whiteTeam.setColor(Team.ColorTeam.WHITE);
    }

    public void setNewPlayer(String name, Uri photo){
        player = new Player();
        player.setName(name);
        player.setPhoto(photo);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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

    public List<Player> getList() {
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

        Player player7 = new Player();
        player7.setName("Bruno");
        player7.setPhoto(foto);
        playerList.add(player7);

        Player player8 = new Player();
        player8.setName("Leo");
        player8.setPhoto(foto);
        playerList.add(player8);

        Player player9 = new Player();
        player9.setName("Denis");
        player9.setPhoto(foto);
        playerList.add(player9);

        Player player10 = new Player();
        player10.setName("Lucas");
        player10.setPhoto(foto);
        playerList.add(player10);

        /*Player player11 = new Player();
        player11.setName("Bruno");
        player11.setPhoto(foto);
        playerList.add(player11);

        Player player12 = new Player();
        player12.setName("Leo");
        player12.setPhoto(foto);
        playerList.add(player12);

        Player player13 = new Player();
        player13.setName("Denis");
        player13.setPhoto(foto);
        playerList.add(player13);

        Player player14 = new Player();
        player14.setName("Ultimo");
        player14.setPhoto(foto);
        playerList.add(player14);
*/
        return playerList;
    }
}
