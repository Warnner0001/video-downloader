package com.videon.downloader.include;

import static android.os.Environment.DIRECTORY_DOWNLOADS;
import static com.videon.downloader.include.Constant.Download;
import static com.videon.downloader.include.Constant.InterstitialAdsId;
import static com.videon.downloader.include.Constant.NativeAdId;
import static com.videon.downloader.include.Constant.RewardAdId;
import static com.videon.downloader.include.Constant.BannerAdId;
import static com.videon.downloader.include.Constant.yourEmail;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.ads.MaxRewardedAd;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.applovin.sdk.AppLovinSdkUtils;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.videon.downloader.R;
import com.videon.downloader.interfaces.OnClick;
import com.vimalcvs.materialrating.MaterialFeedbackApp;
import com.vimalcvs.materialrating.MaterialRatingApp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Function {

    private final Activity activity;
    private final OnClick onClick;
    public static Handler handler;
    public static Runnable runnable;
    private int random;
    public static Dialog customDialog, successDialog;
    private MaxInterstitialAd interstitialAd;
    private MaxRewardedAd rewardedAd;
    public MaxAdView adView;
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd nativeAd;
    private FrameLayout nativeAdLayout;

    @SuppressLint("CommitPrefEdits")
    public Function(Activity activity, OnClick onClick) {
        this.activity = activity;
        this.onClick = onClick;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public static void showProgressDialog(Activity activity) {
        if (customDialog != null) {
            customDialog.dismiss();
            customDialog = null;
        }
        customDialog = new Dialog(activity);
        LayoutInflater inflater = LayoutInflater.from(activity);
        @SuppressLint("InflateParams") View mView = inflater.inflate(R.layout.progress_dialog, null);
        customDialog.setCancelable(false);
        customDialog.setContentView(mView);
        if (!customDialog.isShowing() && !activity.isFinishing()) {
            customDialog.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;
            customDialog.show();
        }
    }

    public void showSuccessDialog(Activity activity, String newVideoPath) {
        if (successDialog != null) {
            successDialog.dismiss();
            successDialog = null;
        }
        successDialog = new Dialog(activity);
        LayoutInflater inflater = LayoutInflater.from(activity);
        @SuppressLint("InflateParams") View mView = inflater.inflate(R.layout.success_dialog, null);
        ConstraintLayout conView = mView.findViewById(R.id.conView);
        ConstraintLayout conOk = mView.findViewById(R.id.conOk);
        conView.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(newVideoPath));
                intent.setDataAndType(Uri.parse(newVideoPath), "video/mp4");
                activity.startActivity(intent);
            } catch (Exception e) {
                showToast(activity.getString(R.string.no_app));
            }
        });
        conOk.setOnClickListener(v -> {
            successDialog.dismiss();
        });
        successDialog.setCancelable(false);
        successDialog.setContentView(mView);
        if (!successDialog.isShowing() && !activity.isFinishing()) {
            successDialog.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;
            successDialog.show();
        }
    }

    public void showHelpDialog(Activity activity) {
        if (successDialog != null) {
            successDialog.dismiss();
            successDialog = null;
        }
        successDialog = new Dialog(activity, R.style.Theme_Dialog);
        LayoutInflater inflater = LayoutInflater.from(activity);
        @SuppressLint("InflateParams") View mView = inflater.inflate(R.layout.instruction_dialog, null);
        ConstraintLayout conOk = mView.findViewById(R.id.conOk);
        conOk.setOnClickListener(v -> {
            successDialog.dismiss();
        });
        successDialog.setCancelable(false);
        successDialog.setContentView(mView);
        if (!successDialog.isShowing() && !activity.isFinishing()) {
            successDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            successDialog.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;
            successDialog.show();
        }
    }

    public static void hideProgressDialog(Activity activity) {
        if (customDialog != null && customDialog.isShowing() && !activity.isFinishing()) {
            customDialog.dismiss();
        }
    }

    public void setStatusBarGradiant(Activity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

    public void alertMessage(String message) {
        if (!activity.isFinishing()) {
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(activity, R.style.DialogTitleTextStyle);
            builder.setMessage(Html.fromHtml(message));
            builder.setCancelable(false);
            builder.setPositiveButton(activity.getString(R.string.ok),
                    (arg0, arg1) -> {
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    public void showToast(String toast) {
        Toast.makeText(activity, toast, Toast.LENGTH_SHORT).show();
    }

    public boolean isPackageInstalled(String packageName, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public void openWhatsApp(String number) {
        PackageManager pm = activity.getPackageManager();
        if (isPackageInstalled("com.whatsapp", pm)) {
            sendMessageToOwner(activity.getString(R.string.wp2) + " : " + activity.getString(R.string.app_name), "com.whatsapp", number);
        } else {
            if (isPackageInstalled("com.whatsapp.w4b", pm)) {
                sendMessageToOwner(activity.getString(R.string.wp2) + " : " + activity.getString(R.string.app_name), "com.whatsapp", number);
            } else {
                alertMessage(activity.getString(R.string.please_install_whatsapp));
            }
        }
    }

    public void openRating() {
        MaterialRatingApp materialRatingApp = new MaterialRatingApp(activity);
        materialRatingApp.showNow(((AppCompatActivity) activity).getSupportFragmentManager(), "");
    }

    public void openRatingFeedback() {
        MaterialFeedbackApp bottomSheetDialog = new MaterialFeedbackApp(yourEmail);
        bottomSheetDialog.showNow(((AppCompatActivity) activity).getSupportFragmentManager(), "");
    }

    public void shareApp() {
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, activity.getString(R.string.app_name));
            String sAux = "\n" + activity.getString(R.string.share_app_msg) + "\n\n";
            sAux = sAux + "https://play.google.com/store/apps/details?id=" + activity.getApplication().getPackageName();
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            activity.startActivity(Intent.createChooser(i, "choose one"));
        } catch (Exception e) {
            //e.toString();
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    public void sendMessageToOwner(String message, String packageName, String number) {
        PackageManager packageManager = activity.getPackageManager();
        Intent i = new Intent(Intent.ACTION_VIEW);
        try {
            String url = "https://api.whatsapp.com/send?phone=" + number + "&text=" + message;
            i.setPackage(packageName);
            i.setData(Uri.parse(url));
            if (i.resolveActivity(packageManager) != null) {
                activity.startActivity(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    public void openWebPage(String url) {
        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            activity.startActivity(browserIntent);
        } catch (Exception e) {
            alertMessage(activity.getString(R.string.failed));
        }
    }

    public void onDownClick(int position, String title, String type, String id) {
        showProgressDialog(activity);
        Constant.adCount = Constant.adCount + 1;
        if (Constant.adCount == Constant.adCountShow) {
            Constant.adCount = 0;
            if (generateAdsCode() >= 5) {
                interstitialAd = new MaxInterstitialAd(InterstitialAdsId, activity);
                interstitialAd.setListener(new MaxAdListener() {
                    @Override
                    public void onAdLoaded(MaxAd ad) {
                        interstitialAd.showAd(InterstitialAdsId);
                    }

                    @Override
                    public void onAdDisplayed(MaxAd ad) {

                    }

                    @Override
                    public void onAdHidden(MaxAd ad) {
                        // interstitialAd.loadAd();
                        hideProgressDialog(activity);
                        onClick.position(position, title, type, id);
                    }

                    @Override
                    public void onAdClicked(MaxAd ad) {

                    }

                    @Override
                    public void onAdLoadFailed(String adUnitId, MaxError error) {
                        // interstitialAd.loadAd();
                    }

                    @Override
                    public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                    }
                });
                interstitialAd.loadAd();
            } else {
                rewardedAd = MaxRewardedAd.getInstance(RewardAdId, activity);
                rewardedAd.setListener(new MaxRewardedAdListener() {
                    @Override
                    public void onRewardedVideoStarted(MaxAd ad) {

                    }

                    @Override
                    public void onRewardedVideoCompleted(MaxAd ad) {

                    }

                    @Override
                    public void onUserRewarded(MaxAd ad, MaxReward reward) {

                    }

                    @Override
                    public void onAdLoaded(MaxAd ad) {
                        rewardedAd.showAd(RewardAdId);
                    }

                    @Override
                    public void onAdDisplayed(MaxAd ad) {

                    }

                    @Override
                    public void onAdHidden(MaxAd ad) {
                        //rewardedAd.loadAd();
                        hideProgressDialog(activity);
                        onClick.position(position, title, type, id);
                    }

                    @Override
                    public void onAdClicked(MaxAd ad) {

                    }

                    @Override
                    public void onAdLoadFailed(String adUnitId, MaxError error) {
                        //rewardedAd.loadAd();
                    }

                    @Override
                    public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                    }
                });
                rewardedAd.loadAd();
            }
        } else {
            hideProgressDialog(activity);
            onClick.position(position, title, type, id);
        }
    }

    public void stopDialog(long time) {
        handler = new Handler();
        runnable = () -> {
            hideProgressDialog(activity);
            handler.removeCallbacks(runnable);
        };
        handler.postDelayed(runnable, (long) time * 1000);
    }

    public void startMediation() {
        AppLovinSdk.getInstance(activity).setMediationProvider("max");
        AppLovinSdk.initializeSdk(activity, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
                // AppLovin SDK is initialized, start loading ads

            }
        });
    }

    public void mediationBanner() {
        adView = new MaxAdView(BannerAdId, activity);
        adView.setListener(new MaxAdViewAdListener() {
            @Override
            public void onAdExpanded(MaxAd ad) {

            }

            @Override
            public void onAdCollapsed(MaxAd ad) {

            }

            @Override
            public void onAdLoaded(MaxAd ad) {

            }

            @Override
            public void onAdDisplayed(MaxAd ad) {

            }

            @Override
            public void onAdHidden(MaxAd ad) {

            }

            @Override
            public void onAdClicked(MaxAd ad) {

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {

            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {

            }
        });

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        final boolean isTablet = AppLovinSdkUtils.isTablet(activity);
        int heightPx = AppLovinSdkUtils.dpToPx(activity, isTablet ? 90 : 50);

        adView.setLayoutParams(new FrameLayout.LayoutParams(width, heightPx));
        final ViewGroup rootView = (ViewGroup) activity.findViewById(R.id.banner_ad_view);
        rootView.addView(adView);
        adView.loadAd();
    }

    public void destroyBanner() {
        adView.destroy();
    }

    public void loadNativeAdMediation() {
        FrameLayout nativeAdContainer = activity.findViewById(R.id.native_ad_layout);
        nativeAdLoader = new MaxNativeAdLoader(NativeAdId, activity);
        nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
            @Override
            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                // Clean up any pre-existing native ad to prevent memory leaks.
                if (nativeAd != null) {
                    nativeAdLoader.destroy(nativeAd);
                }

                // Save ad for cleanup.
                nativeAd = ad;

                // Add ad view to view.
                nativeAdContainer.removeAllViews();
                nativeAdContainer.addView(nativeAdView);
            }

            @Override
            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {
                // We recommend retrying with exponentially higher delays up to a maximum delay
                loadNativeAdMediation();
            }

            @Override
            public void onNativeAdClicked(final MaxAd ad) {
                // Optional click callback
            }
        });
        nativeAdLoader.loadAd();
    }

    public int generateAdsCode() {
        final int min = 1;
        final int max = 9;
        random = new Random().nextInt((max - min) + 1) + min;
        return random;
    }

    public static void downloader(Context context, String downloadURL, String path, String fileName) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> Toast.makeText(context, "" + context.getString(R.string.start_downloading), Toast.LENGTH_SHORT).show());
        String desc = context.getString(R.string.start_downloading);
        Uri Download_Uri = Uri.parse(downloadURL);
        DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Download_Uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setAllowedOverRoaming(true);
        request.setTitle(context.getString(R.string.app_name));
        request.setVisibleInDownloadsUi(true);
        request.setDescription(desc);
        request.setVisibleInDownloadsUi(true);
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(DIRECTORY_DOWNLOADS, path + fileName);
        dm.enqueue(request);
        mediaScanner(context, path, fileName);
    }

    public static void mediaScanner(Context context, String filePath, String fileName) {
        try {
            MediaScannerConnection.scanFile(context, new String[]{new File(DIRECTORY_DOWNLOADS + "/" + filePath + fileName).getAbsolutePath()},
                    null, (path, uri) -> {
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyFileInSavedDir(Context context, String sourceFile) {
        File file = new File(Environment.getExternalStorageDirectory(), File.separator + Download + File.separator + Constant.VideoSavePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String finalPath = file.getAbsolutePath();
        String pathWithName = finalPath + File.separator + new File(sourceFile).getName();
        Uri destUri = Uri.fromFile(new File(pathWithName));
        InputStream is = null;
        OutputStream os = null;
        try {
            Uri uri = Uri.parse(sourceFile);
            is = context.getContentResolver().openInputStream(uri);
            os = context.getContentResolver().openOutputStream(destUri, "w");

            byte[] buffer = new byte[1024];

            int length;
            while ((length = is.read(buffer)) > 0)
                os.write(buffer, 0, length);

            is.close();
            os.flush();
            os.close();

            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            intent.setData(destUri);
            context.sendBroadcast(intent);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static boolean isImageFile(String path) {
        String mimeType = URLConnection.guessContentTypeFromName(path);
        return mimeType != null && mimeType.startsWith("image");
    }

    public static boolean isVideoFile(String path) {
        String mimeType = URLConnection.guessContentTypeFromName(path);
        return mimeType != null && mimeType.startsWith("video");
    }

    public static void mShare(String filepath, Activity activity) {
        File fileToShare = new File(filepath);
        if (isImageFile(filepath)) {
            Intent share = new Intent(Intent.ACTION_SEND);
            share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            share.setType("image/*");
            Uri photoURI = FileProvider.getUriForFile(
                    activity.getApplicationContext(), activity.getApplicationContext()
                            .getPackageName() + ".provider", fileToShare);
            share.putExtra(Intent.EXTRA_STREAM,
                    photoURI);
            activity.startActivity(Intent.createChooser(share, "Share via"));

        } else if (isVideoFile(filepath)) {

            Uri videoURI = FileProvider.getUriForFile(activity.getApplicationContext(), activity.getApplicationContext()
                    .getPackageName() + ".provider", fileToShare);
            Intent videoshare = new Intent(Intent.ACTION_SEND);
            videoshare.setType("*/*");
            videoshare.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            videoshare.putExtra(Intent.EXTRA_STREAM, videoURI);

            activity.startActivity(videoshare);
        }

    }

    public static String getBack(String paramString1, String paramString2) {
        Matcher localMatcher = Pattern.compile(paramString2).matcher(paramString1);
        if (localMatcher.find()) {
            return localMatcher.group(1);
        }
        return "";
    }

    public String getWaFolder() {
        if (new File(Environment.getExternalStorageDirectory() + File.separator + "Android/media/com.whatsapp/WhatsApp" + File.separator + "Media" + File.separator + ".Statuses").isDirectory()) {
            return "Android%2Fmedia%2Fcom.whatsapp%2FWhatsApp%2FMedia%2F.Statuses";
        } else {
            return "WhatsApp%2FMedia%2F.Statuses";
        }
    }

    public String getWbFolder() {
        if (new File(Environment.getExternalStorageDirectory() + File.separator + "Android/media/com.whatsapp.w4b/WhatsApp Business" + File.separator + "Media" + File.separator + ".Statuses").isDirectory()) {
            return "Android%2Fmedia%2Fcom.whatsapp.w4b%2FWhatsApp Business%2FMedia%2F.Statuses";
        } else {
            return "WhatsApp Business%2FMedia%2F.Statuses";
        }
    }
}