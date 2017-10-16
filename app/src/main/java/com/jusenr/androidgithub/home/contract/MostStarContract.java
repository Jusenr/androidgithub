package com.jusenr.androidgithub.home.contract;

import com.jusenr.androidgithub.home.model.model.Repo;
import com.jusenr.androidgithub.retrofit.api.RepoApi;
import com.jusenr.androidlibrary.base.IInteractor;
import com.jusenr.androidlibrary.base.loading.LoadView;

import java.util.ArrayList;

import rx.Observable;

public interface MostStarContract {

    interface View extends LoadView {

        void top30StarsRepoResult(ArrayList<Repo> repos);

        void top30StarsRepoFailed(int code, String msg);
    }

    interface Interactor extends IInteractor {

        Observable<ArrayList<Repo>> top30StarsRepo(@RepoApi.MostStarsType int type);
    }
}