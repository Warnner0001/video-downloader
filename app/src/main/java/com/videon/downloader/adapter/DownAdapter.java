package com.videon.downloader.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.material.textview.MaterialTextView;
import com.videon.downloader.R;
import com.videon.downloader.include.Function;
import com.videon.downloader.interfaces.OnClick;
import com.videon.downloader.list.DownList;

import java.util.List;

public class DownAdapter extends RecyclerView.Adapter<DownAdapter.ViewHolder> {

    private final Activity activity;
    private final String type;
    private final List<DownList> appsLists;
    private final Function function;

    public DownAdapter(Activity activity, List<DownList> downLists, String type, OnClick onClick) {
        this.activity = activity;
        this.type = type;
        this.appsLists = downLists;
        function = new Function(activity, onClick);
    }

    @NonNull
    @Override
    public DownAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.down_list, parent, false);
        return new DownAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DownAdapter.ViewHolder holder, final int position) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        holder.conDownload.setOnClickListener(v -> function.onDownClick(position, appsLists.get(position).getDownloader_name(), "", ""));
        Glide.with(activity).load(appsLists.get(position).getImage()).placeholder(R.drawable.logo).into(holder.imageView);
        holder.title.setText(appsLists.get(position).getDownloader_name());
    }

    @Override
    public int getItemCount() {
        return appsLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ConstraintLayout conDownload;
        private final ImageView imageView;
        private final MaterialTextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            conDownload = itemView.findViewById(R.id.conDownload);
            imageView = itemView.findViewById(R.id.imageIcon);
            title = itemView.findViewById(R.id.downName);
        }
    }
}
