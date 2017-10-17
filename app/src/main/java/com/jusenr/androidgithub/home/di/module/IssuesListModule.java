package com.jusenr.androidgithub.home.di.module;

import com.jusenr.androidgithub.home.contract.IssuesListContract;
import com.jusenr.androidgithub.home.model.interactor.IssuesListInteractorImpl;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class IssuesListModule {
    private IssuesListContract.View view;

    public IssuesListModule(IssuesListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    IssuesListContract.View provideUserView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    IssuesListContract.Interactor provideUserModel(IssuesListInteractorImpl interactor) {
        return interactor;
    }
}