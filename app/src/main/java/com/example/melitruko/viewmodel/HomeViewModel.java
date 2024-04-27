package com.example.melitruko.viewmodel;

import android.app.Application;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.melitruko.model.Player;
import com.example.melitruko.model.Team;

import java.io.File;
import java.io.PipedReader;
import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private Player player = null;
    private Team blueTeam = new Team();
    private Team whiteTeam = new Team();

    private final MutableLiveData<Player> mPlayer = new MutableLiveData<>();
    public LiveData<Player> playerLiveData = mPlayer;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        blueTeam.setColor(Team.ColorTeam.BLUE);
        whiteTeam.setColor(Team.ColorTeam.WHITE);
    }

    public void createNewPlayer(Player player){
        if (mPlayer.getValue() != player){
            setPlayer(player);
            notifyObservers(player);
        }
    }

    private void notifyObservers(Player player){
        mPlayer.postValue(player);
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

        return playerList;
    }
}
