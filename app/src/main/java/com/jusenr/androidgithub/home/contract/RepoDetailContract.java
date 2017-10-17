package com.jusenr.androidgithub.home.contract;

import com.jusenr.androidgithub.home.model.model.RepoDetail;
import com.jusenr.androidlibrary.base.IInteractor;
import com.jusenr.androidlibrary.base.loading.LoadView;

import rx.Observable;

public interface RepoDetailContract {

    interface View extends LoadView {

        void loadRepoDetailsResult(RepoDetail repoDetail);

        void loadRepoDetailsFailed(int code, String msg);

        void starRepoResult(boolean b);

        void unstarRepoResult(boolean b);
    }

    interface Interactor extends IInteractor {

        Observable<RepoDetail> getRepoDetail(String owner, String repo);

        Observable<Boolean> starRepo(String owner, String repo);

        Observable<Boolean> unstarRepo(String owner, String repo);
    }
}