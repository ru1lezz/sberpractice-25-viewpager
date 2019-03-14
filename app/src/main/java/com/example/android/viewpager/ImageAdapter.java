package com.example.android.viewpager;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private ArrayList<Uri> mUriList;

    public ImageAdapter() {
        mUriList = new ArrayList<>();
    }

    public void setUriList(List<Uri> uriList) {
        mUriList.clear();
        mUriList.addAll(uriList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_list_item, viewGroup, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder imageViewHolder, int i) {
        imageViewHolder.bind(mUriList.get(i), i);
    }

    @Override
    public int getItemCount() {
        return mUriList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mImageView;
        private int mPosition;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view_item);
            mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            itemView.setOnClickListener(this);
        }

        public void bind(Uri uri, int position) {
            mImageView.setImageURI(uri);
            mPosition = position;
        }

        @Override
        public void onClick(View v) {
            v.getContext().startActivity(ImageViewPagerActivity.newIntent(v.getContext(), mUriList, mPosition));
        }
    }
}
