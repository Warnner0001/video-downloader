// Generated by view binder compiler. Do not edit!
package com.videon.downloader.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.videon.downloader.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentAboutBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final LottieAnimationView animationHelp;

  @NonNull
  public final ImageView appLogo;

  @NonNull
  public final MaterialCardView cardAdmin;

  @NonNull
  public final MaterialCardView cardAds;

  @NonNull
  public final MaterialCardView cardDeveloped;

  @NonNull
  public final ConstraintLayout conGetHelp;

  @NonNull
  public final ConstraintLayout conHome;

  @NonNull
  public final ConstraintLayout conMain;

  @NonNull
  public final ConstraintLayout conTop;

  @NonNull
  public final ConstraintLayout conWebsite;

  @NonNull
  public final ConstraintLayout conWhatsApp;

  @NonNull
  public final ConstraintLayout imgAdmin;

  @NonNull
  public final ImageView imgDeveloped;

  @NonNull
  public final NativeadLayoutBinding nativeAd;

  @NonNull
  public final NestedScrollView nestedScrollViewHome;

  @NonNull
  public final MaterialTextView tvA;

  @NonNull
  public final MaterialTextView tvAdmin;

  @NonNull
  public final MaterialTextView tvB;

  @NonNull
  public final MaterialTextView tvDeveloped;

  @NonNull
  public final MaterialTextView tvDeveloper;

  @NonNull
  public final MaterialTextView tvDownload;

  @NonNull
  public final View viewDeeveloped;

  private FragmentAboutBinding(@NonNull ConstraintLayout rootView,
      @NonNull LottieAnimationView animationHelp, @NonNull ImageView appLogo,
      @NonNull MaterialCardView cardAdmin, @NonNull MaterialCardView cardAds,
      @NonNull MaterialCardView cardDeveloped, @NonNull ConstraintLayout conGetHelp,
      @NonNull ConstraintLayout conHome, @NonNull ConstraintLayout conMain,
      @NonNull ConstraintLayout conTop, @NonNull ConstraintLayout conWebsite,
      @NonNull ConstraintLayout conWhatsApp, @NonNull ConstraintLayout imgAdmin,
      @NonNull ImageView imgDeveloped, @NonNull NativeadLayoutBinding nativeAd,
      @NonNull NestedScrollView nestedScrollViewHome, @NonNull MaterialTextView tvA,
      @NonNull MaterialTextView tvAdmin, @NonNull MaterialTextView tvB,
      @NonNull MaterialTextView tvDeveloped, @NonNull MaterialTextView tvDeveloper,
      @NonNull MaterialTextView tvDownload, @NonNull View viewDeeveloped) {
    this.rootView = rootView;
    this.animationHelp = animationHelp;
    this.appLogo = appLogo;
    this.cardAdmin = cardAdmin;
    this.cardAds = cardAds;
    this.cardDeveloped = cardDeveloped;
    this.conGetHelp = conGetHelp;
    this.conHome = conHome;
    this.conMain = conMain;
    this.conTop = conTop;
    this.conWebsite = conWebsite;
    this.conWhatsApp = conWhatsApp;
    this.imgAdmin = imgAdmin;
    this.imgDeveloped = imgDeveloped;
    this.nativeAd = nativeAd;
    this.nestedScrollViewHome = nestedScrollViewHome;
    this.tvA = tvA;
    this.tvAdmin = tvAdmin;
    this.tvB = tvB;
    this.tvDeveloped = tvDeveloped;
    this.tvDeveloper = tvDeveloper;
    this.tvDownload = tvDownload;
    this.viewDeeveloped = viewDeeveloped;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentAboutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentAboutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_about, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentAboutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.animationHelp;
      LottieAnimationView animationHelp = ViewBindings.findChildViewById(rootView, id);
      if (animationHelp == null) {
        break missingId;
      }

      id = R.id.app_logo;
      ImageView appLogo = ViewBindings.findChildViewById(rootView, id);
      if (appLogo == null) {
        break missingId;
      }

      id = R.id.cardAdmin;
      MaterialCardView cardAdmin = ViewBindings.findChildViewById(rootView, id);
      if (cardAdmin == null) {
        break missingId;
      }

      id = R.id.cardAds;
      MaterialCardView cardAds = ViewBindings.findChildViewById(rootView, id);
      if (cardAds == null) {
        break missingId;
      }

      id = R.id.cardDeveloped;
      MaterialCardView cardDeveloped = ViewBindings.findChildViewById(rootView, id);
      if (cardDeveloped == null) {
        break missingId;
      }

      id = R.id.conGetHelp;
      ConstraintLayout conGetHelp = ViewBindings.findChildViewById(rootView, id);
      if (conGetHelp == null) {
        break missingId;
      }

      id = R.id.conHome;
      ConstraintLayout conHome = ViewBindings.findChildViewById(rootView, id);
      if (conHome == null) {
        break missingId;
      }

      id = R.id.conMain;
      ConstraintLayout conMain = ViewBindings.findChildViewById(rootView, id);
      if (conMain == null) {
        break missingId;
      }

      id = R.id.conTop;
      ConstraintLayout conTop = ViewBindings.findChildViewById(rootView, id);
      if (conTop == null) {
        break missingId;
      }

      id = R.id.conWebsite;
      ConstraintLayout conWebsite = ViewBindings.findChildViewById(rootView, id);
      if (conWebsite == null) {
        break missingId;
      }

      id = R.id.conWhatsApp;
      ConstraintLayout conWhatsApp = ViewBindings.findChildViewById(rootView, id);
      if (conWhatsApp == null) {
        break missingId;
      }

      id = R.id.imgAdmin;
      ConstraintLayout imgAdmin = ViewBindings.findChildViewById(rootView, id);
      if (imgAdmin == null) {
        break missingId;
      }

      id = R.id.imgDeveloped;
      ImageView imgDeveloped = ViewBindings.findChildViewById(rootView, id);
      if (imgDeveloped == null) {
        break missingId;
      }

      id = R.id.native_ad;
      View nativeAd = ViewBindings.findChildViewById(rootView, id);
      if (nativeAd == null) {
        break missingId;
      }
      NativeadLayoutBinding binding_nativeAd = NativeadLayoutBinding.bind(nativeAd);

      id = R.id.nestedScrollView_home;
      NestedScrollView nestedScrollViewHome = ViewBindings.findChildViewById(rootView, id);
      if (nestedScrollViewHome == null) {
        break missingId;
      }

      id = R.id.tvA;
      MaterialTextView tvA = ViewBindings.findChildViewById(rootView, id);
      if (tvA == null) {
        break missingId;
      }

      id = R.id.tvAdmin;
      MaterialTextView tvAdmin = ViewBindings.findChildViewById(rootView, id);
      if (tvAdmin == null) {
        break missingId;
      }

      id = R.id.tvB;
      MaterialTextView tvB = ViewBindings.findChildViewById(rootView, id);
      if (tvB == null) {
        break missingId;
      }

      id = R.id.tvDeveloped;
      MaterialTextView tvDeveloped = ViewBindings.findChildViewById(rootView, id);
      if (tvDeveloped == null) {
        break missingId;
      }

      id = R.id.tvDeveloper;
      MaterialTextView tvDeveloper = ViewBindings.findChildViewById(rootView, id);
      if (tvDeveloper == null) {
        break missingId;
      }

      id = R.id.tvDownload;
      MaterialTextView tvDownload = ViewBindings.findChildViewById(rootView, id);
      if (tvDownload == null) {
        break missingId;
      }

      id = R.id.viewDeeveloped;
      View viewDeeveloped = ViewBindings.findChildViewById(rootView, id);
      if (viewDeeveloped == null) {
        break missingId;
      }

      return new FragmentAboutBinding((ConstraintLayout) rootView, animationHelp, appLogo,
          cardAdmin, cardAds, cardDeveloped, conGetHelp, conHome, conMain, conTop, conWebsite,
          conWhatsApp, imgAdmin, imgDeveloped, binding_nativeAd, nestedScrollViewHome, tvA, tvAdmin,
          tvB, tvDeveloped, tvDeveloper, tvDownload, viewDeeveloped);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}