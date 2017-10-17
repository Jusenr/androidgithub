package com.jusenr.androidgithub.home.presenter;

import com.jusenr.androidgithub.home.contract.IssuesListContract;
import com.jusenr.androidgithub.home.model.model.IssuesModel;
import com.jusenr.androidgithub.retrofit.subscriber.Subscriber0;
import com.jusenr.androidlibrary.base.BasePresenter;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.functions.Action0;

@ActivityScope
public class IssuesListPresenter extends BasePresenter<IssuesListContract.View, IssuesListContract.Interactor> {

    @Inject
    public IssuesListPresenter(IssuesListContract.View view, IssuesListContract.Interactor interactor) {
        super(view, interactor);
    }

    public void onLoadIssues(String owner, String repo) {
        subscriptions.add(mInteractor.loadIssues(owner, repo)
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
                .subscribe(new Subscriber0<ArrayList<IssuesModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(String msg, ArrayList<IssuesModel> issues) {
                        mView.loadIssuesResult(issues);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        mView.loadIssuesFailed(code, msg);
                    }
                }));
    }
}