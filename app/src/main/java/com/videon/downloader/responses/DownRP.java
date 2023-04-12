package com.videon.downloader.responses;

import com.google.gson.annotations.SerializedName;
import com.videon.downloader.list.DownList;

import java.io.Serializable;
import java.util.List;

public class DownRP implements Serializable {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("VideoDown")
    private List<DownList> downLists;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<DownList> downLists() {
        return downLists;
    }
}
