package com.jusenr.androidgithub.home.presenter;


import com.jusenr.androidgithub.home.contract.SearchContract;
import com.jusenr.androidgithub.home.model.model.Repo;
import com.jusenr.androidgithub.retrofit.subscriber.Subscriber0;
import com.jusenr.androidlibrary.base.BasePresenter;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import java.util.ArrayList;

import javax.inject.Inject;

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
                .subscribe(new Subscriber0<ArrayList<Repo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(String msg, ArrayList<Repo> repos) {
                        mView.searchRepoResult(repos);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        mView.searchRepoFailed(code, msg);
                    }
                }));
    }
}