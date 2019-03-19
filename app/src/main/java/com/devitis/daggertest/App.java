package com.devitis.daggertest;

import android.app.Application;

import com.devitis.daggertest.injection.component.ApplicationComponent;
import com.devitis.daggertest.injection.component.DaggerApplicationComponent;
import com.devitis.daggertest.injection.module.ApplicationModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Diana on 19.03.2019.
 */

public class App  extends Application{
    private ApplicationComponent mComponent;

    @Override public void onCreate() {
        super.onCreate();
        initRealmConfiguration();
        mComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    private void initRealmConfiguration() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public ApplicationComponent getComponent() {
        return mComponent;
    }
}
