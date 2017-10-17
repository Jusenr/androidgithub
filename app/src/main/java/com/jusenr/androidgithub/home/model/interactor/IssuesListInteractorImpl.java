package com.jusenr.androidgithub.home.model.interactor;

import com.jusenr.androidgithub.home.contract.IssuesListContract;
import com.jusenr.androidgithub.home.model.model.IssuesModel;
import com.jusenr.androidgithub.retrofit.RxRetrofitComposer;
import com.jusenr.androidgithub.retrofit.api.RepoApi;
import com.jusenr.androidgithub.utils.AccountHelper;
import com.jusenr.androidgithub.utils.Constants;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Observable;

@ActivityScope
public class IssuesListInteractorImpl implements IssuesListContract.Interactor {

    private RepoApi repoApi;

    @Inject
    public IssuesListInteractorImpl(RepoApi repoApi) {
        this.repoApi = repoApi;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public Observable<ArrayList<IssuesModel>> loadIssues(String owner, String repo) {
        return repoApi.issues(AccountHelper.getCurrentUid(), owner, repo, Constants.Value.NEWEST)
                .compose(RxRetrofitComposer.<ArrayList<IssuesModel>>applySchedulers());
    }
}