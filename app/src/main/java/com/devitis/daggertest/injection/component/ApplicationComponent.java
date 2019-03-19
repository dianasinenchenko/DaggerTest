package com.devitis.daggertest.injection.component;

import com.devitis.daggertest.MainActivity;
import com.devitis.daggertest.injection.module.ApplicationModule;
import com.devitis.daggertest.injection.module.DatabaseModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Diana on 19.03.2019.
 */
@Component(modules = {ApplicationModule.class, DatabaseModule.class})
@Singleton
// inject the dependencies
public interface ApplicationComponent {
    void inject(MainActivity activity);
}
