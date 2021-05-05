package com.coolcats.apicall.presenter;

import com.coolcats.apicall.model.GitResponse;

import java.util.List;

public interface Contract {

    interface Presenter{
        void getGitRepositories(String userName);
    }

    interface View{
        void showRepositories(List<GitResponse> repositories);
        void showError(String message);
    }
}
