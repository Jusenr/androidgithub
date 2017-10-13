package com.jusenr.androidgithub.home.di.component;

import com.jusenr.androidgithub.base.di.component.AppComponent;
import com.jusenr.androidgithub.home.di.module.RepoDetailModule;
import com.jusenr.androidgithub.home.ui.activity.RepoDetailActivity;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = RepoDetailModule.class, dependencies = AppComponent.class)
public interface RepoDetailComponent {
    void inject(RepoDetailActivity arg);
}