package com.videon.downloader.adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.videon.downloader.R;
import com.videon.downloader.include.Function;
import com.videon.downloader.interfaces.OnClick;
import com.videon.downloader.responses.AppsRP;

import java.util.ArrayList;

public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.RecyclerViewHolder> {

    private final ArrayList<AppsRP> appsRPS;
    private final Activity activity;
    private final Function function;

    public AppsAdapter(ArrayList<AppsRP> recyclerDataArrayList, Activity activity, OnClick onClick) {
        this.appsRPS = recyclerDataArrayList;
        this.activity = activity;
        function = new Function(activity, onClick);
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.apps_list, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        AppsRP recyclerData = appsRPS.get(position);
        holder.appName.setText(recyclerData.getTitle());
        holder.appImage.setImageResource(recyclerData.getImage());
        holder.cardApp.setOnClickListener(v -> function.openWebPage(appsRPS.get(position).getLink()));
    }

    @Override
    public int getItemCount() {
        return appsRPS.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private MaterialCardView cardApp;
        private TextView appName;
        private ImageView appImage;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            cardApp = itemView.findViewById(R.id.cardApp);
            appName = itemView.findViewById(R.id.appName);
            appImage = itemView.findViewById(R.id.imageIcon);
        }
    }
}
