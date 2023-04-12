package com.videon.downloader.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.videon.downloader.fragment.GalleryVideoFragment;

public class GalleryAdapter extends FragmentPagerAdapter {


    public GalleryAdapter(FragmentManager fm) {
        super(fm);

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new GalleryVideoFragment();
    }

    @Override
    public int getCount() {
        return 12;
    }


}
