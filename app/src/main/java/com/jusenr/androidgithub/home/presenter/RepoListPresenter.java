package com.jusenr.androidgithub.home.presenter;

import com.jusenr.androidgithub.home.contract.RepoListContract;
import com.jusenr.androidgithub.home.model.model.Repo;
import com.jusenr.androidgithub.retrofit.api.RepoApi;
import com.jusenr.androidgithub.retrofit.subscriber.Subscriber0;
import com.jusenr.androidlibrary.base.BasePresenter;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.functions.Action0;

@ActivityScope
public class RepoListPresenter extends BasePresenter<RepoListContract.View, RepoListContract.Interactor> {

    @Inject
    public RepoListPresenter(RepoListContract.View view, RepoListContract.Interactor interactor) {
        super(view, interactor);
    }

    public void onLoadRepos(String username, boolean isSelf, @RepoApi.RepoType int type) {
        subscriptions.add(mInteractor.loadRepos(username, isSelf, type)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.showLoading();
                    }
                })
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        mView.dismissLoading();
                    }
                })
                .subscribe(new Subscriber0<ArrayList<Repo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(String msg, ArrayList<Repo> repos) {
                        mView.loadReposResult(repos);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        mView.loadReposFailed(code, msg);
                    }
                }));
    }

}