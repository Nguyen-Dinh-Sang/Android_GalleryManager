package com.example.android_gallerymanager.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akshaykale.swipetimeline.TimelineFragment;
import com.akshaykale.swipetimeline.TimelineGroupType;
import com.akshaykale.swipetimeline.TimelineObject;
import com.example.android_gallerymanager.R;
import com.example.android_gallerymanager.data.DataHelper;
import com.example.android_gallerymanager.data.ImageLoad;
import com.example.android_gallerymanager.model.Image;

import java.util.ArrayList;
import java.util.List;

public class TimeLineFragment extends Fragment {

    private View view;
    private TimelineFragment mFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_time_line, container, false);


        mFragment = new TimelineFragment();
        ArrayList<TimelineObject> objs = loadDataInTimeline();
        mFragment.setData(objs, TimelineGroupType.DAY);
        mFragment.setImageLoadEngine(new ImageLoad(getContext()));
        loadFragment(mFragment);

        return view;
    }

    private ArrayList<TimelineObject> loadDataInTimeline() {
        ArrayList<TimelineObject> objs = new ArrayList<>();
        List<Image> imageList = new ArrayList<>();
        imageList =  DataHelper.Companion.findAllImages(getContext().getContentResolver());

        for(Image image: imageList) {
            Log.d("TAGXXX", "loadDataInTimeline: " + image.toString());
            objs.add(image);
        }
        return objs;
    }

    private void loadFragment(Fragment newFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, newFragment);
        transaction.commit();
    }
}