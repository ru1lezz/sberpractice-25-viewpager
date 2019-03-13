package com.example.android.viewpager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ExecutorService mExecutorService;
    private ArrayList<Uri> mUriList;
    private GridView mGridView;
    private ImageGridAdapter mImageGridAdapter;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mExecutorService = Executors.newSingleThreadExecutor();
        mUriList = new ArrayList<>();
        mGridView = findViewById(R.id.grid_view);
        mGridView.setOnItemClickListener((parent, view, position, id) -> {
            startActivity(ImageActivity.newIntent(MainActivity.this, mUriList, position));
        });
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mUriList.size() < 1) {
            if(hasExternalStoragePermission()) {
                mExecutorService.execute(() -> getImages());
            }
        }
    }

    private boolean hasExternalStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            return false;
        }
        return true;
    }

    private void getImages() {
        final String[] columns = {
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATA
        };

        Cursor cursor = managedQuery(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                columns,
                null,
                null,
                MediaStore.Images.Media._ID
        );

        if(cursor != null) {
            ArrayList<Uri> tempList = new ArrayList<>();
            while(cursor.moveToNext()) {
                int column = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                tempList.add(Uri.parse(cursor.getString(column)));
            }
            mHandler.post(() -> setList(tempList));
        }
    }

    private void setList(ArrayList<Uri> uriList) {
        mUriList = uriList;
        mImageGridAdapter = new ImageGridAdapter(MainActivity.this, mUriList);
        mGridView.setAdapter(mImageGridAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mExecutorService.execute(() -> getImages());
            }
        }
    }
}
