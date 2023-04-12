package com.videon.downloader.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.videon.downloader.R;
import com.videon.downloader.activity.VideoPlayerActivity;
import com.videon.downloader.include.Function;
import com.videon.downloader.list.DataModel;

import java.util.ArrayList;

public class FullPreviewAdapter extends PagerAdapter {
    Activity activity;
    ArrayList<DataModel> imageList;

    public FullPreviewAdapter(Activity activity, ArrayList<DataModel> imageList) {
        this.activity = activity;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = LayoutInflater.from(activity).inflate(R.layout.preview_item_list, container, false);

        ImageView imgItem = itemView.findViewById(R.id.imgItem);
        ImageView imgPlay = itemView.findViewById(R.id.imgPlay);

        if (!Function.getBack(imageList.get(position).getFilePath(), "((\\.mp4|\\.webm|\\.ogg|\\.mpK|\\.avi|\\.mkv|\\.flv|\\.mpg|\\.wmv|\\.vob|\\.ogv|\\.mov|\\.qt|\\.rm|\\.rmvb\\.|\\.asf|\\.m4p|\\.m4v|\\.mp2|\\.mpeg|\\.mpe|\\.mpv|\\.m2v|\\.3gp|\\.f4p|\\.f4a|\\.f4b|\\.f4v)$)").isEmpty()) {
            imgPlay.setVisibility(View.VISIBLE);
        } else {
            imgPlay.setVisibility(View.GONE);
        }

        Glide.with(this.activity).load(imageList.get(position).getFilePath()).into(imgItem);

        imgItem.setOnClickListener(view -> {
            if (!Function.getBack(imageList.get(position).getFilePath(), "((\\.mp4|\\.webm|\\.ogg|\\.mpK|\\.avi|\\.mkv|\\.flv|\\.mpg|\\.wmv|\\.vob|\\.ogv|\\.mov|\\.qt|\\.rm|\\.rmvb\\.|\\.asf|\\.m4p|\\.m4v|\\.mp2|\\.mpeg|\\.mpe|\\.mpv|\\.m2v|\\.3gp|\\.f4p|\\.f4a|\\.f4b|\\.f4v)$)").isEmpty()) {
                Intent intent = new Intent(activity, VideoPlayerActivity.class);
                intent.putExtra("videoPath", imageList.get(position).getFilePath());
                activity.startActivity(intent);
            }
        });
        container.addView(itemView);

        return itemView;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
