package com.devitis.daggertest.model;

import android.support.annotation.NonNull;

import lombok.Getter;

/**
 * Created by Diana on 19.03.2019.
 */

public class SyncEvent {
    @Getter
    private String message;

    public SyncEvent(@NonNull String message) {
        this.message = message;
    }
}
