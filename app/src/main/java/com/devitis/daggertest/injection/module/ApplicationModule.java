package com.devitis.daggertest.injection.module;

import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.devitis.daggertest.service.ApiService;
import com.devitis.daggertest.service.NotificationAdapter;
import com.devitis.daggertest.service.NotificationService;
import com.devitis.daggertest.ui.presenter.MainPresenter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Diana on 19.03.2019.
 */
@Module
public class ApplicationModule {
    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }


    private static final String API_ENDPOINT = "http://api.instamotorlabs.com";


    @Provides
    @Singleton
    // provide Context in ApplicationModule
    public Context providerApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPrefs() {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    Realm providerRealm() {
        return Realm.getDefaultInstance();
    }

//     @Provides
@Singleton
public ApiService apiService() {
    OkHttpClient client = new OkHttpClient();

    final GsonBuilder builder = new GsonBuilder()
            .registerTypeAdapter(Notification.class, new NotificationAdapter());

    final Gson gson = builder.create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build();

    ApiService service = retrofit.create(ApiService.class);
    return  service;
}

    @Provides
    @Singleton
    NotificationService notificationService() {
        return new NotificationService(application.getComponent());
    }

    @Provides
    @Singleton
    MainPresenter mainPresenter() {
        return new MainPresenter(application.getComponent());
    }

    @Provides
    @Singleton
    EventBus eventBus() {
        return EventBus.getDefault();
    }
}
