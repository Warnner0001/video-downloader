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
import com.google.android.material.button.MaterialButton;
import com.videon.downloader.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class NoDataFoundBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialButton btnTryAgain;

  @NonNull
  public final ConstraintLayout conNoInternet;

  @NonNull
  public final ImageView imgError;

  private NoDataFoundBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialButton btnTryAgain, @NonNull ConstraintLayout conNoInternet,
      @NonNull ImageView imgError) {
    this.rootView = rootView;
    this.btnTryAgain = btnTryAgain;
    this.conNoInternet = conNoInternet;
    this.imgError = imgError;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static NoDataFoundBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static NoDataFoundBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.no_data_found, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static NoDataFoundBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnTryAgain;
      MaterialButton btnTryAgain = ViewBindings.findChildViewById(rootView, id);
      if (btnTryAgain == null) {
        break missingId;
      }

      ConstraintLayout conNoInternet = (ConstraintLayout) rootView;

      id = R.id.imgError;
      ImageView imgError = ViewBindings.findChildViewById(rootView, id);
      if (imgError == null) {
        break missingId;
      }

      return new NoDataFoundBinding((ConstraintLayout) rootView, btnTryAgain, conNoInternet,
          imgError);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
