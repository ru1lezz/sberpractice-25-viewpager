package com.example.android.viewpager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ImageActivity extends AppCompatActivity {

    private static final String EXTRA_LIST = "uri_list";
    private static final String EXTRA_POSITION = "position";

    private List<Uri> mUriList;
    private int mPosition;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        mUriList = getIntent().getParcelableArrayListExtra(EXTRA_LIST);
        mPosition = getIntent().getIntExtra(EXTRA_POSITION, 0);
        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setAdapter(new ImagePagerAdapter(getSupportFragmentManager(), mUriList));
        mViewPager.setCurrentItem(mPosition);
    }

    public static Intent newIntent(Context context, ArrayList<Uri> uriList, int position) {
        Intent intent = new Intent(context, ImageActivity.class);
        intent.putParcelableArrayListExtra(EXTRA_LIST, uriList);
        intent.putExtra(EXTRA_POSITION, position);
        return intent;
    }
}
