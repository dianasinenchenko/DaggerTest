package com.devitis.daggertest.ui.view;

import android.app.Notification;
import android.support.annotation.NonNull;

import io.realm.RealmResults;

/**
 * Created by Diana on 19.03.2019.
 */

public interface MainView {
    void showNotifications(@NonNull RealmResults<Notification> notifications);
    void showSyncMessage(@NonNull String message);
}
