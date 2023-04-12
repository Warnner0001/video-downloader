package com.videon.downloader.fragment;

import static com.videon.downloader.include.Constant.NativeAdId;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.videon.downloader.R;
import com.videon.downloader.activity.MainActivity;
import com.videon.downloader.adapter.AppsAdapter;
import com.videon.downloader.databinding.FragmentMoreBinding;
import com.videon.downloader.include.Function;
import com.videon.downloader.interfaces.OnClick;
import com.videon.downloader.responses.AppsRP;

import java.util.ArrayList;

public class MoreFragment extends Fragment {
    private Function function;
    private Activity activity;
    private FrameLayout nativeAdLayout;
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd nativeAd;
    private FragmentMoreBinding binding;
    private ArrayList<AppsRP> appsRP;

    public MoreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMoreBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        activity = requireActivity();
        appsRP = new ArrayList<>();
        OnClick onClick = (position, title, type, id) -> {
            if (activity != null) {

            }
        };
        function = new Function(activity, onClick);
        MainActivity.binding.toolbar.toolbarMain.setTitle(activity.getString(R.string.more_apps));
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

        appsRP.add(new AppsRP("Affiliate App", "https://codecanyon.net/item/affiliator-all-in-one-affiliate-marketing-android-app-with-admin-panel-inapppurchase/43280940?s_rank=1", R.drawable.affiliate));
        appsRP.add(new AppsRP("Private Call", "https://codecanyon.net/item/yoohoo-anonymous-calling-android-app-source-code-admin-panel-website/38869312?s_rank=6", R.drawable.yoohoo));
        appsRP.add(new AppsRP("Instagram Downloader", "https://codeintra.com/krishna-instagram-downloader-android-studio/", R.drawable.insta));
        appsRP.add(new AppsRP("Sharechat Downloader", "https://codeintra.com/krishna-telegram-downloader-android-studio/", R.drawable.sharechat));
        appsRP.add(new AppsRP("Facebook Downloader", "https://codeintra.com/krishna-telegram-downloader-android-studio/", R.drawable.facebook));
        appsRP.add(new AppsRP("Likee Downloader", "https://codeintra.com/krishna-telegram-downloader-android-studio/", R.drawable.likee));
        appsRP.add(new AppsRP("Telegram Downloader", "https://codeintra.com/krishna-telegram-downloader-android-studio/", R.drawable.telegram));
        appsRP.add(new AppsRP("Pinterest Downloader", "https://codeintra.com/krishna-telegram-downloader-android-studio/", R.drawable.pinterest));
        appsRP.add(new AppsRP("VPN App", "https://codecanyon.net/item/krishna-vpn-pro-android-vpn-app-with-admin-panel-inapppurchase/43043827?s_rank=2", R.drawable.vpn_logo));
        appsRP.add(new AppsRP("TikTok", "https://apk.krishnaapps.com/TikMate_V1.apk", R.drawable.tiktok));

        AppsAdapter adapter = new AppsAdapter(appsRP, activity, onClick);
        GridLayoutManager layoutManager = new GridLayoutManager(activity, 2);
        binding.rvApps.setLayoutManager(layoutManager);
        binding.rvApps.setAdapter(adapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        MainActivity.binding.toolbar.toolbarMain.setTitle(activity.getString(R.string.app_name));
        super.onDestroyView();
    }
}