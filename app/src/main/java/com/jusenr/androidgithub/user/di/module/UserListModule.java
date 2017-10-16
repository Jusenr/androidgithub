package com.jusenr.androidgithub.user.di.module;

import com.jusenr.androidgithub.user.contract.UserListContract;
import com.jusenr.androidgithub.user.model.interactor.UserListInteractorImpl;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class UserListModule {
    private UserListContract.View view;

    public UserListModule(UserListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    UserListContract.View provideUserView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    UserListContract.Interactor provideUserModel(UserListInteractorImpl interactor) {
        return interactor;
    }
}