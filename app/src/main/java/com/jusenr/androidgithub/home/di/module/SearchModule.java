package com.jusenr.androidgithub.home.di.module;


import com.jusenr.androidgithub.home.contract.SearchContract;
import com.jusenr.androidgithub.home.model.interactor.SearchInteractorImpl;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchModule {
    private SearchContract.View view;

    public SearchModule(SearchContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    SearchContract.View provideUserView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    SearchContract.Interactor provideUserModel(SearchInteractorImpl interactor) {
        return interactor;
    }
}