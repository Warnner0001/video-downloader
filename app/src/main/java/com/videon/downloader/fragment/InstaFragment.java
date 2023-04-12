package com.videon.downloader.fragment;

import static android.content.Context.CLIPBOARD_SERVICE;
import static com.videon.downloader.include.Constant.DownloadUrl;
import static com.videon.downloader.include.Constant.NativeAdId;
import static com.videon.downloader.include.Constant.WebAddress3;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.videon.downloader.R;
import com.videon.downloader.activity.MainActivity;
import com.videon.downloader.databinding.FragmentInstaBinding;
import com.videon.downloader.include.Function;
import com.videon.downloader.include.InsaverDownload;
import com.videon.downloader.interfaces.OnClick;

import java.util.Objects;

public class InstaFragment extends Fragment {
    private Function function;
    private Activity activity;
    private FragmentInstaBinding binding;
    private ClipboardManager myClipboard;
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd nativeAd;
    private FrameLayout nativeAdLayout;

    public InstaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInstaBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        activity = requireActivity();
        activity.registerReceiver(onDownloadComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        OnClick onClick = (position, title, type, id) -> {
            if (activity != null) {
                InsaverDownload.INSTANCE.startInstantDownload(id, activity);
            }
        };
        function = new Function(activity, onClick);
        function.startMediation();
        MainActivity.binding.toolbar.toolbarMain.setTitle(getResources().getString(R.string.instagram));
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
            requestData(DownloadUrl);
        }
        binding.btnPaste.setOnClickListener(v -> {
            String test = binding.tvPaste.getText().toString();
            if (test.equals(getString(R.string.paste))) {
                try {
                    ClipData.Item item = myClipboard.getPrimaryClip().getItemAt(0);
                    String text = item.getText().toString();
                    if (!text.contains(WebAddress3)) {
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
                if (!ids.contains(WebAddress3)) {
                    function.alertMessage(getString(R.string.invalid_url));
                } else {
                    requestData(binding.editTextUrl.getText().toString());
                }
            } else {
                function.alertMessage(getString(R.string.please_enter_url));
            }
        });
        return view;
    }

    private void requestData(String id) {
        if (getActivity() != null) {
            if (function.isNetworkAvailable()) {
                binding.btnDownload.setOnClickListener(view -> function.onDownClick(1, activity.getString(R.string.app_name), activity.getString(R.string.app_name), id));
            } else {
                function.alertMessage(activity.getString(R.string.internet_connection));
            }
        }
    }

    private final BroadcastReceiver onDownloadComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            function.showToast(activity.getString(R.string.complete_down));
        }
    };

    @Override
    public void onDestroy() {
        activity.unregisterReceiver(onDownloadComplete);
        MainActivity.binding.toolbar.toolbarMain.setTitle(activity.getString(R.string.app_name));
        super.onDestroy();
    }
}