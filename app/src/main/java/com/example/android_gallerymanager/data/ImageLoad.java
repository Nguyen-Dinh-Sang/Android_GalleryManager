package com.example.android_gallerymanager.data;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.akshaykale.swipetimeline.ImageLoadingEngine;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class ImageLoad implements ImageLoadingEngine {

    Context context;

    public ImageLoad(Context context) {
        this.context = context;
    }

    @Override
    public void onLoadImage(ImageView imageView, String uri) {
        Glide.with(context).load(uri).centerCrop().into(imageView);
    }
}