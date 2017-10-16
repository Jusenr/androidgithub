package com.jusenr.androidgithub.home.di.module;

import com.jusenr.androidgithub.home.contract.MostStarContract;
import com.jusenr.androidgithub.home.model.interactor.MostStarInteractorImpl;
import com.jusenr.androidlibrary.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MostStarModule {
    private MostStarContract.View view;

    public MostStarModule(MostStarContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    MostStarContract.View provideUserView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    MostStarContract.Interactor provideUserModel(MostStarInteractorImpl interactor) {
        return interactor;
    }
}