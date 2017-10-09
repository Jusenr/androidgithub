package com.jusenr.androidgithub.home.contract;

import com.jusenr.androidgithub.home.model.model.Repo;
import com.jusenr.androidlibrary.base.IInteractor;
import com.jusenr.androidlibrary.base.loading.LoadView;

import java.util.ArrayList;

import rx.Observable;

public interface SearchContract {

    interface View extends LoadView {

        void searchRepoResult(ArrayList<Repo> repos);

        void searchRepoFailed(int code, String msg);
    }

    interface Interactor extends IInteractor {

        Observable<ArrayList<Repo>> searchRepo(String key, String language);
    }
}