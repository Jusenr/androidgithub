package com.jusenr.androidgithub.home.di.component;

import com.jusenr.androidgithub.base.di.component.AppComponent;
import com.jusenr.androidgithub.home.di.module.RepoTreeModule;
import com.jusenr.androidgithub.home.ui.activity.RepoTreeActivity;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = RepoTreeModule.class, dependencies = AppComponent.class)
public interface RepoTreeComponent {
    void inject(RepoTreeActivity arg);
}