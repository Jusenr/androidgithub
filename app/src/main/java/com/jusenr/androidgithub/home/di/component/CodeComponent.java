package com.jusenr.androidgithub.home.di.component;

import com.jusenr.androidgithub.base.di.component.AppComponent;
import com.jusenr.androidgithub.home.di.module.CodeModule;
import com.jusenr.androidgithub.home.ui.activity.CodeActivity;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = CodeModule.class, dependencies = AppComponent.class)
public interface CodeComponent {
    void inject(CodeActivity arg);
}