// Generated by view binder compiler. Do not edit!
package com.videon.downloader.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.card.MaterialCardView;
import com.videon.downloader.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class SuccessDialogBinding implements ViewBinding {
  @NonNull
  private final MaterialCardView rootView;

  @NonNull
  public final LottieAnimationView animationView;

  @NonNull
  public final ConstraintLayout conOk;

  @NonNull
  public final ConstraintLayout conView;

  @NonNull
  public final TextView tv1;

  @NonNull
  public final View view1;

  private SuccessDialogBinding(@NonNull MaterialCardView rootView,
      @NonNull LottieAnimationView animationView, @NonNull ConstraintLayout conOk,
      @NonNull ConstraintLayout conView, @NonNull TextView tv1, @NonNull View view1) {
    this.rootView = rootView;
    this.animationView = animationView;
    this.conOk = conOk;
    this.conView = conView;
    this.tv1 = tv1;
    this.view1 = view1;
  }

  @Override
  @NonNull
  public MaterialCardView getRoot() {
    return rootView;
  }

  @NonNull
  public static SuccessDialogBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static SuccessDialogBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.success_dialog, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static SuccessDialogBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.animation_view;
      LottieAnimationView animationView = ViewBindings.findChildViewById(rootView, id);
      if (animationView == null) {
        break missingId;
      }

      id = R.id.conOk;
      ConstraintLayout conOk = ViewBindings.findChildViewById(rootView, id);
      if (conOk == null) {
        break missingId;
      }

      id = R.id.conView;
      ConstraintLayout conView = ViewBindings.findChildViewById(rootView, id);
      if (conView == null) {
        break missingId;
      }

      id = R.id.tv1;
      TextView tv1 = ViewBindings.findChildViewById(rootView, id);
      if (tv1 == null) {
        break missingId;
      }

      id = R.id.view1;
      View view1 = ViewBindings.findChildViewById(rootView, id);
      if (view1 == null) {
        break missingId;
      }

      return new SuccessDialogBinding((MaterialCardView) rootView, animationView, conOk, conView,
          tv1, view1);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
