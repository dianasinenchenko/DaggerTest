package com.devitis.daggertest.service;

import android.app.Notification;
import android.content.Context;
import android.util.Log;

import com.devitis.daggertest.R;
import com.devitis.daggertest.injection.component.ApplicationComponent;
import com.devitis.daggertest.model.SyncEvent;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Callback;
import retrofit2.Retrofit;
import org.greenrobot.eventbus.EventBus;



/**
 * Created by Diana on 19.03.2019.
 */

public class NotificationService {
    private static final String TAG = NotificationService.class.getSimpleName();

    @Inject
    ApiService apiService;

    @Inject
    Realm realmInstance;

    @Inject
    EventBus mBus;

    @Inject
    Context appContext;

    public NotificationService(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    public RealmResults<Notification> getLocalNotifications() {
        return realmInstance.allObjects(Notification.class);
    }

    public void fetchNotificationsAsync() {
        Call<List<Notification>> call = apiService.getNotifications();
        call.enqueue(new Callback<List<Notification>>() {
            @Override
            public void onResponse(retrofit.Response<List<Notification>> response, Retrofit retrofit) {
                List<Notification> list = response.body();
                Log.d(TAG, "onResponse: " + list.size());
                if (list.size() > 0) {
                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    realm.copyToRealmOrUpdate(list);
                    realm.commitTransaction();
                    mBus.post(new SyncEvent(appContext.getString(R.string.synced_event)));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure");
                mBus.post(new SyncEvent(appContext.getString(R.string.sync_failed_event)));
                t.printStackTrace();
            }
        });
    }
}
