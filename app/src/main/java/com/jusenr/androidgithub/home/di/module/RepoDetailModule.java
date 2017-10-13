package com.jusenr.androidgithub.home.di.module;

import com.jusenr.androidgithub.home.contract.RepoDetailContract;
import com.jusenr.androidgithub.home.model.interactor.RepoDetailInteractorImpl;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class RepoDetailModule {
    private RepoDetailContract.View view;

    public RepoDetailModule(RepoDetailContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    RepoDetailContract.View provideUserView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    RepoDetailContract.Interactor provideUserModel(RepoDetailInteractorImpl interactor) {
        return interactor;
    }
}