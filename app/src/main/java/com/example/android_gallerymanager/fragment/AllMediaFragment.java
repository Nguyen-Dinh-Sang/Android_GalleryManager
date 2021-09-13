package com.example.android_gallerymanager.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_gallerymanager.R;
import com.example.android_gallerymanager.adapter.AlbumAdapter;
import com.example.android_gallerymanager.adapter.ImageAdapter;
import com.example.android_gallerymanager.data.DataHelper;
import com.example.android_gallerymanager.model.Album;
import com.example.android_gallerymanager.model.Image;

import java.util.ArrayList;
import java.util.List;

import kotlin.reflect.KVariance;

public class AllMediaFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Image> imageList = new ArrayList<>();
    private ImageAdapter imageAdapter;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_all_media, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_all_image);
        imageList = DataHelper.Companion.findAllImages(getContext().getContentResolver());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        imageAdapter = new ImageAdapter(getActivity(), imageList);
        recyclerView.setAdapter(imageAdapter);
        return view;
    }
}