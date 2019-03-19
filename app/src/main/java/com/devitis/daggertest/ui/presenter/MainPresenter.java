package com.devitis.daggertest.ui.presenter;

import android.support.annotation.NonNull;

import com.devitis.daggertest.injection.component.ApplicationComponent;
import com.devitis.daggertest.model.SyncEvent;
import com.devitis.daggertest.service.NotificationService;
import com.devitis.daggertest.ui.view.MainView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

/**
 * Created by Diana on 19.03.2019.
 */

public class MainPresenter {
    @Inject
    NotificationService mService;

    @Inject
    EventBus mBus;

    private MainView view;

    public MainPresenter(@NonNull ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }


    public void onResume() {
        mBus.register(this);
    }
    public void onPause(){
        mBus.unregister(this);
    }

    public void setView(@NonNull MainView view) {
        this.view = view;
        this.view.showNotifications(mService.getLocalNotifications());
        mService.fetchNotificationsAsync();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(SyncEvent event){
        view.showSyncMessage(event.getMessage());
    }
}
