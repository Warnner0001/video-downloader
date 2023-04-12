package com.videon.downloader.include;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalPref {

    private static SharedPreferences mPreferences;
    public static final String WA_TREE_URI = "krishna_wa_uri";
    public static final String WB_TREE_URI = "krishna_wb_uri";

    private static SharedPreferences getInstance(Context context) {
        if (mPreferences == null) {
            mPreferences = context.getApplicationContext()
                    .getSharedPreferences(Constant.VideoSavePath, Context.MODE_PRIVATE);
        }
        return mPreferences;
    }

    public static int getInt(Context context, String key, int defaultValue) {
        return getInstance(context).getInt(key, defaultValue);
    }

    public static void setInt(Context context, String key, int value) {
        getInstance(context).edit().putInt(key, value).apply();
    }

    public static void clearPrefs(Context context) {
        getInstance(context).edit().clear().apply();
    }

    public static void setWATree(Context context, String value) {
        getInstance(context).edit().putString(WA_TREE_URI, value).apply();
    }

    public static String getWATree(Context context) {
        return getInstance(context).getString(WA_TREE_URI, "");
    }

    public static void setWBTree(Context context, String value) {
        getInstance(context).edit().putString(WB_TREE_URI, value).apply();
    }

    public static String getWBTree(Context context) {
        return getInstance(context).getString(WB_TREE_URI, "");
    }
}
