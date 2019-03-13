package com.example.android.viewpager;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageFragment extends Fragment {

    private static final String EXTRA_URI = "extra_uri";

    private ImageView mImageView;

    public ImageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mImageView = view.findViewById(R.id.image_view_fragment);
        mImageView.setImageURI(getArguments().getParcelable(EXTRA_URI));
        mImageView.setMaxWidth(300);
        mImageView.setMaxHeight(300);
    }

    public static ImageFragment newInstance(Uri uri) {
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_URI, uri);
        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
