package com.jusenr.androidgithub.home.presenter;


import com.jusenr.androidgithub.home.contract.SearchContract;
import com.jusenr.androidgithub.home.model.model.Repo;
import com.jusenr.androidlibrary.base.BasePresenter;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Subscriber;
import rx.functions.Action0;

@ActivityScope
public class SearchPresenter extends BasePresenter<SearchContract.View, SearchContract.Interactor> {

    @Inject
    public SearchPresenter(SearchContract.View view, SearchContract.Interactor interactor) {
        super(view, interactor);
    }

    public void onSearchRepo(String key, String language) {
        subscriptions.add(mInteractor.searchRepo(key, language)
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
                .subscribe(new Subscriber<ArrayList<Repo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.searchRepoFailed(-1, e.getMessage());
                    }

                    @Override
                    public void onNext(ArrayList<Repo> repos) {
                        mView.searchRepoResult(repos);
                    }
                }));
    }
}