package com.videon.downloader.fragment;

import static android.content.Context.CLIPBOARD_SERVICE;
import static com.videon.downloader.include.Constant.DOWNLOADER;
import static com.videon.downloader.include.Constant.Download;
import static com.videon.downloader.include.Constant.DownloadName;
import static com.videon.downloader.include.Constant.DownloadUrl;
import static com.videon.downloader.include.Constant.EXCEPTION;
import static com.videon.downloader.include.Constant.FAIL;
import static com.videon.downloader.include.Constant.GodIsOne;
import static com.videon.downloader.include.Constant.OneDownload;
import static com.videon.downloader.include.Constant.VideoDownloadedID;
import static com.videon.downloader.include.Constant.VideoDownloader;
import static com.videon.downloader.include.Constant.VideoDuration;
import static com.videon.downloader.include.Constant.VideoID;
import static com.videon.downloader.include.Constant.VideoImage;
import static com.videon.downloader.include.Constant.VideoName;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.videon.downloader.R;
import com.videon.downloader.activity.MainActivity;
import com.videon.downloader.adapter.DownloadAdapter;
import com.videon.downloader.databinding.FragmentDownloadBinding;
import com.videon.downloader.include.API;
import com.videon.downloader.include.Constant;
import com.videon.downloader.include.Function;
import com.videon.downloader.interfaces.OnClick;
import com.videon.downloader.list.DownloadList;
import com.videon.downloader.responses.DownloadRP;
import com.videon.downloader.rest.ApiClient;
import com.videon.downloader.rest.ApiInterface;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownloadFragment extends Fragment {
    private Function function;
    private Activity activity;
    private FragmentDownloadBinding binding;
    private OnClick onClick;
    private ClipboardManager myClipboard;
    private List<DownloadList> downLists;
    private DownloadAdapter downAdapter;
    private LayoutAnimationController animation;

    public DownloadFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDownloadBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        activity = requireActivity();
        downLists = new ArrayList<>();
        activity.registerReceiver(onDownloadComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        String alphaNumeric = Calendar.getInstance().getTimeInMillis() + VideoDownloader;
        onClick = (position, title, type, id) -> {
            if (activity != null) {
                try {
                    downloadTask(id, alphaNumeric + type);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        function = new Function(activity, onClick);
        function.startMediation();
        MainActivity.binding.toolbar.toolbarMain.setTitle(DownloadName);
        myClipboard = (ClipboardManager) activity.getSystemService(CLIPBOARD_SERVICE);
        int resId = R.anim.layout_animation_fall_down;
        animation = AnimationUtils.loadLayoutAnimation(getActivity(), resId);
        binding.rvDownloader.setLayoutManager(new GridLayoutManager(activity, 1));
        binding.rvDownloader.setFocusable(false);
        if (!DownloadUrl.isEmpty()) {
            binding.editTextUrl.setText(DownloadUrl);
            binding.tvPaste.setText(getString(R.string.clear));
        }
        binding.btnPaste.setOnClickListener(v -> {
            String test = binding.tvPaste.getText().toString();
            if (test.equals(getString(R.string.paste))) {
                try {
                    ClipData.Item item = myClipboard.getPrimaryClip().getItemAt(0);
                    String text = item.getText().toString();
                    binding.editTextUrl.setText(text);
                    binding.tvPaste.setText(getString(R.string.clear));
                } catch (Exception e) {
                    function.alertMessage(getString(R.string.nothing_paste));
                }
            } else {
                binding.editTextUrl.setText("");
                binding.tvPaste.setText(getString(R.string.paste));
            }
        });
        binding.btnDownload.setOnClickListener(v -> {
            if (!Objects.requireNonNull(binding.editTextUrl.getText()).toString().isEmpty()) {
                requestData(binding.editTextUrl.getText().toString());
            } else {
                function.alertMessage(getString(R.string.please_enter_url));
            }
        });
        binding.noData.btnTryAgain.setOnClickListener(v -> {
            binding.noData.conNoInternet.setVisibility(View.GONE);
            if (!Objects.requireNonNull(binding.editTextUrl.getText()).toString().isEmpty()) {
                requestData(binding.editTextUrl.getText().toString());
            } else {
                function.alertMessage(getString(R.string.please_enter_url));
            }
        });
        return view;
    }

    private void requestData(String id) {
        if (getActivity() != null) {
            if (function.isNetworkAvailable()) {
                getDownList(id);
            } else {
                function.alertMessage(activity.getString(R.string.internet_connection));
            }
        }
    }

    private void getDownList(String id) {
        if (activity != null) {
            function.showProgressDialog(requireActivity());
            downAdapter = null;
            downLists.clear();
            JsonObject jsObj = (JsonObject) new Gson().toJsonTree(new API(getActivity()));
            jsObj.addProperty(GodIsOne, OneDownload);
            jsObj.addProperty(VideoID, id);
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<DownloadRP> call = apiService.getDownloadList(API.toBase64(jsObj.toString()));
            call.enqueue(new Callback<>() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onResponse(@NotNull Call<DownloadRP> call, @NotNull Response<DownloadRP> response) {
                    if (activity != null) {
                        try {
                            DownloadRP downRP = response.body();
                            if (!Objects.requireNonNull(downRP).getTitle().equals("")) {
                                VideoDuration = downRP.getDuration();
                                VideoImage = downRP.getThumbnail();
                                VideoName = downRP.getTitle();
                                if (downRP.getDownloadLists().size() != 0) {
                                    downLists.addAll(downRP.getDownloadLists());
                                    binding.rvDownloader.setVisibility(View.VISIBLE);
                                } else {
                                    binding.rvDownloader.setVisibility(View.GONE);
                                    binding.noData.conNoInternet.setVisibility(View.GONE);
                                }

                                if (downAdapter == null) {
                                    if (downLists.size() != 0) {
                                        downAdapter = new DownloadAdapter(activity, downLists, DOWNLOADER, onClick);
                                        binding.rvDownloader.setAdapter(downAdapter);
                                        binding.rvDownloader.setLayoutAnimation(animation);
                                    }
                                } else {
                                    downAdapter.notifyDataSetChanged();
                                }
                            } else {
                                binding.noData.conNoInternet.setVisibility(View.VISIBLE);
                            }
                        } catch (Exception e) {
                            Log.d(EXCEPTION, e.toString());
                            function.alertMessage(activity.getString(R.string.failed));
                        }
                    }
                    function.stopDialog(2);
                }

                @Override
                public void onFailure(@NotNull Call<DownloadRP> call, @NotNull Throwable t) {
                    Log.e(FAIL, t.toString());
                    function.hideProgressDialog(activity);
                    function.alertMessage(activity.getString(R.string.failed));
                }
            });
        }
    }

    private void downloadTask(String url, String videoName) {
        if (!url.startsWith("http") || !url.startsWith("https")) {
            return;
        }
        try {
            File file = new File(Environment.getExternalStorageDirectory(), File.separator + Download + File.separator + Constant.VideoSavePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            File result = new File(file.getAbsolutePath() + File.separator + videoName);
            DownloadManager downloadManager = (DownloadManager) activity.getSystemService(Context.DOWNLOAD_SERVICE);
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
            request.allowScanningByMediaScanner();
            request.setTitle(videoName);
            request.setDestinationUri(Uri.fromFile(result));
            VideoDownloadedID = String.valueOf(Uri.fromFile(result));
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            if (downloadManager != null) {
                downloadManager.enqueue(request);
            }
            function.showToast(getString(R.string.start_downloading) + " : " + videoName);
            MediaScannerConnection.scanFile(activity, new String[]{result.toString()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                        }
                    });
        } catch (Exception e) {
            Log.e(">>>>>", e.toString());
        }
    }

    private final BroadcastReceiver onDownloadComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            function.showSuccessDialog(activity, VideoDownloadedID);
        }
    };

    @Override
    public void onDestroy() {
        activity.unregisterReceiver(onDownloadComplete);
        MainActivity.binding.toolbar.toolbarMain.setTitle(activity.getString(R.string.app_name));
        super.onDestroy();
    }
}