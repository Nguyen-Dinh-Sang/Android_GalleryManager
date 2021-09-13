package com.example.android_gallerymanager.model;

import android.net.Uri;

import com.akshaykale.swipetimeline.TimelineObject;

import java.util.Date;

public class Image implements TimelineObject {
    private String id;
    private String name;
    private Uri link;
    private long timeline;

    public Image(String id, String name, Uri link, long timeline) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.timeline = timeline;
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

    public Uri getLink() {
        return link;
    }

    public void setLink(Uri link) {
        this.link = link;
    }

    public long getTimeline() {
        return timeline;
    }

    public void setTimeline(long timeline) {
        this.timeline = timeline;
    }

    @Override
    public long getTimestamp() {
        return timeline;
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public String getImageUrl() {
        return link.toString();
    }

    @Override
    public String toString() {
        return "Image{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", link=" + link.toString() +
                ", timeline=" + timeline +
                '}';
    }
}
