package com.jusenr.androidgithub.home.model.interactor;

import com.jusenr.androidgithub.home.contract.RepoListContract;
import com.jusenr.androidgithub.home.model.model.Repo;
import com.jusenr.androidgithub.retrofit.RxRetrofitComposer;
import com.jusenr.androidgithub.retrofit.api.RepoApi;
import com.jusenr.androidgithub.utils.AccountHelper;
import com.jusenr.androidgithub.utils.Constants;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Observable;

@ActivityScope
public class RepoListInteractorImpl implements RepoListContract.Interactor {

    private RepoApi repoApi;

    @Inject
    public RepoListInteractorImpl(RepoApi repoApi) {
        this.repoApi = repoApi;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public Observable<ArrayList<Repo>> loadRepos(String username, boolean isSelf, @RepoApi.RepoType int type) {
        Observable<ArrayList<Repo>> observable = null;
        switch (type) {
            case RepoApi.OWNER_REPOS:
                if (isSelf) {
                    observable = repoApi.getMyRepos(AccountHelper.getCurrentUid(), Constants.Value.UPDATED, Constants.Value.ALL);
                } else {
                    observable = repoApi.getUserRepos(AccountHelper.getCurrentUid(), username, Constants.Value.UPDATED);
                }
                break;

            case RepoApi.STARRED_REPOS:
                if (isSelf) {
                    observable = repoApi.getMyStarredRepos(AccountHelper.getCurrentUid(), Constants.Value.UPDATED);
                } else {
                    observable = repoApi.getUserStarredRepos(AccountHelper.getCurrentUid(), username, Constants.Value.UPDATED);
                }
                break;

            case RepoApi.ORG_REPOS:
                // TODO, not support now.
                break;

            default:
                break;
        }
        if (observable == null) return null;

        return observable.compose(RxRetrofitComposer.<ArrayList<Repo>>applySchedulers());
    }
}