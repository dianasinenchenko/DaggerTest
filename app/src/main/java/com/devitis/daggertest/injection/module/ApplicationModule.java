package com.devitis.daggertest.injection.module;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Diana on 19.03.2019.
 */
@Module
public class ApplicationModule {
    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    // provide Context in ApplicationModule
    public Context provideContext() {
        return application;
    }
}
