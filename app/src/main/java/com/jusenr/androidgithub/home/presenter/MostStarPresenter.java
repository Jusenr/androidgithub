package com.jusenr.androidgithub.home.presenter;

import com.jusenr.androidgithub.home.contract.MostStarContract;
import com.jusenr.androidgithub.home.model.model.Repo;
import com.jusenr.androidgithub.retrofit.api.RepoApi;
import com.jusenr.androidlibrary.base.BasePresenter;
import com.jusenr.androidlibrary.di.scope.FragmentScope;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Subscriber;
import rx.functions.Action0;

@FragmentScope
public class MostStarPresenter extends BasePresenter<MostStarContract.View, MostStarContract.Interactor> {

    @Inject
    public MostStarPresenter(MostStarContract.View view, MostStarContract.Interactor interactor) {
        super(view, interactor);
    }

    public void onLoadMostStars(@RepoApi.MostStarsType int type) {
        subscriptions.add(mInteractor.top30StarsRepo(type)
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
                        mView.top30StarsRepoFailed(-1, e.getMessage());
                    }

                    @Override
                    public void onNext(ArrayList<Repo> repos) {
                        mView.top30StarsRepoResult(repos);
                    }
                }));
    }
}