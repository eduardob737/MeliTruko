package com.example.melitruko.presentation.viewmodel;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcelable;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.melitruko.R;
import com.example.melitruko.data.repositories.PlayersRepository;
import com.example.melitruko.data.repositories.RepositoryTemp;
import com.example.melitruko.data.repositories.TeamsRepository;
import com.example.melitruko.domain.CreateTeamUseCase;
import com.example.melitruko.domain.GetPlayersListUseCase;
import com.example.melitruko.domain.ResetStatusPlayersUseCase;
import com.example.melitruko.domain.UpdateStatusPlayerUseCase;
import com.example.melitruko.domain.model.Match;
import com.example.melitruko.domain.model.Player;
import com.example.melitruko.domain.model.Team;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private Player player = null;
    private Team blueTeam = new Team();
    private Team whiteTeam = new Team();

    private Team.ColorTeam colorTeam = null;
    private int position = -1;

    private final MutableLiveData<Player> mPlayer = new MutableLiveData<>();
    public LiveData<Player> playerLiveData = mPlayer;

    private final GetPlayersListUseCase getPlayersListUseCase;
    private final UpdateStatusPlayerUseCase updateStatusPlayerUseCase;
    private final ResetStatusPlayersUseCase resetStatusPlayersUseCase;

    private final TeamsRepository teamsRepository = new TeamsRepository();
    private final CreateTeamUseCase createTeamUseCase = new CreateTeamUseCase(teamsRepository);

    private Match match;

    private final MutableLiveData<Integer> mBlueTeamScore = new MutableLiveData<>();
    public LiveData<Integer> blueTeamScoreLiveData = mBlueTeamScore;

    private final MutableLiveData<Integer> mWhiteTeamScore = new MutableLiveData<>();
    public LiveData<Integer> whiteTeamScoreLiveData = mWhiteTeamScore;

    private final MutableLiveData<Integer> mMatchValue = new MutableLiveData<>();
    public LiveData<Integer> matchValueLiveData = mMatchValue;

    private RepositoryTemp repositoryTemp;
    private static final String TAG = HomeViewModel.class.getSimpleName();
    private final MutableLiveData<String> _mutableInsert = new MutableLiveData<>();
    public LiveData<String> insertLiveData = _mutableInsert;

    private final MutableLiveData<Boolean> _mutableStatusSucess = new MutableLiveData<>();
    public LiveData<Boolean> statusSucessLiveData = _mutableStatusSucess;

    public LiveData<List<Player>> playersListLiveData;

    public HomeViewModel(RepositoryTemp repositoryTemp, PlayersRepository playersRepository) {
        this.repositoryTemp = repositoryTemp;
        playersListLiveData = repositoryTemp.getAllPlayers();
        getPlayersListUseCase = new GetPlayersListUseCase(playersRepository);
        updateStatusPlayerUseCase = new UpdateStatusPlayerUseCase(playersRepository);
        resetStatusPlayersUseCase = new ResetStatusPlayersUseCase(playersRepository);

        blueTeam.setColor(Team.ColorTeam.BLUE);
        whiteTeam.setColor(Team.ColorTeam.WHITE);
    }

    public boolean nameValidation(String name){
        return !name.isEmpty() && name.length() >= 2;
    }

    public void insertPlayer(String name, String photoPath){
        try {
            repositoryTemp.insert(name, photoPath);
             _mutableInsert.postValue("Jogador criado com sucesso");
             _mutableStatusSucess.postValue(true);
        } catch (Exception exception){
            _mutableInsert.postValue("Erro ao cadastrar, tente novamente");
            _mutableStatusSucess.postValue(false);
            Log.e(TAG, exception.toString());
        }
    }

    public void createNewPlayer(Player player){
        if (mPlayer.getValue() != player){
            setPlayer(player);
            player.setPartOfATeam(true);
            atributePlayer(player);
            notifyObservers(player);
        }
    }

    public void updateStatusPlayer(int id){
        updateStatusPlayerUseCase.invoke(id);
    }

    public void setTeamAtributes(Team.ColorTeam colorTeam, int position){
        this.colorTeam = colorTeam;
        this.position = position;
    }

    public void atributePlayer(Player player) {
        if (colorTeam.equals(Team.ColorTeam.BLUE)){
            if (isPositionFilled(getBlueTeam())) {
                updateStatusPlayer(getBlueTeam().getPlayers().get(position).getId());
            }
            getBlueTeam().getPlayers().set(position, player);
        }
        if (colorTeam.equals(Team.ColorTeam.WHITE)){
            if (isPositionFilled(getWhiteTeam())) {
                updateStatusPlayer(getWhiteTeam().getPlayers().get(position).getId());
            }
            getWhiteTeam().getPlayers().set(position, player);
        }
    }

    public boolean isPositionFilled(Team team){
        return team.getPlayers().get(position) != null;
    }

    public List<Player> getPlayers(){
        return getPlayersListUseCase.invoke();
    }

    public boolean isChosenPlayer(int position) {
        return getPlayers().get(position).isPartOfATeam();
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

    public Team getWhiteTeam() {
        return whiteTeam;
    }

    public void toAddPointsBlueTeam() {
        getMatch().getBlueTeam().setScore(getBlueTeamScore() + getMatchValue());
        mBlueTeamScore.postValue(getBlueTeamScore());
        if (getBlueTeamScore() == 12) {
            getMatch().setWinnerTeam(getMatch().getBlueTeam());
            getMatch().setLoserTeam(getMatch().getWhiteTeam());
        }
        getMatch().setValue(getMatch().getInitialValueMatch());
    }

    public void toAddPointsWhiteTeam() {
        getMatch().getWhiteTeam().setScore(getWhiteTeamScore() + getMatchValue());
        mWhiteTeamScore.postValue(getWhiteTeamScore());
        if (getWhiteTeamScore() == 12) {
            getMatch().setWinnerTeam(getMatch().getWhiteTeam());
            getMatch().setLoserTeam(getMatch().getBlueTeam());
        }
        getMatch().setValue(getMatch().getInitialValueMatch());
    }

    public void setupMatchValue() {
        if (getMatchValue() == getMatch().getInitialValueMatch()) {
            getMatch().setValue(getMatch().getAdditionalValueMatch());
        } else {
            getMatch().setValue(getMatchValue() + getMatch().getAdditionalValueMatch());
        }
        mMatchValue.postValue(getMatchValue());
    }

    public void resetMatchValue() {
        getMatch().setValue(getMatch().getInitialValueMatch());
        mMatchValue.postValue(getMatchValue());
    }

    public int getWhiteTeamScore() {
        return getMatch().getWhiteTeam().getScore();
    }

    public int getBlueTeamScore() {
        return getMatch().getBlueTeam().getScore();
    }

    public int getMatchValue() {
        return getMatch().getValue();
    }

    public void setNewMatch(Parcelable blueTeam, Parcelable whiteTeam) {
        match = new Match((Team) blueTeam, (Team) whiteTeam);
    }

    public int setMessageButtonToAddPoints() {
        switch (getMatchValue()) {
            case 3:
                return R.string.txt_btn_six;
            case 6:
                return R.string.txt_btn_nine;
            case 9:
                return R.string.txt_btn_twelve;
            default:
                return R.string.txt_btn_truco;
        }
    }

    public int maximumValueMatch(){
        return getMatch().getMaximumValue();
    }

    public boolean isTheMaximumValueOfTheMatch(){
        return getMatchValue() == maximumValueMatch();
    }

    public Match getMatch() {
        return match;
    }

    public int setLayoutMatch() {
        switch (getMatch().getQtdPlayersMatch()) {
            case 2:
                return R.layout.layout_two_players_match;
            case 4:
                return R.layout.layout_four_players_match;
            case 6:
                return R.layout.layout_six_players_match;
            default:
                return 0;
        }
    }

    public int getWinnerTeamScore(){
        return getMatch().getWinnerTeam().getScore();
    }

    public int getLoserTeamScore(){
        return getMatch().getLoserTeam().getScore();
    }

    public void clearViewModel(){
        player = null;
        blueTeam = new Team();
        whiteTeam = new Team();
        colorTeam = null;
        position = -1;
        resetStatusPlayersUseCase.invoke();
    }
}
