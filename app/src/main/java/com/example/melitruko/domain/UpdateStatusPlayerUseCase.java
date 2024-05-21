package com.example.melitruko.domain;

import com.example.melitruko.data.repositories.RepositoryTemp;

public class UpdateStatusPlayerUseCase {
    private final RepositoryTemp repositoryTemp;

    public UpdateStatusPlayerUseCase(RepositoryTemp repositoryTemp) {
        this.repositoryTemp = repositoryTemp;
    }

    public void invoke(int id){
        repositoryTemp.updateStatusPlayer(id);
    }
}

