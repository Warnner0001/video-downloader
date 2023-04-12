package com.videon.downloader.fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.videon.downloader.R;
import com.videon.downloader.activity.MainActivity;
import com.videon.downloader.adapter.DownloadVideoAdapter;
import com.videon.downloader.databinding.FragmentGalleryBinding;
import com.videon.downloader.include.Constant;
import com.videon.downloader.include.Function;
import com.videon.downloader.interfaces.OnClick;
import com.videon.downloader.list.DataModel;

import org.apache.commons.io.comparator.LastModifiedFileComparator;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class GalleryVideoFragment extends Fragment {
    private Function function;
    private Activity activity;
    File file;
    ArrayList<DataModel> downloadImageList = new ArrayList<>();
    ArrayList<DataModel> downloadVideoList = new ArrayList<>();
    RecyclerView.LayoutManager mLayoutManager;
    DownloadVideoAdapter mAdapter;
    private FragmentGalleryBinding binding;

    public GalleryVideoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        loadMedia();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        activity = requireActivity();
        mLayoutManager = new GridLayoutManager(activity, 3);
        binding.rvGallery.setLayoutManager(mLayoutManager);
        OnClick onClick = (position, title, type, id) -> {

        };
        function = new Function(requireActivity(), onClick);
        return view;
    }

    public void loadMedia() {
        file = Constant.whatsAppStatusFolder;
        downloadImageList.clear();
        downloadVideoList.clear();
        if (!file.isDirectory()) {
            return;
        }
        viewDownloadedFiles(file, binding.rvGallery);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void viewDownloadedFiles(File file, final RecyclerView mRecyclerView) {
        File[] downloadedFiles = dirListByAscendingDate(file);
        if (Objects.requireNonNull(downloadedFiles).length == 0) {
            function.showToast(activity.getString(R.string.no_found));
        }
        int i = 0;
        while (i < downloadedFiles.length) {
            downloadImageList.add(new DataModel(downloadedFiles[i].getAbsolutePath(), downloadedFiles[i].getName()));
            i++;
        }

        if (downloadImageList.size() == 0) {
            function.showToast(activity.getString(R.string.no_found));
        }
        mAdapter = new DownloadVideoAdapter(activity, downloadImageList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public static File[] dirListByAscendingDate(File folder) {
        if (!folder.isDirectory()) {
            return null;
        }
        File[] sortedByDate = folder.listFiles();
        if (sortedByDate == null || sortedByDate.length <= 1) {
            return sortedByDate;
        }
        Arrays.sort(sortedByDate, LastModifiedFileComparator.LASTMODIFIED_REVERSE);

        return sortedByDate;
    }

    @Override
    public void onDestroyView() {
        MainActivity.binding.toolbar.toolbarMain.setTitle(activity.getString(R.string.app_name));
        super.onDestroyView();
    }

}