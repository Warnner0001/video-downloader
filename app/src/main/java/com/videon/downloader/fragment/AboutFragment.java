package com.videon.downloader.fragment;

import static com.videon.downloader.include.Constant.NativeAdId;
import static com.videon.downloader.include.Constant.yourAbout;
import static com.videon.downloader.include.Constant.yourCompany;
import static com.videon.downloader.include.Constant.yourWhatsApp;
import static com.videon.downloader.include.Constant.yourWebsite;

import android.annotation.SuppressLint;
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
import com.google.android.material.textview.MaterialTextView;
import com.videon.downloader.BuildConfig;
import com.videon.downloader.R;
import com.videon.downloader.activity.MainActivity;
import com.videon.downloader.databinding.FragmentAboutBinding;
import com.videon.downloader.databinding.FragmentDownloadersBinding;
import com.videon.downloader.include.Constant;
import com.videon.downloader.include.Function;
import com.videon.downloader.interfaces.OnClick;

public class AboutFragment extends Fragment {
    private Function function;
    private Activity activity;
    private FrameLayout nativeAdLayout;
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd nativeAd;
    private FragmentAboutBinding binding;

    public AboutFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAboutBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        activity = requireActivity();
        OnClick onClick = (position, title, type, id) -> {
            if (activity != null) {

            }
        };
        function = new Function(activity, onClick);
        function.startMediation();
        MainActivity.binding.toolbar.toolbarMain.setTitle(getString(R.string.about_us));
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
        binding.tvB.setText(yourAbout);
        binding.tvDeveloper.setText(yourCompany);
        binding.tvAdmin.setText(yourCompany);
        binding.conWhatsApp.setOnClickListener(v -> {
            function.openWhatsApp(yourWhatsApp);
        });
        binding.conWebsite.setOnClickListener(v -> {
            function.openWebPage(yourWebsite);
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        MainActivity.binding.toolbar.toolbarMain.setTitle(activity.getString(R.string.app_name));
        super.onDestroyView();
    }
}