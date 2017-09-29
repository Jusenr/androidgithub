package com.jusenr.androidgithub.user.di.module;

import com.jusenr.androidgithub.user.contract.LoginContract;
import com.jusenr.androidgithub.user.model.interactor.LoginInteractorImpl;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {
    private LoginContract.View view;

    public LoginModule(LoginContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    LoginContract.View provideUserView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    LoginContract.Interactor provideUserModel(LoginInteractorImpl interactor) {
        return interactor;
    }
}