package com.jusenr.androidgithub.user.di.component;


import com.jusenr.androidgithub.base.di.component.AppComponent;
import com.jusenr.androidgithub.user.di.module.UserModule;
import com.jusenr.androidgithub.user.ui.activity.UserActivity;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = UserModule.class, dependencies = AppComponent.class)
public interface UserComponent {
    void inject(UserActivity arg);
}