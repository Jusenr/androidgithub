package com.jusenr.androidgithub.user.presenter;

import com.jusenr.androidgithub.retrofit.api.RepoApi;
import com.jusenr.androidgithub.retrofit.subscriber.Subscriber0;
import com.jusenr.androidgithub.user.contract.UserListContract;
import com.jusenr.androidgithub.user.model.model.UserModel;
import com.jusenr.androidlibrary.base.BasePresenter;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.functions.Action0;

@ActivityScope
public class UserListPresenter extends BasePresenter<UserListContract.View, UserListContract.Interactor> {

    @Inject
    public UserListPresenter(UserListContract.View view, UserListContract.Interactor interactor) {
        super(view, interactor);
    }

    public void onLoadUsers(String username, boolean isSelf, @RepoApi.UserType int type) {
        subscriptions.add(mInteractor.loadUsers(username, isSelf, type)
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
                .subscribe(new Subscriber0<ArrayList<UserModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(String msg, ArrayList<UserModel> userModels) {
                        mView.loadUsersResult(userModels);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        mView.loadUsersFailed(code, msg);
                    }
                }));
    }
}