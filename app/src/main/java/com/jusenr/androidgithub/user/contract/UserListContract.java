package com.jusenr.androidgithub.user.contract;

import com.jusenr.androidgithub.retrofit.api.RepoApi;
import com.jusenr.androidgithub.user.model.model.UserModel;
import com.jusenr.androidlibrary.base.IInteractor;
import com.jusenr.androidlibrary.base.loading.LoadView;

import java.util.ArrayList;

import rx.Observable;

public interface UserListContract {

    interface View extends LoadView {

        void loadUsersResult(ArrayList<UserModel> repos);

        void loadUsersFailed(int code, String msg);
    }

    interface Interactor extends IInteractor {

        Observable<ArrayList<UserModel>> loadUsers(String username, boolean isSelf, @RepoApi.RepoType int type);
    }
}