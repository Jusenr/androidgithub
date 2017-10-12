package com.jusenr.androidgithub.home.di.component;

import com.jusenr.androidgithub.base.di.component.AppComponent;
import com.jusenr.androidgithub.home.di.module.RepoListModule;
import com.jusenr.androidgithub.home.ui.activity.RepoListActivity;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = RepoListModule.class, dependencies = AppComponent.class)
public interface RepoListComponent {
    void inject(RepoListActivity arg);
}