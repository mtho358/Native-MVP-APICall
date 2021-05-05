package com.coolcats.apicall.presenter;

import com.coolcats.apicall.model.GitResponse;
import com.coolcats.apicall.network.GitNetwork;

import java.util.List;

public class GitPresenter implements Contract.Presenter{

    private Contract.View view;

    public GitPresenter(Contract.View view) {
        this.view = view;
    }

    @Override
    public void getGitRepositories(String userName) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                List<GitResponse> response = GitNetwork.getRepos(userName);
                if(response.isEmpty())
                    view.showError("No Results. :-{");
                else
                    view.showRepositories(GitNetwork.getRepos(userName));
            }
        }.start();
    }
}
