package com.videon.downloader.list;

import java.io.Serializable;

public class DownloadList implements Serializable {

    private String thumbnail;
    private String title;
    private String duration;
    private String quality;
    private String extension;
    private String formattedSize;
    private String audioAvailable;
    private String videoAvailable;
    private String url;

    public DownloadList(String thumbnail, String title, String duration, String quality, String extension, String formattedSize, String audioAvailable, String videoAvailable, String url) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.duration = duration;
        this.quality = quality;
        this.extension = extension;
        this.formattedSize = formattedSize;
        this.audioAvailable = audioAvailable;
        this.videoAvailable = videoAvailable;
        this.url = url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getFormattedSize() {
        return formattedSize;
    }

    public void setFormattedSize(String formattedSize) {
        this.formattedSize = formattedSize;
    }

    public String getAudioAvailable() {
        return audioAvailable;
    }

    public void setAudioAvailable(String audioAvailable) {
        this.audioAvailable = audioAvailable;
    }

    public String getVideoAvailable() {
        return videoAvailable;
    }

    public void setVideoAvailable(String videoAvailable) {
        this.videoAvailable = videoAvailable;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

