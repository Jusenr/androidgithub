package com.jusenr.androidgithub.home.contract;

import com.jusenr.androidgithub.home.model.model.Content;
import com.jusenr.androidlibrary.base.IInteractor;
import com.jusenr.androidlibrary.base.loading.LoadView;

import rx.Observable;

public interface CodeContract {

    interface View extends LoadView {

        void contentDetailResult(Content content);

        void contentDetailFailed(int code, String msg);
    }

    interface Interactor extends IInteractor {

        Observable<Content> contentDetail(String owner, String repo, String path);
    }
}