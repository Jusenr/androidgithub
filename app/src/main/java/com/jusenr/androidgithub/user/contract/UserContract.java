package com.jusenr.androidgithub.user.contract;

import com.jusenr.androidgithub.user.model.model.OrganizationsModel;
import com.jusenr.androidgithub.user.model.model.UserModel;
import com.jusenr.androidlibrary.base.IInteractor;
import com.jusenr.androidlibrary.base.loading.LoadView;

import java.util.ArrayList;

import rx.Observable;

public interface UserContract {

    interface View extends LoadView {

        void getUserinfoResult(UserModel bean);

        void getUserinfoFailed(int code, String msg);

        void organizationsResult(ArrayList<OrganizationsModel> modelArrayList);

        void organizationsFailed(int code, String msg);
    }

    interface Interactor extends IInteractor {

        Observable<UserModel> getSingleUser(String username);

        Observable<ArrayList<OrganizationsModel>> organizations(String username);
    }
}