package com.example.android.viewpager;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ImagePagerAdapter extends FragmentStatePagerAdapter {

    private List<Uri> mUriList;

    public ImagePagerAdapter(FragmentManager fm, List<Uri> uriList) {
        super(fm);
        mUriList = new ArrayList<>();
        mUriList.addAll(uriList);
    }

    @Override
    public Fragment getItem(int i) {
        return ImageFragment.newInstance(mUriList.get(i));
    }

    @Override
    public int getCount() {
        return mUriList.size();
    }
}