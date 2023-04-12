package com.videon.downloader.fragment;

import static android.content.Context.CLIPBOARD_SERVICE;
import static com.videon.downloader.include.Constant.DOWNLOADER;
import static com.videon.downloader.include.Constant.Download;
import static com.videon.downloader.include.Constant.DownloadName;
import static com.videon.downloader.include.Constant.DownloadUrl;
import static com.videon.downloader.include.Constant.EXCEPTION;
import static com.videon.downloader.include.Constant.FAIL;
import static com.videon.downloader.include.Constant.GodIsOne;
import static com.videon.downloader.include.Constant.NativeAdId;
import static com.videon.downloader.include.Constant.OneDownload;
import static com.videon.downloader.include.Constant.VideoDownloadedID;
import static com.videon.downloader.include.Constant.VideoDownloader;
import static com.videon.downloader.include.Constant.VideoDuration;
import static com.videon.downloader.include.Constant.VideoID;
import static com.videon.downloader.include.Constant.VideoImage;
import static com.videon.downloader.include.Constant.VideoName;
import static com.videon.downloader.include.Constant.WebAddress;
import static com.videon.downloader.include.Constant.WebAddress2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.ClipData;
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
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.videon.downloader.R;
import com.videon.downloader.activity.MainActivity;
import com.videon.downloader.adapter.DownloadAdapter;
import com.videon.downloader.databinding.FragmentDownloadBinding;
import com.videon.downloader.databinding.FragmentDownloadsBinding;
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

public class DownloadsFragment extends Fragment {
    private Function function;
    private Activity activity;
    private FragmentDownloadsBinding binding;
    private OnClick onClick;
    private ClipboardManager myClipboard;

    private FrameLayout nativeAdLayout;
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd nativeAd;

    public DownloadsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDownloadsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        activity = requireActivity();
        activity.registerReceiver(onDownloadComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        String alphaNumeric = Calendar.getInstance().getTimeInMillis() + VideoDownloader;
        onClick = (position, title, type, id) -> {
            if (activity != null) {
                try {
                    downloadTask(id, alphaNumeric + "mp4");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        function = new Function(activity, onClick);
        function.startMediation();
        MainActivity.binding.toolbar.toolbarMain.setTitle(getString(R.string.facebook));
        myClipboard = (ClipboardManager) activity.getSystemService(CLIPBOARD_SERVICE);

        nativeAdLayout = view.findViewById(R.id.native_ad_layout);
        nativeAdLoader = new MaxNativeAdLoader(NativeAdId, activity);
        nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
            @Override
            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                if (nativeAd != null) {
                    nativeAdLoader.destroy(nativeAd);
                }
                nativeAd = ad;
                nativeAdLayout.removeAllViews();
                nativeAdLayout.addView(nativeAdView);
            }

            @Override
            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {
                //function.showToast("Load failed");
            }

            @Override
            public void onNativeAdClicked(final MaxAd ad) {

            }
        });
        nativeAdLoader.loadAd();
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
                    if (!text.contains(WebAddress) && !text.contains(WebAddress2)) {
                        function.alertMessage(getString(R.string.invalid_url));
                    } else {
                        binding.editTextUrl.setText(text);
                        binding.tvPaste.setText(getString(R.string.clear));
                    }
                } catch (Exception e) {
                    function.alertMessage(getString(R.string.nothing_paste));
                }
            } else {
                binding.editTextUrl.setText("");
                binding.tvPaste.setText(getString(R.string.paste));
            }
        });

        binding.btnDownload.setOnClickListener(v -> {
            String ids = Objects.requireNonNull(binding.editTextUrl.getText()).toString();
            if (!ids.isEmpty()) {
                if (!ids.contains(WebAddress) && !ids.contains(WebAddress2)) {
                    function.alertMessage(getString(R.string.invalid_url));
                } else {
                    Function.showProgressDialog(activity);
                    connectAPI(binding.editTextUrl.getText().toString());
                }
            } else {
                function.alertMessage(getString(R.string.please_enter_url));
            }
        });
        return view;
    }

    private void downloadTask(String url, String videoName) {
        if (!url.startsWith("http") || !url.startsWith("https")) {
            return;
        }
        try {
            File file = new File(Environment.getExternalStorageDirectory(), Download);
            if (!file.exists()) {
                file.mkdirs();
            }
            File result = new File(file.getAbsolutePath() + File.separator + Constant.VideoSavePath + File.separator + videoName);
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

    private void connectAPI(String video) {
        String videoUrl;
        try {
            videoUrl = URLEncoder.encode(video, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            videoUrl = video;
        }
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://facebook-reel-and-video-downloader.p.rapidapi.com/app/main.php?url=" + videoUrl)
                .get()
                .addHeader("X-RapidAPI-Key", Constant.RapidFBKey)
                .addHeader("X-RapidAPI-Host", "facebook-reel-and-video-downloader.p.rapidapi.com")
                .build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NonNull okhttp3.Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull okhttp3.Call call, @NonNull okhttp3.Response response) throws IOException {
                final String myResponse = Objects.requireNonNull(response.body()).string();
                activity.runOnUiThread(() -> {
                    try {
                        JSONObject json = new JSONObject(myResponse);
                        VideoImage = json.getString("thumbnail");
                        VideoName = json.getString("title");
                        VideoID = json.getJSONObject("links").getString("Download High Quality");
                        Glide.with(activity).load(VideoImage).placeholder(R.drawable.logo).into(binding.imageIcon);
                        binding.cardView.setVisibility(View.VISIBLE);
                        binding.videoDownload.setOnClickListener(view -> function.onDownClick(1, VideoID, VideoID, VideoID));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                });
                Function.hideProgressDialog(activity);
            }
        });
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