package com.example.android_gallerymanager.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_gallerymanager.R;
import com.example.android_gallerymanager.adapter.AlbumAdapter;
import com.example.android_gallerymanager.data.DataHelper;
import com.example.android_gallerymanager.model.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Album> albumList = new ArrayList<Album>();
    private AlbumAdapter albumAdapter;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album, container, false);
        recyclerView = view.findViewById(R.id.albums);
        albumList = DataHelper.Companion.findAlbums(getContext().getContentResolver());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        albumAdapter = new AlbumAdapter(getActivity(), albumList);
        recyclerView.setAdapter(albumAdapter);
        return view;
    }
}