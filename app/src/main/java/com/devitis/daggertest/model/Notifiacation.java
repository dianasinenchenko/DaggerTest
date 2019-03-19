package com.devitis.daggertest.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Diana on 19.03.2019.
 */

public class Notifiacation extends RealmObject {
    @Getter
    @Setter
    @PrimaryKey
    private String id;
    @Getter
    @Setter
    private Date create_at;
    @Getter
    @Setter
    private Date seen_at;
    @Getter
    @Setter
    private Date read_at;
    @Getter
    @Setter
    String event_data;


}
