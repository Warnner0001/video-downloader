package com.videon.downloader.responses;

import com.google.gson.annotations.SerializedName;
import com.videon.downloader.list.DownloadList;

import java.io.Serializable;
import java.util.List;

public class DownloadRP implements Serializable {

    @SerializedName("title")
    private String title;

    @SerializedName("duration")
    private String duration;

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("medias")
    private List<DownloadList> downloadLists;

    public String getTitle() {
        return title;
    }

    public String getDuration() {
        return duration;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public List<DownloadList> getDownloadLists() {
        return downloadLists;
    }
}
