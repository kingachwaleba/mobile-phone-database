package com.example.mobilephonedatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MobilePhone {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    private String producer;

    @NonNull
    private String model;

    @NonNull
    private String androidVersion;

    @NonNull
    private String wwwPage;

    public MobilePhone(@NonNull String producer, @NonNull String model, @NonNull String androidVersion, @NonNull String wwwPage) {
        this.producer = producer;
        this.model = model;
        this.androidVersion = androidVersion;
        this.wwwPage = wwwPage;
    }

    public long getId() {
        return id;
    }

    @NonNull
    public String getProducer() {
        return producer;
    }

    public void setProducer(@NonNull String producer) {
        this.producer = producer;
    }

    @NonNull
    public String getModel() {
        return model;
    }

    public void setModel(@NonNull String model) {
        this.model = model;
    }

    @NonNull
    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(@NonNull String androidVersion) {
        this.androidVersion = androidVersion;
    }

    @NonNull
    public String getWwwPage() {
        return wwwPage;
    }

    public void setWwwPage(@NonNull String wwwPage) {
        this.wwwPage = wwwPage;
    }
}
