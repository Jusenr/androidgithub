package com.jusenr.androidgithub.home.di.component;


import com.jusenr.androidgithub.base.di.component.AppComponent;
import com.jusenr.androidgithub.home.di.module.SearchModule;
import com.jusenr.androidgithub.home.ui.activity.SearchActivity;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = SearchModule.class, dependencies = AppComponent.class)
public interface SearchComponent {
    void inject(SearchActivity arg);
}