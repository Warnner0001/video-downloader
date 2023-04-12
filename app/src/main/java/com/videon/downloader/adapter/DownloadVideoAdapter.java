package com.videon.downloader.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.card.MaterialCardView;
import com.videon.downloader.R;
import com.videon.downloader.activity.PreviewActivity;
import com.videon.downloader.include.Function;
import com.videon.downloader.list.DataModel;

import java.io.File;
import java.util.ArrayList;

public class DownloadVideoAdapter extends RecyclerView.Adapter<DownloadVideoAdapter.ViewHolder> {
    private Activity activity;
    private File file;
    ArrayList<DataModel> mData;

    public DownloadVideoAdapter(Activity paramActivity, ArrayList<DataModel> paramArrayList) {
        this.mData = paramArrayList;
        this.activity = paramActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.download_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final DataModel jpast = this.mData.get(position);
        this.file = new File(jpast.getFilePath());
        if (!this.file.isDirectory()) {
            if (!Function.getBack(jpast.getFilePath(), "((\\.mp4|\\.webm|\\.ogg|\\.mpK|\\.avi|\\.mkv|\\.flv|\\.mpg|\\.wmv|\\.vob|\\.ogv|\\.mov|\\.qt|\\.rm|\\.rmvb\\.|\\.asf|\\.m4p|\\.m4v|\\.mp2|\\.mpeg|\\.mpe|\\.mpv|\\.m2v|\\.3gp|\\.f4p|\\.f4a|\\.f4b|\\.f4v)$)").isEmpty()) {
                try {
                    Glide.with(this.activity).load(this.file).apply(new RequestOptions().placeholder(R.color.black).error(android.R.color.black).optionalTransform(new RoundedCorners(1))).into(holder.imgList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                holder.imgPlay.setVisibility(View.VISIBLE);
            } else if (!Function.getBack(jpast.getFilePath(), "((\\.3ga|\\.aac|\\.aif|\\.aifc|\\.aiff|\\.amr|\\.au|\\.aup|\\.caf|\\.flac|\\.gsm|\\.kar|\\.m4a|\\.m4p|\\.m4r|\\.mid|\\.midi|\\.mmf|\\.mp2|\\.mp3|\\.mpga|\\.ogg|\\.oma|\\.opus|\\.qcp|\\.ra|\\.ram|\\.wav|\\.wma|\\.xspf)$)").isEmpty()) {
                holder.imgPlay.setVisibility(View.GONE);
            } else if (!Function.getBack(jpast.getFilePath(), "((\\.jpg|\\.png|\\.gif|\\.jpeg|\\.bmp)$)").isEmpty()) {
                holder.imgPlay.setVisibility(View.GONE);
                Glide.with(this.activity).load(this.file).apply(new RequestOptions().placeholder(R.color.black).error(android.R.color.black).optionalTransform(new RoundedCorners(1))).into(holder.imgList);
            }

            holder.imgDelete.setOnClickListener(v -> delete(position, activity));
            holder.imgShare.setOnClickListener(v -> share(jpast.getFilePath(), activity));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private final ImageView imgList, imgShare, imgDelete, imgPlay;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imgList = itemView.findViewById(R.id.imageIcon);
            MaterialCardView cardView = itemView.findViewById(R.id.cardDownList);
            cardView.setOnClickListener(this);
            this.imgShare = itemView.findViewById(R.id.imgShare);
            this.imgDelete = itemView.findViewById(R.id.imgDelete);
            this.imgPlay = itemView.findViewById(R.id.imgPlay);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(activity, PreviewActivity.class);
            intent.putParcelableArrayListExtra("images", mData);
            intent.putExtra("position", getAdapterPosition());
            activity.startActivity(intent);
        }
    }

    void share(String path, Activity activity) {
        Function.mShare(path, activity);
    }

    @SuppressLint("NotifyDataSetChanged")
    void delete(final int position, Activity activity) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
        alertDialog.setTitle("Delete Item");
        alertDialog.setMessage("Are You Sure to Delete this File?");
        alertDialog.setPositiveButton("Yes", (dialog, which) -> {
            dialog.dismiss();
            File file = new File(mData.get(position).getFilePath());
            if (file.exists()) {
                file.delete();
                mData.remove(position);
                notifyDataSetChanged();
                Toast.makeText(activity, "not delete" + file, Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.dismiss());
        alertDialog.show();
    }
}
