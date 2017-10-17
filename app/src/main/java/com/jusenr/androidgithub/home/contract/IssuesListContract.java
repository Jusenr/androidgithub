package com.jusenr.androidgithub.home.contract;

import com.jusenr.androidgithub.home.model.model.IssuesModel;
import com.jusenr.androidlibrary.base.IInteractor;
import com.jusenr.androidlibrary.base.loading.LoadView;

import java.util.ArrayList;

import rx.Observable;

public interface IssuesListContract {

    interface View extends LoadView {

        void loadIssuesResult(ArrayList<IssuesModel> issues);

        void loadIssuesFailed(int code, String msg);
    }

    interface Interactor extends IInteractor {

        Observable<ArrayList<IssuesModel>> loadIssues(String owner, String repo);
    }
}