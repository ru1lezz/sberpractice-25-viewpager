package com.example.android.viewpager;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ImageGridAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<Uri> mURiList;

    public ImageGridAdapter(Context context, List<Uri> uriList) {
        mContext = context;
        mURiList = new ArrayList<>();
        mURiList.addAll(uriList);
    }

    @Override
    public int getCount() {
        return mURiList.size();
    }

    @Override
    public Object getItem(int position) {
        return mURiList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if(convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 300));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setMaxWidth(200);
            imageView.setMaxHeight(200);
        } else {
            imageView = (ImageView) convertView;
            imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 300));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setMaxWidth(200);
            imageView.setMaxHeight(200);
        }
        imageView.setImageURI(mURiList.get(position));

        return imageView;
    }
}
