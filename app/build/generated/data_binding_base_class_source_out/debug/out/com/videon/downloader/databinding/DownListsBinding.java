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
import com.google.android.material.textview.MaterialTextView;
import com.videon.downloader.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DownListsBinding implements ViewBinding {
  @NonNull
  private final MaterialCardView rootView;

  @NonNull
  public final MaterialCardView cardView;

  @NonNull
  public final ImageView imageIcon;

  @NonNull
  public final MaterialTextView tvDesc;

  @NonNull
  public final ConstraintLayout userLayout;

  @NonNull
  public final MaterialTextView vidName;

  private DownListsBinding(@NonNull MaterialCardView rootView, @NonNull MaterialCardView cardView,
      @NonNull ImageView imageIcon, @NonNull MaterialTextView tvDesc,
      @NonNull ConstraintLayout userLayout, @NonNull MaterialTextView vidName) {
    this.rootView = rootView;
    this.cardView = cardView;
    this.imageIcon = imageIcon;
    this.tvDesc = tvDesc;
    this.userLayout = userLayout;
    this.vidName = vidName;
  }

  @Override
  @NonNull
  public MaterialCardView getRoot() {
    return rootView;
  }

  @NonNull
  public static DownListsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DownListsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.down_lists, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DownListsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      MaterialCardView cardView = (MaterialCardView) rootView;

      id = R.id.imageIcon;
      ImageView imageIcon = ViewBindings.findChildViewById(rootView, id);
      if (imageIcon == null) {
        break missingId;
      }

      id = R.id.tvDesc;
      MaterialTextView tvDesc = ViewBindings.findChildViewById(rootView, id);
      if (tvDesc == null) {
        break missingId;
      }

      id = R.id.userLayout;
      ConstraintLayout userLayout = ViewBindings.findChildViewById(rootView, id);
      if (userLayout == null) {
        break missingId;
      }

      id = R.id.vidName;
      MaterialTextView vidName = ViewBindings.findChildViewById(rootView, id);
      if (vidName == null) {
        break missingId;
      }

      return new DownListsBinding((MaterialCardView) rootView, cardView, imageIcon, tvDesc,
          userLayout, vidName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
