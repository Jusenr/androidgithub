package com.jusenr.androidgithub.home.presenter;

import com.jusenr.androidgithub.home.contract.MostStarContract;
import com.jusenr.androidgithub.home.model.model.Repo;
import com.jusenr.androidgithub.retrofit.api.RepoApi;
import com.jusenr.androidgithub.retrofit.subscriber.Subscriber0;
import com.jusenr.androidlibrary.base.BasePresenter;
import com.jusenr.androidlibrary.di.scope.FragmentScope;

import java.util.ArrayList;

import javax.inject.Inject;

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
                .subscribe(new Subscriber0<ArrayList<Repo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(String msg, ArrayList<Repo> repos) {
                        mView.top30StarsRepoResult(repos);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        mView.top30StarsRepoFailed(code, msg);
                    }
                }));
    }
}