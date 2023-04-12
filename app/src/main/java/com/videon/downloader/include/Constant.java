package com.videon.downloader.include;

import android.os.Environment;

import java.io.File;

public class Constant {

    public static String OneSignalAppID = "enter_one_signal_key";
    public static String RapidFBKey = "enter_rapi_api_key";
    public static String yourWhatsApp = "+918307878848";
    public static String yourWebsite = "https://codeintra.com/store/krishna-apps";
    public static String yourVideoWebsite = "https://videoant.nulledsourcecode.com/";
    public static String yourEmail = "contact@krishnaapps.com";
    public static String yourCompany = "Krishna Apps Development";
    public static String yourAbout = "Hire us For Making Custom Android Studio Apps, Screenshots Design Work, App Reskin, Admin Panel Installation, Apps Submission on Play Store, App Manage etc.";
    public static String NativeAdId = "06bf4bbdfce0e494";
    public static String InterstitialAdsId = "ae5735c62d9c955d";
    public static String BannerAdId = "9c544018c39f6c5d";
    public static String RewardAdId = "aa4841e576499e98";

    public static String VideoSavePath = "KrishnaDownloader";


    /**
     * Do not change any parameters below of this line.
     */
	public static File whatsAppStatusFolder = new File(Environment.getExternalStorageDirectory(), File.separator + "Download" + File.separator + Constant.VideoSavePath);
    public static String VideoDownloader = "_KrishnaApps.";
    public static String Download = "Download";
    public static String DownloadUrl = "";
    public static String VideoDownloadedID = "VideoDownloadedID";
    public static String GodIsOne = "AUM";
    public static String DOWNLOADER = "listDownloader";
    public static String OneDownload = "OneDownload";
    public static String EXCEPTION = "exceptionError_";
    public static String FAIL = "failError_";
    public static String DownloadName = "DownloadName";
    public static String WebAddress = "facebook.com";
    public static String WebAddress2 = "fb.watch";
    public static String WebAddress3 = "instagram.com";
    public static String VideoID = "VideoID";
    public static String VideoName = "VideoName";
    public static String VideoImage = "VideoImage";
    public static String VideoDuration = "VideoDuration";
    public static String ONE = "1";
    public static String TRUE = "true";
    public static String FALSE = "false";
    public static int adCount = 0;
    public static int adCountShow = 2;
    public static boolean MEDIATION_INIT = false;
    public static String IS_TRENDING = "";
    public static String WhatsApp_API = "https://api.whatsapp.com/send?phone=";
}
