package com.jusenr.androidgithub.home.contract;

import com.jusenr.androidgithub.home.model.model.Repo;
import com.jusenr.androidgithub.retrofit.api.RepoApi;
import com.jusenr.androidlibrary.base.IInteractor;
import com.jusenr.androidlibrary.base.loading.LoadView;

import java.util.ArrayList;

import rx.Observable;

public interface RepoListContract {

    interface View extends LoadView {

        void loadReposResult(ArrayList<Repo> repos);

        void loadReposFailed(int code, String msg);
    }

    interface Interactor extends IInteractor {

        Observable<ArrayList<Repo>> loadRepos(String username, boolean isSelf, @RepoApi.RepoType int type);
    }
}