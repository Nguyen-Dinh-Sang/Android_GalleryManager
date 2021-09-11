package com.example.android_gallerymanager.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android_gallerymanager.R;
import com.example.android_gallerymanager.model.Album;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    Activity activity;
    List<Album> albumList;

    public AlbumAdapter(Activity activity, List<Album> contactList) {
        this.activity = activity;
        this.albumList = contactList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = albumList.get(position);
        Log.d("TAGXXX", "onBindViewHolder: " + album.toString());
        holder.textViewName.setText(album.getName());
        holder.textViewNumber.setText(album.getCount().toString());
        Glide.with(activity).load(album.getLastImageUri().getPath()).centerCrop().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewNumber;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_album_name);
            textViewNumber = itemView.findViewById(R.id.text_view_album_count);
            imageView = itemView.findViewById(R.id.image_view_album);
        }
    }
}
