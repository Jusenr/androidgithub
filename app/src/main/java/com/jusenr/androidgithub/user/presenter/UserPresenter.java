package com.jusenr.androidgithub.user.presenter;

import com.jusenr.androidgithub.retrofit.subscriber.Subscriber0;
import com.jusenr.androidgithub.user.contract.UserContract;
import com.jusenr.androidgithub.user.model.model.OrganizationsModel;
import com.jusenr.androidgithub.user.model.model.UserModel;
import com.jusenr.androidlibrary.base.BasePresenter;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.functions.Action0;

@ActivityScope
public class UserPresenter extends BasePresenter<UserContract.View, UserContract.Interactor> {

    @Inject
    public UserPresenter(UserContract.View view, UserContract.Interactor interactor) {
        super(view, interactor);
    }

    public void onUserInfo(final String username) {
        subscriptions.add(mInteractor.getSingleUser(username)
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
                .subscribe(new Subscriber0<UserModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(String msg, UserModel userModel) {
                        mView.getUserinfoResult(userModel);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        mView.getUserinfoFailed(code, msg);
                    }
                }));
    }

    public void onOrganizations(final String username) {
        subscriptions.add(mInteractor.organizations(username)
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
                .subscribe(new Subscriber0<ArrayList<OrganizationsModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(String msg, ArrayList<OrganizationsModel> modelArrayList) {
                        mView.organizationsResult(modelArrayList);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        mView.organizationsFailed(code, msg);
                    }
                }));
    }
}