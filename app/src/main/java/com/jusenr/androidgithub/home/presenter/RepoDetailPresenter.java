package com.jusenr.androidgithub.home.presenter;

import com.jusenr.androidgithub.home.contract.RepoDetailContract;
import com.jusenr.androidgithub.home.model.model.RepoDetail;
import com.jusenr.androidgithub.retrofit.subscriber.Subscriber0;
import com.jusenr.androidlibrary.base.BasePresenter;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import javax.inject.Inject;

import rx.functions.Action0;

import static com.umeng.analytics.pro.bs.b;

@ActivityScope
public class RepoDetailPresenter extends BasePresenter<RepoDetailContract.View, RepoDetailContract.Interactor> {

    @Inject
    public RepoDetailPresenter(RepoDetailContract.View view, RepoDetailContract.Interactor interactor) {
        super(view, interactor);
    }

    public void onLoadRepoDetails(String owner, String repo) {
        subscriptions.add(mInteractor.getRepoDetail(owner, repo)
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
                .subscribe(new Subscriber0<RepoDetail>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(String msg, RepoDetail repoDetail) {
                        mView.loadRepoDetailsResult(repoDetail);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        mView.loadRepoDetailsFailed(code, msg);
                    }
                })
        );
    }

    public void onStarRepo(String owner, String repo) {
        subscriptions.add(mInteractor.starRepo(owner, repo)
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
                .subscribe(new Subscriber0<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(String msg, Boolean aBoolean) {
                        mView.starRepoResult(aBoolean);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        mView.starRepoResult(false);
                    }
                })
        );
    }

    public void onUnstarRepo(String owner, String repo) {
        subscriptions.add(mInteractor.unstarRepo(owner, repo)
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
                .subscribe(new Subscriber0<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(String msg, Boolean aBoolean) {
                        mView.unstarRepoResult(aBoolean);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        mView.unstarRepoResult(false);
                    }
                })
        );
    }
}