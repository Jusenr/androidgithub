package com.jusenr.androidgithub.home.di.module;

import com.jusenr.androidgithub.home.contract.RepoListContract;
import com.jusenr.androidgithub.home.model.interactor.RepoListInteractorImpl;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class RepoListModule {
    private RepoListContract.View view;

    public RepoListModule(RepoListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    RepoListContract.View provideUserView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    RepoListContract.Interactor provideUserModel(RepoListInteractorImpl interactor) {
        return interactor;
    }
}