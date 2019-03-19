package com.devitis.daggertest.injection.component;

import android.app.Application;

import com.devitis.daggertest.MainActivity;
import com.devitis.daggertest.injection.module.ApplicationModule;
import com.devitis.daggertest.service.NotificationService;
import com.devitis.daggertest.ui.presenter.MainPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Diana on 19.03.2019.
 */
@Singleton
// inject the modules
@Component(modules = {ApplicationModule.class})

// inject the dependencies
public interface ApplicationComponent {
    void inject(MainActivity activity);

    void inject(Application application);

    void inject(NotificationService service);

    void inject(MainPresenter presenter);

}
