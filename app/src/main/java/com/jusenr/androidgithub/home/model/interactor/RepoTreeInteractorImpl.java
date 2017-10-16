package com.jusenr.androidgithub.home.model.interactor;

import android.text.TextUtils;

import com.jusenr.androidgithub.home.contract.RepoTreeContract;
import com.jusenr.androidgithub.home.model.model.Content;
import com.jusenr.androidgithub.retrofit.RxRetrofitComposer;
import com.jusenr.androidgithub.retrofit.api.RepoApi;
import com.jusenr.androidgithub.utils.AccountHelper;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Observable;

@ActivityScope
public class RepoTreeInteractorImpl implements RepoTreeContract.Interactor {

    private RepoApi repoApi;

    @Inject
    public RepoTreeInteractorImpl(RepoApi repoApi) {
        this.repoApi = repoApi;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public Observable<ArrayList<Content>> repoContents(String owner, String repo, String path) {
        Observable<ArrayList<Content>> observable = null;
        if (TextUtils.isEmpty(path)) {
            observable = repoApi.contents(AccountHelper.getCurrentUid(), owner, repo);
        } else {
            observable = repoApi.contentsWithPath(AccountHelper.getCurrentUid(), owner, repo, path);
        }

        return observable.compose(RxRetrofitComposer.<ArrayList<Content>>applySchedulers());
    }
}