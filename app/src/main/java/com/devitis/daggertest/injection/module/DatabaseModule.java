package com.devitis.daggertest.injection.module;

/**
 * Created by Diana on 19.03.2019.
 */

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;
@Module
public class DatabaseModule {
    @Provides
    Realm provideRealm(Context context) {
        Realm.init(context);
        RealmConfiguration.Builder builder = new RealmConfiguration.Builder();
        builder.name("dagger.realm");

        RealmConfiguration config = builder.build();
        return Realm.getInstance(config);
    }
}

