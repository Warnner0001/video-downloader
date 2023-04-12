package com.videon.downloader.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.card.MaterialCardView;
import com.videon.downloader.R;
import com.videon.downloader.include.Constant;
import com.videon.downloader.include.Function;
import com.videon.downloader.list.DataModel;

import java.io.File;
import java.util.ArrayList;

public class WAppStatusAdapter extends RecyclerView.Adapter<WAppStatusAdapter.ViewHolder> {
    private final Context activity;
    ArrayList<DataModel> mData;
    String folderPath;
    boolean isWApp;

    public WAppStatusAdapter(Context activity, ArrayList<DataModel> jData, boolean isWApp) {
        this.mData = jData;
        this.activity = activity;
        this.isWApp = isWApp;
        folderPath = File.separator + Constant.VideoSavePath + File.separator;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.wa_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final DataModel jpast = this.mData.get(position);
        Glide.with(this.activity).load(jpast.getFilePath()).apply(new RequestOptions().placeholder(R.color.black).error(android.R.color.black).optionalTransform(new RoundedCorners(5))).into(holder.imgWa);
        holder.cardView.setOnClickListener(v -> {
            Function.copyFileInSavedDir(activity, jpast.getFilePath());
            Toast.makeText(activity, activity.getString(R.string.complete_downs), Toast.LENGTH_LONG).show();
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final MaterialCardView cardView;
        private final ImageView imgWa;

        public ViewHolder(View itemView) {
            super(itemView);
            imgWa = itemView.findViewById(R.id.imageIcon);
            cardView = itemView.findViewById(R.id.cardApp);
        }
    }

}
