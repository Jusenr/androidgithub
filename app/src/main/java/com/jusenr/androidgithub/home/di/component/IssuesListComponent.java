package com.jusenr.androidgithub.home.di.component;

import com.jusenr.androidgithub.base.di.component.AppComponent;
import com.jusenr.androidgithub.home.di.module.IssuesListModule;
import com.jusenr.androidgithub.home.ui.activity.IssuesListActivity;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = IssuesListModule.class, dependencies = AppComponent.class)
public interface IssuesListComponent {
    void inject(IssuesListActivity arg);
}