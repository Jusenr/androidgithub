package com.jusenr.androidgithub.home.model.interactor;

import com.jusenr.androidgithub.home.contract.CodeContract;
import com.jusenr.androidgithub.home.model.model.Content;
import com.jusenr.androidgithub.retrofit.RxRetrofitComposer;
import com.jusenr.androidgithub.retrofit.api.RepoApi;
import com.jusenr.androidgithub.utils.AccountHelper;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import javax.inject.Inject;

import rx.Observable;

@ActivityScope
public class CodeInteractorImpl implements CodeContract.Interactor {

    private RepoApi repoApi;

    @Inject
    public CodeInteractorImpl(RepoApi repoApi) {
        this.repoApi = repoApi;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public Observable<Content> contentDetail(String owner, String repo, String path) {
        return repoApi.contentDetail(AccountHelper.getCurrentUid(), owner, repo, path)
                .compose(RxRetrofitComposer.<Content>applySchedulers());
    }
}