package com.jusenr.androidgithub.home.contract;

import com.jusenr.androidgithub.home.model.model.Content;
import com.jusenr.androidlibrary.base.IInteractor;
import com.jusenr.androidlibrary.base.loading.LoadView;

import java.util.ArrayList;

import rx.Observable;

public interface RepoTreeContract {

    interface View extends LoadView {

        void repoContentsResult(ArrayList<Content> contents);

        void repoContentsFailed(int code, String msg);
    }

    interface Interactor extends IInteractor {

        Observable<ArrayList<Content>> repoContents(String owner, String repo, String path);
    }
}