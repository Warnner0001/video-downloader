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

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.videon.downloader.R;
import com.videon.downloader.activity.MainActivity;
import com.videon.downloader.databinding.FragmentQuoteBinding;
import com.videon.downloader.include.Function;
import com.videon.downloader.include.QuotesGenerator;
import com.videon.downloader.interfaces.OnClick;

public class QuoteFragment extends Fragment {
    private Activity activity;
    private FrameLayout nativeAdLayout;
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd nativeAd;
    private FragmentQuoteBinding binding;

    QuotesGenerator quotesGenerator;
    QuotesGenerator.Quote quote;

    public QuoteFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentQuoteBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        activity = requireActivity();
        quotesGenerator = new QuotesGenerator(getResources().openRawResource(R.raw.quotes));
        OnClick onClick = (position, title, type, id) -> loadQuote();
        Function function = new Function(activity, onClick);
        function.startMediation();
        MainActivity.binding.toolbar.toolbarMain.setTitle(activity.getString(R.string.daily_quote));
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
        loadQuote();

        binding.conQuote.setOnClickListener(v -> function.onDownClick(1, activity.getString(R.string.app_name), activity.getString(R.string.app_name), activity.getString(R.string.app_name)));
        return view;
    }

    private void loadQuote() {
        quote = quotesGenerator.getRandomQuote();
        binding.tvQuote.setText(quote.getText());
        binding.tvAuthor.setText(quote.getAuthor());
    }

    @Override
    public void onDestroyView() {
        MainActivity.binding.toolbar.toolbarMain.setTitle(activity.getString(R.string.app_name));
        super.onDestroyView();
    }
}