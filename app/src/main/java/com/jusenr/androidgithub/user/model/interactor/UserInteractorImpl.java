package com.jusenr.androidgithub.user.model.interactor;

import com.jusenr.androidgithub.retrofit.RxRetrofitComposer;
import com.jusenr.androidgithub.retrofit.api.RepoApi;
import com.jusenr.androidgithub.user.contract.UserContract;
import com.jusenr.androidgithub.user.model.model.OrganizationsModel;
import com.jusenr.androidgithub.user.model.model.UserModel;
import com.jusenr.androidgithub.utils.AccountHelper;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Observable;

@ActivityScope
public class UserInteractorImpl implements UserContract.Interactor {

    private RepoApi repoApi;

    @Inject
    public UserInteractorImpl(RepoApi repoApi) {
        this.repoApi = repoApi;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public Observable<UserModel> getSingleUser(String username) {
        return repoApi.getSingleUser(AccountHelper.getCurrentUid(), username)
                .compose(RxRetrofitComposer.<UserModel>applySchedulers());
    }

    @Override
    public Observable<ArrayList<OrganizationsModel>> organizations(String username) {
        return repoApi.organizations(AccountHelper.getCurrentUid(), username)
                .compose(RxRetrofitComposer.<ArrayList<OrganizationsModel>>applySchedulers());
    }
}