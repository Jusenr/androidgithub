package com.jusenr.androidgithub.home.presenter;

import com.jusenr.androidgithub.home.contract.RepoTreeContract;
import com.jusenr.androidgithub.home.model.model.Content;
import com.jusenr.androidlibrary.base.BasePresenter;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Subscriber;
import rx.functions.Action0;

@ActivityScope
public class RepoTreePresenter extends BasePresenter<RepoTreeContract.View, RepoTreeContract.Interactor> {

    @Inject
    public RepoTreePresenter(RepoTreeContract.View view, RepoTreeContract.Interactor interactor) {
        super(view, interactor);
    }

    public void onRepoContents(String owner, String repo, String path) {
        subscriptions.add(mInteractor.repoContents(owner, repo, path)
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
                .subscribe(new Subscriber<ArrayList<Content>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.repoContentsFailed(-1, e.getMessage());
                    }

                    @Override
                    public void onNext(ArrayList<Content> contents) {
                        mView.repoContentsResult(contents);
                    }
                }));
    }
}