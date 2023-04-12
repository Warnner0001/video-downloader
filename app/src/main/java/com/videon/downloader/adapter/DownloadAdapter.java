package com.videon.downloader.adapter;

import static com.videon.downloader.include.Constant.FALSE;
import static com.videon.downloader.include.Constant.TRUE;
import static com.videon.downloader.include.Constant.VideoImage;
import static com.videon.downloader.include.Constant.VideoName;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.videon.downloader.R;
import com.videon.downloader.include.Function;
import com.videon.downloader.interfaces.OnClick;
import com.videon.downloader.list.DownloadList;

import java.util.List;

public class DownloadAdapter extends RecyclerView.Adapter<DownloadAdapter.ViewHolder> {

    private final Activity activity;
    private final List<DownloadList> downloadLists;
    private final Function function;
    private String VideoType = "";

    public DownloadAdapter(Activity activity, List<DownloadList> downLists, String type, OnClick onClick) {
        this.activity = activity;
        this.downloadLists = downLists;
        function = new Function(activity, onClick);
    }

    @NonNull
    @Override
    public DownloadAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.down_lists, parent, false);
        return new DownloadAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DownloadAdapter.ViewHolder holder, final int position) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Glide.with(activity).load(VideoImage).into(holder.imageView);
        holder.cardView.setOnClickListener(v -> function.onDownClick(position, downloadLists.get(position).getTitle(), downloadLists.get(position).getExtension(), downloadLists.get(position).getUrl()));
        holder.vidName.setText(VideoName);
        String audio = downloadLists.get(position).getAudioAvailable();
        String video = downloadLists.get(position).getVideoAvailable();

        if (audio.equals(TRUE) && video.equals(TRUE)) {
            VideoType = "Audio/Video";
        } else if (audio.equals(TRUE) && video.equals(FALSE)) {
            VideoType = "Only Audio";
        } else if (audio.equals(FALSE) && video.equals(TRUE)) {
            VideoType = "No Audio";
        }
        holder.tvDesc.setText(activity.getResources().getString(R.string.video_type) + VideoType
                + " | " + activity.getResources().getString(R.string.extension) + downloadLists.get(position).getExtension()
                + "\n" + activity.getResources().getString(R.string.video_size) + downloadLists.get(position).getFormattedSize()
                + " | " + activity.getResources().getString(R.string.video_quality) + downloadLists.get(position).getQuality()
                + "\n" + activity.getResources().getString(R.string.video_duration) + downloadLists.get(position).getDuration());
    }

    @Override
    public int getItemCount() {
        return downloadLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final MaterialCardView cardView;
        private final MaterialTextView vidName, tvDesc;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageIcon);
            vidName = itemView.findViewById(R.id.vidName);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
