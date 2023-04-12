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
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.videon.downloader.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentDownloadersBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView appLogo;

  @NonNull
  public final MaterialCardView cardFb;

  @NonNull
  public final MaterialCardView cardInsta;

  @NonNull
  public final ConstraintLayout cardLogo;

  @NonNull
  public final MaterialCardView cardTiktok;

  @NonNull
  public final ConstraintLayout cardTrending;

  @NonNull
  public final MaterialCardView cardView;

  @NonNull
  public final MaterialCardView cardYt;

  @NonNull
  public final ConstraintLayout conDirect;

  @NonNull
  public final ConstraintLayout conGallery;

  @NonNull
  public final ConstraintLayout conHome;

  @NonNull
  public final ConstraintLayout conMotivate;

  @NonNull
  public final ConstraintLayout conQuote;

  @NonNull
  public final ConstraintLayout conRecycler;

  @NonNull
  public final ConstraintLayout conTrend;

  @NonNull
  public final ConstraintLayout conWa;

  @NonNull
  public final ConstraintLayout conWb;

  @NonNull
  public final ImageView imageFb;

  @NonNull
  public final ImageView imageInsta;

  @NonNull
  public final ImageView imageTiktok;

  @NonNull
  public final ImageView imageYt;

  @NonNull
  public final NativeadLayoutBinding nativeAd;

  @NonNull
  public final NestedScrollView nestedScrollView;

  @NonNull
  public final NoDataFoundBinding noData;

  @NonNull
  public final RecyclerView rvDownloader;

  @NonNull
  public final MaterialTextView tvAppName;

  @NonNull
  public final MaterialTextView tvFb;

  @NonNull
  public final MaterialTextView tvInsta;

  @NonNull
  public final MaterialTextView tvTiktok;

  @NonNull
  public final MaterialTextView tvTrend;

  @NonNull
  public final MaterialTextView tvYt;

  private FragmentDownloadersBinding(@NonNull ConstraintLayout rootView, @NonNull ImageView appLogo,
      @NonNull MaterialCardView cardFb, @NonNull MaterialCardView cardInsta,
      @NonNull ConstraintLayout cardLogo, @NonNull MaterialCardView cardTiktok,
      @NonNull ConstraintLayout cardTrending, @NonNull MaterialCardView cardView,
      @NonNull MaterialCardView cardYt, @NonNull ConstraintLayout conDirect,
      @NonNull ConstraintLayout conGallery, @NonNull ConstraintLayout conHome,
      @NonNull ConstraintLayout conMotivate, @NonNull ConstraintLayout conQuote,
      @NonNull ConstraintLayout conRecycler, @NonNull ConstraintLayout conTrend,
      @NonNull ConstraintLayout conWa, @NonNull ConstraintLayout conWb, @NonNull ImageView imageFb,
      @NonNull ImageView imageInsta, @NonNull ImageView imageTiktok, @NonNull ImageView imageYt,
      @NonNull NativeadLayoutBinding nativeAd, @NonNull NestedScrollView nestedScrollView,
      @NonNull NoDataFoundBinding noData, @NonNull RecyclerView rvDownloader,
      @NonNull MaterialTextView tvAppName, @NonNull MaterialTextView tvFb,
      @NonNull MaterialTextView tvInsta, @NonNull MaterialTextView tvTiktok,
      @NonNull MaterialTextView tvTrend, @NonNull MaterialTextView tvYt) {
    this.rootView = rootView;
    this.appLogo = appLogo;
    this.cardFb = cardFb;
    this.cardInsta = cardInsta;
    this.cardLogo = cardLogo;
    this.cardTiktok = cardTiktok;
    this.cardTrending = cardTrending;
    this.cardView = cardView;
    this.cardYt = cardYt;
    this.conDirect = conDirect;
    this.conGallery = conGallery;
    this.conHome = conHome;
    this.conMotivate = conMotivate;
    this.conQuote = conQuote;
    this.conRecycler = conRecycler;
    this.conTrend = conTrend;
    this.conWa = conWa;
    this.conWb = conWb;
    this.imageFb = imageFb;
    this.imageInsta = imageInsta;
    this.imageTiktok = imageTiktok;
    this.imageYt = imageYt;
    this.nativeAd = nativeAd;
    this.nestedScrollView = nestedScrollView;
    this.noData = noData;
    this.rvDownloader = rvDownloader;
    this.tvAppName = tvAppName;
    this.tvFb = tvFb;
    this.tvInsta = tvInsta;
    this.tvTiktok = tvTiktok;
    this.tvTrend = tvTrend;
    this.tvYt = tvYt;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentDownloadersBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentDownloadersBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_downloaders, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentDownloadersBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.app_logo;
      ImageView appLogo = ViewBindings.findChildViewById(rootView, id);
      if (appLogo == null) {
        break missingId;
      }

      id = R.id.cardFb;
      MaterialCardView cardFb = ViewBindings.findChildViewById(rootView, id);
      if (cardFb == null) {
        break missingId;
      }

      id = R.id.cardInsta;
      MaterialCardView cardInsta = ViewBindings.findChildViewById(rootView, id);
      if (cardInsta == null) {
        break missingId;
      }

      id = R.id.cardLogo;
      ConstraintLayout cardLogo = ViewBindings.findChildViewById(rootView, id);
      if (cardLogo == null) {
        break missingId;
      }

      id = R.id.cardTiktok;
      MaterialCardView cardTiktok = ViewBindings.findChildViewById(rootView, id);
      if (cardTiktok == null) {
        break missingId;
      }

      id = R.id.cardTrending;
      ConstraintLayout cardTrending = ViewBindings.findChildViewById(rootView, id);
      if (cardTrending == null) {
        break missingId;
      }

      id = R.id.cardView;
      MaterialCardView cardView = ViewBindings.findChildViewById(rootView, id);
      if (cardView == null) {
        break missingId;
      }

      id = R.id.cardYt;
      MaterialCardView cardYt = ViewBindings.findChildViewById(rootView, id);
      if (cardYt == null) {
        break missingId;
      }

      id = R.id.conDirect;
      ConstraintLayout conDirect = ViewBindings.findChildViewById(rootView, id);
      if (conDirect == null) {
        break missingId;
      }

      id = R.id.conGallery;
      ConstraintLayout conGallery = ViewBindings.findChildViewById(rootView, id);
      if (conGallery == null) {
        break missingId;
      }

      id = R.id.conHome;
      ConstraintLayout conHome = ViewBindings.findChildViewById(rootView, id);
      if (conHome == null) {
        break missingId;
      }

      id = R.id.conMotivate;
      ConstraintLayout conMotivate = ViewBindings.findChildViewById(rootView, id);
      if (conMotivate == null) {
        break missingId;
      }

      id = R.id.conQuote;
      ConstraintLayout conQuote = ViewBindings.findChildViewById(rootView, id);
      if (conQuote == null) {
        break missingId;
      }

      id = R.id.conRecycler;
      ConstraintLayout conRecycler = ViewBindings.findChildViewById(rootView, id);
      if (conRecycler == null) {
        break missingId;
      }

      id = R.id.conTrend;
      ConstraintLayout conTrend = ViewBindings.findChildViewById(rootView, id);
      if (conTrend == null) {
        break missingId;
      }

      id = R.id.conWa;
      ConstraintLayout conWa = ViewBindings.findChildViewById(rootView, id);
      if (conWa == null) {
        break missingId;
      }

      id = R.id.conWb;
      ConstraintLayout conWb = ViewBindings.findChildViewById(rootView, id);
      if (conWb == null) {
        break missingId;
      }

      id = R.id.imageFb;
      ImageView imageFb = ViewBindings.findChildViewById(rootView, id);
      if (imageFb == null) {
        break missingId;
      }

      id = R.id.imageInsta;
      ImageView imageInsta = ViewBindings.findChildViewById(rootView, id);
      if (imageInsta == null) {
        break missingId;
      }

      id = R.id.imageTiktok;
      ImageView imageTiktok = ViewBindings.findChildViewById(rootView, id);
      if (imageTiktok == null) {
        break missingId;
      }

      id = R.id.imageYt;
      ImageView imageYt = ViewBindings.findChildViewById(rootView, id);
      if (imageYt == null) {
        break missingId;
      }

      id = R.id.native_ad;
      View nativeAd = ViewBindings.findChildViewById(rootView, id);
      if (nativeAd == null) {
        break missingId;
      }
      NativeadLayoutBinding binding_nativeAd = NativeadLayoutBinding.bind(nativeAd);

      id = R.id.nestedScrollView;
      NestedScrollView nestedScrollView = ViewBindings.findChildViewById(rootView, id);
      if (nestedScrollView == null) {
        break missingId;
      }

      id = R.id.noData;
      View noData = ViewBindings.findChildViewById(rootView, id);
      if (noData == null) {
        break missingId;
      }
      NoDataFoundBinding binding_noData = NoDataFoundBinding.bind(noData);

      id = R.id.rvDownloader;
      RecyclerView rvDownloader = ViewBindings.findChildViewById(rootView, id);
      if (rvDownloader == null) {
        break missingId;
      }

      id = R.id.tvAppName;
      MaterialTextView tvAppName = ViewBindings.findChildViewById(rootView, id);
      if (tvAppName == null) {
        break missingId;
      }

      id = R.id.tvFb;
      MaterialTextView tvFb = ViewBindings.findChildViewById(rootView, id);
      if (tvFb == null) {
        break missingId;
      }

      id = R.id.tvInsta;
      MaterialTextView tvInsta = ViewBindings.findChildViewById(rootView, id);
      if (tvInsta == null) {
        break missingId;
      }

      id = R.id.tvTiktok;
      MaterialTextView tvTiktok = ViewBindings.findChildViewById(rootView, id);
      if (tvTiktok == null) {
        break missingId;
      }

      id = R.id.tvTrend;
      MaterialTextView tvTrend = ViewBindings.findChildViewById(rootView, id);
      if (tvTrend == null) {
        break missingId;
      }

      id = R.id.tvYt;
      MaterialTextView tvYt = ViewBindings.findChildViewById(rootView, id);
      if (tvYt == null) {
        break missingId;
      }

      return new FragmentDownloadersBinding((ConstraintLayout) rootView, appLogo, cardFb, cardInsta,
          cardLogo, cardTiktok, cardTrending, cardView, cardYt, conDirect, conGallery, conHome,
          conMotivate, conQuote, conRecycler, conTrend, conWa, conWb, imageFb, imageInsta,
          imageTiktok, imageYt, binding_nativeAd, nestedScrollView, binding_noData, rvDownloader,
          tvAppName, tvFb, tvInsta, tvTiktok, tvTrend, tvYt);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}