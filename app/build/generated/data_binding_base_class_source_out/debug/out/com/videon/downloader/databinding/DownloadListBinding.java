// Generated by view binder compiler. Do not edit!
package com.videon.downloader.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.videon.downloader.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DownloadListBinding implements ViewBinding {
  @NonNull
  private final MaterialCardView rootView;

  @NonNull
  public final MaterialCardView cardDownList;

  @NonNull
  public final ConstraintLayout conBtn;

  @NonNull
  public final ImageView imageIcon;

  @NonNull
  public final ImageView imgDelete;

  @NonNull
  public final ImageView imgPlay;

  @NonNull
  public final ImageView imgShare;

  private DownloadListBinding(@NonNull MaterialCardView rootView,
      @NonNull MaterialCardView cardDownList, @NonNull ConstraintLayout conBtn,
      @NonNull ImageView imageIcon, @NonNull ImageView imgDelete, @NonNull ImageView imgPlay,
      @NonNull ImageView imgShare) {
    this.rootView = rootView;
    this.cardDownList = cardDownList;
    this.conBtn = conBtn;
    this.imageIcon = imageIcon;
    this.imgDelete = imgDelete;
    this.imgPlay = imgPlay;
    this.imgShare = imgShare;
  }

  @Override
  @NonNull
  public MaterialCardView getRoot() {
    return rootView;
  }

  @NonNull
  public static DownloadListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DownloadListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.download_list, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DownloadListBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      MaterialCardView cardDownList = (MaterialCardView) rootView;

      id = R.id.conBtn;
      ConstraintLayout conBtn = ViewBindings.findChildViewById(rootView, id);
      if (conBtn == null) {
        break missingId;
      }

      id = R.id.imageIcon;
      ImageView imageIcon = ViewBindings.findChildViewById(rootView, id);
      if (imageIcon == null) {
        break missingId;
      }

      id = R.id.imgDelete;
      ImageView imgDelete = ViewBindings.findChildViewById(rootView, id);
      if (imgDelete == null) {
        break missingId;
      }

      id = R.id.imgPlay;
      ImageView imgPlay = ViewBindings.findChildViewById(rootView, id);
      if (imgPlay == null) {
        break missingId;
      }

      id = R.id.imgShare;
      ImageView imgShare = ViewBindings.findChildViewById(rootView, id);
      if (imgShare == null) {
        break missingId;
      }

      return new DownloadListBinding((MaterialCardView) rootView, cardDownList, conBtn, imageIcon,
          imgDelete, imgPlay, imgShare);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
