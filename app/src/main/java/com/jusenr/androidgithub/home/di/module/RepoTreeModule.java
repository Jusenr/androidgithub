package com.jusenr.androidgithub.home.di.module;

import com.jusenr.androidgithub.home.contract.RepoTreeContract;
import com.jusenr.androidgithub.home.model.interactor.RepoTreeInteractorImpl;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class RepoTreeModule {
    private RepoTreeContract.View view;

    public RepoTreeModule(RepoTreeContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    RepoTreeContract.View provideUserView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    RepoTreeContract.Interactor provideUserModel(RepoTreeInteractorImpl interactor) {
        return interactor;
    }
}