package com.example.melitruko.presentation.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.melitruko.data.repositories.TeamsRepository;
import com.example.melitruko.domain.CreateTeamUseCase;
import com.example.melitruko.domain.GetPlayersListUseCase;
import com.example.melitruko.domain.model.Player;
import com.example.melitruko.domain.model.Team;
import com.example.melitruko.data.repositories.PlayersRepository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private Player player = null;
    private Team blueTeam = new Team();
    private Team whiteTeam = new Team();

    private Team.ColorTeam colorTeam = null;
    private int position = -1;

    private final MutableLiveData<Player> mPlayer = new MutableLiveData<>();
    public LiveData<Player> playerLiveData = mPlayer;

    private final PlayersRepository playersRepository = new PlayersRepository();
    private final GetPlayersListUseCase getPlayersListUseCase = new GetPlayersListUseCase(playersRepository);

    private final TeamsRepository teamsRepository = new TeamsRepository();
    private final CreateTeamUseCase createTeamUseCase = new CreateTeamUseCase(teamsRepository);

    public HomeViewModel(@NonNull Application application) {
        super(application);
        blueTeam.setColor(Team.ColorTeam.BLUE);
        whiteTeam.setColor(Team.ColorTeam.WHITE);
    }

    public void createNewPlayer(Player player){
        if (mPlayer.getValue() != player){
            setPlayer(player);
            atributePlayer(player);
            notifyObservers(player);
        }
    }

    public void setTeamAtributes(Team.ColorTeam colorTeam, int position){
        this.colorTeam = colorTeam;
        this.position = position;
    }

    public void atributePlayer(Player player) {
        if (colorTeam.equals(Team.ColorTeam.BLUE)){
            getBlueTeam().getPlayers().set(position, player);
        }
        if (colorTeam.equals(Team.ColorTeam.WHITE)){
            getWhiteTeam().getPlayers().set(position, player);
        }
    }

    public List<Player> getPlayers(){
        return getPlayersListUseCase.invoke();
    }

    public void createTeam(Team team){
        createTeamUseCase.invoke(team);
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

    public void clearViewModel(){
        player = null;
        blueTeam = new Team();
        whiteTeam = new Team();
        colorTeam = null;
        position = -1;
    }
}
