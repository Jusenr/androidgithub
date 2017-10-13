package com.jusenr.androidgithub.home.presenter;

import com.jusenr.androidgithub.home.contract.RepoDetailContract;
import com.jusenr.androidgithub.home.model.model.RepoDetail;
import com.jusenr.androidlibrary.base.BasePresenter;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import javax.inject.Inject;

import rx.Subscriber;
import rx.functions.Action0;

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
                .subscribe(new Subscriber<RepoDetail>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.loadRepoDetailsFailed(-1, e.getMessage());
                    }

                    @Override
                    public void onNext(RepoDetail repoDetail) {
                        mView.loadRepoDetailsResult(repoDetail);
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
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.starRepoResult(false);
                    }

                    @Override
                    public void onNext(Boolean b) {
                        mView.starRepoResult(b);
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
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.unstarRepoResult(false);
                    }

                    @Override
                    public void onNext(Boolean b) {
                        mView.unstarRepoResult(b);
                    }
                })
        );
    }
}