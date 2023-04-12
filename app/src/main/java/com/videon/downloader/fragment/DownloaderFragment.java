package com.videon.downloader.fragment;

import static com.videon.downloader.include.Constant.DOWNLOADER;
import static com.videon.downloader.include.Constant.DownloadName;
import static com.videon.downloader.include.Constant.EXCEPTION;
import static com.videon.downloader.include.Constant.FAIL;
import static com.videon.downloader.include.Constant.GodIsOne;
import static com.videon.downloader.include.Constant.NativeAdId;
import static com.videon.downloader.include.Constant.ONE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.FrameLayout;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.videon.downloader.R;
import com.videon.downloader.activity.MainActivity;
import com.videon.downloader.adapter.DownAdapter;
import com.videon.downloader.databinding.FragmentDownloadersBinding;
import com.videon.downloader.include.API;
import com.videon.downloader.include.Constant;
import com.videon.downloader.include.Function;
import com.videon.downloader.interfaces.OnClick;
import com.videon.downloader.list.DownList;
import com.videon.downloader.responses.DownRP;
import com.videon.downloader.rest.ApiClient;
import com.videon.downloader.rest.ApiInterface;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownloaderFragment extends Fragment {
    private Function function;
    private OnClick onClick;
    private Activity activity;
    private List<DownList> downLists;
    private DownAdapter downAdapter;
    private LayoutAnimationController animation;
    private FrameLayout nativeAdLayout;
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd nativeAd;
    private FragmentDownloadersBinding binding;

    public DownloaderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDownloadersBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("trending");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    Constant.IS_TRENDING = dataSnapshot.getValue(String.class);
                } catch (Exception e) {
                    Log.w(FAIL, "Failed to read value.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(EXCEPTION, "Exception to read value.", error.toException());
            }
        });

        downLists = new ArrayList<>();
        activity = requireActivity();

        onClick = (position, title, type, id) -> {
            if (getActivity() != null) {
                if (title.equals(getString(R.string.facebook))) {
                    requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new DownloadsFragment(), getResources().getString(R.string.app_name)).addToBackStack(getResources().getString(R.string.app_name)).commitAllowingStateLoss();
                } else if (title.equals(getString(R.string.instagram))) {
                    requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new InstaFragment(), getResources().getString(R.string.app_name)).addToBackStack(getResources().getString(R.string.app_name)).commitAllowingStateLoss();
                } else if (title.equals(getString(R.string.quote))) {
                    requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new QuoteFragment(), getResources().getString(R.string.app_name)).addToBackStack(getResources().getString(R.string.app_name)).commitAllowingStateLoss();
                } else if (title.equals(getString(R.string.whatsapp_mes))) {
                    requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new WhatsappFragment(), getResources().getString(R.string.app_name)).addToBackStack(getResources().getString(R.string.app_name)).commitAllowingStateLoss();
                } else if (title.equals(getString(R.string.whatsapp_me))) {
                    requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new WaFragment(), getResources().getString(R.string.app_name)).addToBackStack(getResources().getString(R.string.app_name)).commitAllowingStateLoss();
                } else if (title.equals(getString(R.string.whatsapp_me_b))) {
                    requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new WbFragment(), getResources().getString(R.string.app_name)).addToBackStack(getResources().getString(R.string.app_name)).commitAllowingStateLoss();
                } else if (title.equals(getString(R.string.view_gallery))) {
                    requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new GalleryVideoFragment(), getResources().getString(R.string.app_name)).addToBackStack(getResources().getString(R.string.app_name)).commitAllowingStateLoss();
                } else if (title.equals(getString(R.string.youtube))) {
                    DownloadFragment downloadFragment = new DownloadFragment();
                    requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, downloadFragment, title).addToBackStack(title).commitAllowingStateLoss();
                } else if (title.equals(getString(R.string.tiktok))) {
                    DownloadFragment downloadFragment = new DownloadFragment();
                    requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, downloadFragment, title).addToBackStack(title).commitAllowingStateLoss();
                } else {
                    DownloadName = title;
                    DownloadFragment downloadFragment = new DownloadFragment();
                    requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, downloadFragment, title).addToBackStack(title).commitAllowingStateLoss();
                }
            }
        };
        function = new Function(requireActivity(), onClick);
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
        int resId = R.anim.layout_animation_fall_down;
        animation = AnimationUtils.loadLayoutAnimation(getActivity(), resId);
        binding.rvDownloader.setLayoutManager(new GridLayoutManager(activity, 3));
        binding.rvDownloader.setFocusable(false);
        binding.noData.btnTryAgain.setOnClickListener(v -> {
            binding.noData.conNoInternet.setVisibility(View.GONE);
            requestData();
        });
        binding.cardFb.setOnClickListener(v -> function.onDownClick(1, activity.getString(R.string.facebook), activity.getString(R.string.app_name), activity.getString(R.string.app_name)));
        binding.cardInsta.setOnClickListener(v -> function.onDownClick(1, activity.getString(R.string.instagram), activity.getString(R.string.app_name), activity.getString(R.string.app_name)));
        binding.conQuote.setOnClickListener(v -> function.onDownClick(1, activity.getString(R.string.quote), activity.getString(R.string.app_name), activity.getString(R.string.app_name)));
        binding.conDirect.setOnClickListener(v -> function.onDownClick(1, activity.getString(R.string.whatsapp_mes), activity.getString(R.string.app_name), activity.getString(R.string.app_name)));
        binding.conWa.setOnClickListener(v -> function.onDownClick(1, activity.getString(R.string.whatsapp_me), activity.getString(R.string.app_name), activity.getString(R.string.app_name)));
        binding.conWb.setOnClickListener(v -> function.onDownClick(1, activity.getString(R.string.whatsapp_me_b), activity.getString(R.string.app_name), activity.getString(R.string.app_name)));
        binding.cardYt.setOnClickListener(v -> function.onDownClick(1, activity.getString(R.string.youtube), activity.getString(R.string.app_name), activity.getString(R.string.app_name)));
        binding.cardTiktok.setOnClickListener(v -> function.onDownClick(1, activity.getString(R.string.tiktok), activity.getString(R.string.app_name), activity.getString(R.string.app_name)));
        binding.conGallery.setOnClickListener(v -> function.onDownClick(1, activity.getString(R.string.view_gallery), activity.getString(R.string.app_name), activity.getString(R.string.app_name)));
        return view;
    }

    private void requestData() {
        if (activity != null) {
            if (function.isNetworkAvailable()) {
                getDownList();
            } else {
                function.alertMessage(activity.getString(R.string.internet_connection));
            }
        }
    }

    private void getDownList() {
        if (activity != null) {
            Function.showProgressDialog(requireActivity());
            downAdapter = null;
            downLists.clear();
            JsonObject jsObj = (JsonObject) new Gson().toJsonTree(new API(getActivity()));
            jsObj.addProperty(GodIsOne, DOWNLOADER);
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<DownRP> call = apiService.getDownList(API.toBase64(jsObj.toString()));
            call.enqueue(new Callback<>() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onResponse(@NotNull Call<DownRP> call, @NotNull Response<DownRP> response) {
                    if (activity != null) {
                        try {
                            DownRP downRP = response.body();
                            if (Objects.requireNonNull(downRP).getStatus().equals(ONE)) {
                                if (downRP.downLists().size() != 0) {
                                    downLists.addAll(downRP.downLists());
                                    binding.nestedScrollView.setVisibility(View.VISIBLE);
                                    if (!Constant.IS_TRENDING.equals("show")) {
                                        binding.conTrend.setVisibility(View.VISIBLE);
                                    }
                                }

                                if (downAdapter == null) {
                                    if (downLists.size() == 0) {
                                        binding.nestedScrollView.setVisibility(View.GONE);
                                        binding.noData.conNoInternet.setVisibility(View.VISIBLE);
                                    } else {
                                        downAdapter = new DownAdapter(getActivity(), downLists, DOWNLOADER, onClick);
                                        binding.rvDownloader.setAdapter(downAdapter);
                                        binding.rvDownloader.setLayoutAnimation(animation);
                                    }
                                } else {
                                    downAdapter.notifyDataSetChanged();
                                }
                            } else {
                                function.showToast(downRP.getMessage());
                                binding.nestedScrollView.setVisibility(View.GONE);
                                binding.noData.conNoInternet.setVisibility(View.VISIBLE);
                            }
                        } catch (Exception e) {
                            Log.d(EXCEPTION + DOWNLOADER, e.toString());
                            function.showToast(activity.getString(R.string.failed) + "\n" + e);
                        }
                    }
                    function.stopDialog(2);
                }

                @Override
                public void onFailure(@NotNull Call<DownRP> call, @NotNull Throwable t) {
                    Log.e(FAIL + DOWNLOADER, t.toString());
                    Function.hideProgressDialog(activity);
                    function.showToast(activity.getString(R.string.failed) + "\n" + t);
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        requestData();
    }

    @Override
    public void onDestroyView() {
        MainActivity.binding.toolbar.toolbarMain.setTitle(activity.getString(R.string.app_name));
        super.onDestroyView();
    }

}