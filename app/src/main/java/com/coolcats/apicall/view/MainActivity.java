package com.coolcats.apicall.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.coolcats.apicall.R;
import com.coolcats.apicall.databinding.ActivityMainBinding;
import com.coolcats.apicall.model.GitResponse;
import com.coolcats.apicall.presenter.Contract;
import com.coolcats.apicall.presenter.GitPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Contract.View, GitAdapter.GitDelegate {

    private Contract.Presenter presenter;
    private GitAdapter gitAdapter = new GitAdapter(new ArrayList<>(), this);

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new GitPresenter(this);
        presenter.getGitRepositories("mtho358");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recyclerview.setLayoutManager(linearLayoutManager);
        binding.recyclerview.setAdapter(gitAdapter);
    }

    @Override
    public void showRepositories(List<GitResponse> repositories) {
        runOnUiThread(()->{
            Glide.with(binding.getRoot()).applyDefaultRequestOptions(RequestOptions.centerCropTransform()).load(
                    "https://avatars.githubusercontent.com/u/47256641?v=4"
            ).into(binding.avatarImageview);
            gitAdapter.setResponseList(repositories);
        });
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void openRepo(GitResponse repo) {

    }
}