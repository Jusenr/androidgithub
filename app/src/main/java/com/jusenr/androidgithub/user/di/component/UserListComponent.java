package com.jusenr.androidgithub.user.di.component;

import com.jusenr.androidgithub.base.di.component.AppComponent;
import com.jusenr.androidgithub.user.di.module.UserListModule;
import com.jusenr.androidgithub.user.ui.activity.UserListActivity;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = UserListModule.class, dependencies = AppComponent.class)
public interface UserListComponent {
    void inject(UserListActivity arg);
}