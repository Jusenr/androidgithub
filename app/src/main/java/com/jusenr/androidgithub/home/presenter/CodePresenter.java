package com.jusenr.androidgithub.home.presenter;

import com.jusenr.androidgithub.home.contract.CodeContract;
import com.jusenr.androidgithub.home.model.model.Content;
import com.jusenr.androidlibrary.base.BasePresenter;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import javax.inject.Inject;

import rx.Subscriber;
import rx.functions.Action0;

@ActivityScope
public class CodePresenter extends BasePresenter<CodeContract.View, CodeContract.Interactor> {

    @Inject
    public CodePresenter(CodeContract.View view, CodeContract.Interactor interactor) {
        super(view, interactor);
    }

    public void onContentDetail(String owner, String repo, String path) {
        subscriptions.add(mInteractor.contentDetail(owner, repo, path)
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
                .subscribe(new Subscriber<Content>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.contentDetailFailed(-1, e.getMessage());
                    }

                    @Override
                    public void onNext(Content content) {
                        mView.contentDetailResult(content);
                    }
                }));
    }
}