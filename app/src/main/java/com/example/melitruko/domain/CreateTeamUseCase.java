package com.example.melitruko.domain;

import com.example.melitruko.data.repositories.TeamsRepository;
import com.example.melitruko.domain.model.Team;

public class CreateTeamUseCase {

    private final TeamsRepository teamsRepository;

    public CreateTeamUseCase(TeamsRepository teamsRepository) {
        this.teamsRepository = teamsRepository;
    }

    public void invoke(Team team){
        teamsRepository.createTeam(team);
    }

}
