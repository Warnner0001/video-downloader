package com.videon.downloader.fragment;

import static com.videon.downloader.include.Constant.NativeAdId;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.videon.downloader.databinding.FragmentWhatsappBinding;
import com.videon.downloader.include.Constant;
import com.videon.downloader.include.Function;
import com.videon.downloader.interfaces.OnClick;

import java.util.Objects;

public class WhatsappFragment extends Fragment {
    private Function function;
    private Activity activity;
    private FrameLayout nativeAdLayout;
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd nativeAd;
    private String messageText;
    private FragmentWhatsappBinding binding;

    public WhatsappFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentWhatsappBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        activity = requireActivity();
        OnClick onClick = (position, title, type, id) -> {
            if (TextUtils.isEmpty(Objects.requireNonNull(binding.etNumber.getText()).toString())) {
                function.showToast(activity.getString(R.string.enter_number));
            } else {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(
                                Constant.WhatsApp_API + Objects.requireNonNull(binding.etNumber.getText()) + "&text=" + messageText
                        )));
            }
        };
        function = new Function(activity, onClick);
        function.startMediation();
        MainActivity.binding.toolbar.toolbarMain.setTitle(activity.getString(R.string.direct_whatsapp));
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

            }

            @Override
            public void onNativeAdClicked(final MaxAd ad) {

            }
        });
        nativeAdLoader.loadAd();

        binding.conBold.setOnClickListener(v -> {
            messageText = Objects.requireNonNull(binding.etMessage.getText()).toString();
            if (TextUtils.isEmpty(messageText)) {
                function.showToast(activity.getString(R.string.enter_message));
            } else if (messageText.startsWith("*") && messageText.endsWith("*")
                    || messageText.startsWith("_") && messageText.endsWith("_")
                    || messageText.startsWith("~") && messageText.endsWith("~")
                    || messageText.startsWith("```") && messageText.endsWith("```")) {
                function.showToast(activity.getString(R.string.already_applied));
            } else {
                messageText = "*" + messageText + "*";
                binding.etMessage.setText("");
                binding.etMessage.setText(messageText);
            }
        });
        binding.conItalic.setOnClickListener(v -> {
            messageText = Objects.requireNonNull(binding.etMessage.getText()).toString();
            if (TextUtils.isEmpty(messageText)) {
                function.showToast(activity.getString(R.string.enter_message));
            } else if (messageText.startsWith("*") && messageText.endsWith("*")
                    || messageText.startsWith("_") && messageText.endsWith("_")
                    || messageText.startsWith("~") && messageText.endsWith("~")
                    || messageText.startsWith("```") && messageText.endsWith("```")) {
                function.showToast(activity.getString(R.string.already_applied));
            } else {
                messageText = "_" + messageText + "_";
                binding.etMessage.setText("");
                binding.etMessage.setText(messageText);
            }
        });
        binding.conStrike.setOnClickListener(v -> {
            messageText = Objects.requireNonNull(binding.etMessage.getText()).toString();
            if (TextUtils.isEmpty(messageText)) {
                function.showToast(activity.getString(R.string.enter_message));
            } else if (messageText.startsWith("*") && messageText.endsWith("*")
                    || messageText.startsWith("_") && messageText.endsWith("_")
                    || messageText.startsWith("~") && messageText.endsWith("~")
                    || messageText.startsWith("```") && messageText.endsWith("```")) {
                function.showToast(activity.getString(R.string.already_applied));
            } else {
                messageText = "~" + messageText + "~";
                binding.etMessage.setText("");
                binding.etMessage.setText(messageText);
            }
        });
        binding.conMono.setOnClickListener(v -> {
            messageText = Objects.requireNonNull(binding.etMessage.getText()).toString();
            if (TextUtils.isEmpty(messageText)) {
                function.showToast(activity.getString(R.string.enter_message));
            } else if (messageText.startsWith("*") && messageText.endsWith("*")
                    || messageText.startsWith("_") && messageText.endsWith("_")
                    || messageText.startsWith("~") && messageText.endsWith("~")
                    || messageText.startsWith("```") && messageText.endsWith("```")) {
                function.showToast(activity.getString(R.string.already_applied));
            } else {
                messageText = "```" + messageText + "```";
                binding.etMessage.setText("");
                binding.etMessage.setText(messageText);
            }
        });
        binding.conWhatsApp.setOnClickListener(v -> function.onDownClick(1, activity.getString(R.string.app_name), activity.getString(R.string.app_name), activity.getString(R.string.app_name)));
        return view;
    }

    @Override
    public void onDestroyView() {
        MainActivity.binding.toolbar.toolbarMain.setTitle(activity.getString(R.string.app_name));
        super.onDestroyView();
    }
}