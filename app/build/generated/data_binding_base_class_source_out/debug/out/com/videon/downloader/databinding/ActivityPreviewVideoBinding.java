// Generated by view binder compiler. Do not edit!
package com.videon.downloader.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.videon.downloader.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityPreviewVideoBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final BannerAdBinding bannerAd;

  @NonNull
  public final RowToolbarBinding toolbar;

  @NonNull
  public final VideoView videoPlayer;

  private ActivityPreviewVideoBinding(@NonNull ConstraintLayout rootView,
      @NonNull BannerAdBinding bannerAd, @NonNull RowToolbarBinding toolbar,
      @NonNull VideoView videoPlayer) {
    this.rootView = rootView;
    this.bannerAd = bannerAd;
    this.toolbar = toolbar;
    this.videoPlayer = videoPlayer;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityPreviewVideoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityPreviewVideoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_preview_video, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityPreviewVideoBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bannerAd;
      View bannerAd = ViewBindings.findChildViewById(rootView, id);
      if (bannerAd == null) {
        break missingId;
      }
      BannerAdBinding binding_bannerAd = BannerAdBinding.bind(bannerAd);

      id = R.id.toolbar;
      View toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }
      RowToolbarBinding binding_toolbar = RowToolbarBinding.bind(toolbar);

      id = R.id.videoPlayer;
      VideoView videoPlayer = ViewBindings.findChildViewById(rootView, id);
      if (videoPlayer == null) {
        break missingId;
      }

      return new ActivityPreviewVideoBinding((ConstraintLayout) rootView, binding_bannerAd,
          binding_toolbar, videoPlayer);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}