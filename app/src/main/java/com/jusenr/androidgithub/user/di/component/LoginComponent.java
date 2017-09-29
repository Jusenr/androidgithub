package com.jusenr.androidgithub.user.di.component;

import com.jusenr.androidgithub.base.di.component.AppComponent;
import com.jusenr.androidgithub.user.di.module.LoginModule;
import com.jusenr.androidgithub.user.ui.activity.LoginActivity;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity arg);
}