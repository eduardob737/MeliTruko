package com.example.melitruko.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.melitruko.data.model.Player;
import com.example.melitruko.data.model.Team;
import com.example.melitruko.data.repositories.PlayersRepository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private Player player = null;
    private Team blueTeam = new Team();
    private Team whiteTeam = new Team();

    private final MutableLiveData<Player> mPlayer = new MutableLiveData<>();
    public LiveData<Player> playerLiveData = mPlayer;

    private final PlayersRepository playersRepository = new PlayersRepository();

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

    public List<Player> getPlayers(){
        return playersRepository.getPlayers();
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
}
