package com.example.android_gallerymanager.model;

import android.net.Uri;

public class Album {
    private String id;
    private String name;
    private Long count;
    private Uri lastImageUri;

    public Album(String id, String name, Long count, Uri lastImageUri) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.lastImageUri = lastImageUri;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getLastImageUri() {
        return lastImageUri;
    }

    public void setLastImageUri(Uri lastImageUri) {
        this.lastImageUri = lastImageUri;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", lastImageUri=" + lastImageUri +
                '}';
    }
}
