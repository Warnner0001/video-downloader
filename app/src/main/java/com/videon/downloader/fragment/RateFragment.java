package com.videon.downloader.fragment;

import static com.videon.downloader.include.Constant.NativeAdId;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.videon.downloader.R;
import com.videon.downloader.activity.MainActivity;
import com.videon.downloader.databinding.FragmentDownloadersBinding;
import com.videon.downloader.databinding.FragmentRateBinding;
import com.videon.downloader.include.Function;
import com.videon.downloader.interfaces.OnClick;

public class RateFragment extends Fragment {
    private Function function;
    private Activity activity;
    private FrameLayout nativeAdLayout;
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd nativeAd;
    private FragmentRateBinding binding;

    public RateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentRateBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        activity = requireActivity();
        OnClick onClick = (position, title, type, id) -> {
            if (activity != null) {

            }
        };
        function = new Function(activity, onClick);
        MainActivity.binding.toolbar.toolbarMain.setTitle(activity.getString(R.string.rate_share));
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

        binding.tvRate.setOnClickListener(v -> {
            function.openRating();
        });
        binding.conWhatsApp.setOnClickListener(v -> {
            function.shareApp();
        });
        binding.tvFeedback.setOnClickListener(v -> {
            function.openRatingFeedback();
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        MainActivity.binding.toolbar.toolbarMain.setTitle(activity.getString(R.string.app_name));
        super.onDestroyView();
    }
}