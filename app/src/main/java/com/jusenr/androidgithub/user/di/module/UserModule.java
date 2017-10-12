package com.jusenr.androidgithub.user.di.module;

import com.jusenr.androidgithub.user.contract.UserContract;
import com.jusenr.androidgithub.user.model.interactor.UserInteractorImpl;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {
    private UserContract.View view;

    public UserModule(UserContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    UserContract.View provideUserView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    UserContract.Interactor provideUserModel(UserInteractorImpl interactor) {
        return interactor;
    }
}