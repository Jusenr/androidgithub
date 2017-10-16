package com.jusenr.androidgithub.home.di.component;

import com.jusenr.androidgithub.base.di.component.AppComponent;
import com.jusenr.androidgithub.home.di.module.MostStarModule;
import com.jusenr.androidgithub.home.ui.fragment.MostStarFragment;
import com.jusenr.androidlibrary.di.scope.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(modules = MostStarModule.class, dependencies = AppComponent.class)
public interface MostStarComponent {
    void inject(MostStarFragment arg);
}