package com.jusenr.androidgithub.home.di.module;

import com.jusenr.androidgithub.home.contract.CodeContract;
import com.jusenr.androidgithub.home.model.interactor.CodeInteractorImpl;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class CodeModule {
    private CodeContract.View view;

    public CodeModule(CodeContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    CodeContract.View provideUserView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    CodeContract.Interactor provideUserModel(CodeInteractorImpl interactor) {
        return interactor;
    }
}