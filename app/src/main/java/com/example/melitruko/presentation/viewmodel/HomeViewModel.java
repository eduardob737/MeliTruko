package com.example.melitruko.presentation.viewmodel;

import android.os.Parcelable;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.melitruko.R;
import com.example.melitruko.data.repositories.PlayerRepository;
import com.example.melitruko.data.repositories.TeamsRepository;
import com.example.melitruko.domain.CreateTeamUseCase;
import com.example.melitruko.domain.business.usecases.GetInternalPlayersListUseCase;
import com.example.melitruko.domain.business.PlayerBusiness;
import com.example.melitruko.domain.business.usecases.GetPlayersListUseCase;
import com.example.melitruko.domain.business.usecases.InsertPlayerUseCase;
import com.example.melitruko.domain.model.Match;
import com.example.melitruko.domain.model.Player;
import com.example.melitruko.domain.model.Team;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private static final String TAG = HomeViewModel.class.getSimpleName();

    private Team blueTeam = new Team();
    private Team whiteTeam = new Team();

    private Match match;
    private Team.ColorTeam colorTeam = null;
    private int position = -1;

    // Dependencias
    private final PlayerBusiness playerBusiness;
    private final TeamsRepository teamsRepository = new TeamsRepository();

    // UseCases
    private final GetPlayersListUseCase getPlayersListUseCase;
    private final InsertPlayerUseCase insertPlayerUseCase;
    private final GetInternalPlayersListUseCase getInternalPlayersListUseCase;
    private final CreateTeamUseCase createTeamUseCase = new CreateTeamUseCase(teamsRepository);

    // Mutables e LiveDatas
    private final MutableLiveData<Player> mPlayer = new MutableLiveData<>();
    public LiveData<Player> playerLiveData = mPlayer;

    private final MutableLiveData<Integer> mBlueTeamScore = new MutableLiveData<>();
    public LiveData<Integer> blueTeamScoreLiveData = mBlueTeamScore;

    private final MutableLiveData<Integer> mWhiteTeamScore = new MutableLiveData<>();
    public LiveData<Integer> whiteTeamScoreLiveData = mWhiteTeamScore;

    private final MutableLiveData<Integer> mMatchValue = new MutableLiveData<>();
    public LiveData<Integer> matchValueLiveData = mMatchValue;

    private final MutableLiveData<String> _mutableInsert = new MutableLiveData<>();
    public LiveData<String> insertLiveData = _mutableInsert;

    private final MutableLiveData<Boolean> _mutableStatusSuccess = new MutableLiveData<>();
    public LiveData<Boolean> statusSuccessLiveData = _mutableStatusSuccess;

    public LiveData<List<Player>> playersListLiveData;

    public HomeViewModel(PlayerBusiness playerBusiness, PlayerRepository playerRepository) {
        this.playerBusiness = playerBusiness;
        getPlayersListUseCase = new GetPlayersListUseCase(playerRepository);
        insertPlayerUseCase = new InsertPlayerUseCase(playerRepository);
        getInternalPlayersListUseCase = new GetInternalPlayersListUseCase(playerRepository);
        playersListLiveData = getPlayersListUseCase.invoke();
        blueTeam.setColor(Team.ColorTeam.BLUE);
        whiteTeam.setColor(Team.ColorTeam.WHITE);
    }

    //Business/Use Cases ABAIXO
    public void insertPlayer(String name, String photoPath){
        try {
            insertPlayerUseCase.invoke(name, photoPath);
             _mutableInsert.postValue("Jogador criado com sucesso");
             _mutableStatusSuccess.postValue(true);
        } catch (Exception exception){
            _mutableInsert.postValue("Erro ao cadastrar, tente novamente");
            _mutableStatusSuccess.postValue(false);
            Log.e(TAG, exception.toString());
        }
    }

    public void updateStatusPlayer(int id){
        int position = playerBusiness.getPositionPlayer(id);
        playerBusiness.updateStatusPlayer(position);
    }

    public boolean isPlayerChosen(int position) {
        return playerBusiness.isPlayerChosen(position);
    }

    public void setInternalPlayersList(List<Player> list) {
        playerBusiness.setInternalListPlayer(list);
    }

    public List<Player> getInternalPlayersList() {
        return playerBusiness.getInternalListPlayer();
    }

    public List<Player> getList(){
        if (playerBusiness.getInternalListPlayer() == null)
            setInternalPlayersList(getInternalPlayersListUseCase.invoke());
        return getInternalPlayersList();
    }

    public boolean isNameValid(String name){
        return playerBusiness.nameValidation(name);
    }

    public Player getPlayerOfList(int position){
        return playerBusiness.getPlayer(position);
    }

    public void setPositionList(int position) {
        playerBusiness.setPositionList(position);
    }

    public int getPositionList(){
        return playerBusiness.getPositionList();
    }
    //Business/Use Cases ACIMA

    // Team
    public void createNewPlayer(Player player){
        if (mPlayer.getValue() != player){
            player.setPartOfATeam(true);
            atributePlayer(player);
            notifyObservers(player);
        }
    }

    public Team getBlueTeam() {
        return blueTeam;
    }

    public Team getWhiteTeam() {
        return whiteTeam;
    }

    public boolean isPositionFilled(Team team){ return team.getPlayers().get(position) != null;}

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

    // Match
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

    // Notify Observers
    private void notifyObservers(Player player){
        mPlayer.postValue(player);
    }

    // Clear Data
    public void clearViewModel(){
        blueTeam = new Team();
        whiteTeam = new Team();
        colorTeam = null;
        position = -1;
        playerBusiness.resetStatusPlayer();
    }
}
