package com.devitis.daggertest.service;

import android.app.Notification;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Diana on 19.03.2019.
 */

public interface ApiService {
    @GET("v2/mp/debug/notifications")
    Call<List<Notification>> getNotifications();
}
