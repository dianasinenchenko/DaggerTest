package com.devitis.daggertest;

import android.app.Application;

import com.devitis.daggertest.injection.component.ApplicationComponent;
import com.devitis.daggertest.injection.component.DaggerApplicationComponent;
import com.devitis.daggertest.injection.module.ApplicationModule;
import com.devitis.daggertest.injection.module.DatabaseModule;

/**
 * Created by Diana on 19.03.2019.
 */

public class DaggerDemoApplication extends Application {
    private ApplicationComponent component;

    public ApplicationComponent getComponent() {
        if (component == null) {

            component = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .databaseModule(new DatabaseModule())
                    .build();
        }
        return component;
    }
}
