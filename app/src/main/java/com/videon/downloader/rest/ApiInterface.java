package com.videon.downloader.rest;

import com.videon.downloader.BuildConfig;
import com.videon.downloader.responses.DownRP;
import com.videon.downloader.responses.DownloadRP;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST(BuildConfig.API_FILE)
    @FormUrlEncoded
    Call<DownRP> getDownList(@Field("data") String data);

    @POST(BuildConfig.API_FILE)
    @FormUrlEncoded
    Call<DownloadRP> getDownloadList(@Field("data") String data);

}
