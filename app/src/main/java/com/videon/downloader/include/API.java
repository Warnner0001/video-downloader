package com.videon.downloader.include;

import android.app.Activity;
import android.content.Context;
import android.util.Base64;
import com.google.gson.annotations.SerializedName;
import com.videon.downloader.BuildConfig;

public class API {

    @SerializedName("kay")
    private final String kay;
    @SerializedName("pkId")
    private final String pkId;
    @SerializedName("user")
    private final String user;

    public API(Activity activity) {
        user = BuildConfig.BUYER_NAME;
        pkId = BuildConfig.APPLICATION_ID;
        kay = BuildConfig.LICENSE_KEY;
    }

    public API(Context context) {
        user = BuildConfig.BUYER_NAME;
        pkId = BuildConfig.APPLICATION_ID;
        kay = BuildConfig.LICENSE_KEY;
    }

    public static String toBase64(String input) {
        byte[] encodeValue = Base64.encode(input.getBytes(), Base64.DEFAULT);
        return new String(encodeValue);
    }

}
