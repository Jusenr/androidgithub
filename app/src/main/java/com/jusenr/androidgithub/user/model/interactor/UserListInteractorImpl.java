package com.jusenr.androidgithub.user.model.interactor;

import com.jusenr.androidgithub.retrofit.RxRetrofitComposer;
import com.jusenr.androidgithub.retrofit.api.RepoApi;
import com.jusenr.androidgithub.user.contract.UserListContract;
import com.jusenr.androidgithub.user.model.model.UserModel;
import com.jusenr.androidgithub.utils.AccountHelper;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Observable;

@ActivityScope
public class UserListInteractorImpl implements UserListContract.Interactor {

    private RepoApi repoApi;

    @Inject
    public UserListInteractorImpl(RepoApi repoApi) {
        this.repoApi = repoApi;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public Observable<ArrayList<UserModel>> loadUsers(String username, boolean isSelf, @RepoApi.UserType int type) {
        Observable<ArrayList<UserModel>> observable = null;
        switch (type) {
            case RepoApi.FOLLOWER:
                if (isSelf) {
                    observable = repoApi.getMyFollowers(AccountHelper.getCurrentUid());
                } else {
                    observable = repoApi.getUserFollowers(AccountHelper.getCurrentUid(), username);
                }
                break;

            case RepoApi.FOLLOWING:
                if (isSelf) {
                    observable = repoApi.getMyFollowing(AccountHelper.getCurrentUid());
                } else {
                    observable = repoApi.getUserFollowing(AccountHelper.getCurrentUid(), username);
                }
                break;

            default:
                break;
        }
        if (observable == null) return null;

        return observable.compose(RxRetrofitComposer.<ArrayList<UserModel>>applySchedulers());
    }
}