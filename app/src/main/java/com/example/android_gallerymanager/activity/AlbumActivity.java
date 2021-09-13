package com.example.android_gallerymanager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.android_gallerymanager.R;
import com.example.android_gallerymanager.adapter.AlbumAdapter;
import com.example.android_gallerymanager.adapter.ImageAdapter;
import com.example.android_gallerymanager.data.DataHelper;
import com.example.android_gallerymanager.model.Image;

import java.util.ArrayList;
import java.util.List;

public class AlbumActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private List<Image> imageList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        initView();
        initEvent();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("Album")) {
                String albumID = intent.getStringExtra("Album");
                imageList = DataHelper.Companion.findImagesInAlbum(getContentResolver(), albumID);
                recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                imageAdapter = new ImageAdapter(this, imageList);
                recyclerView.setAdapter(imageAdapter);
            }
        }
    }

    private void initEvent() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Album");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar_album);
        recyclerView = findViewById(R.id.recycler_view_image_album);
    }
}