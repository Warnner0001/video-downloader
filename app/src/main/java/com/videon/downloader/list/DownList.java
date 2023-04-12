package com.videon.downloader.list;

import java.io.Serializable;

public class DownList implements Serializable {

    private final String downloader_name;
    private final String image;

    public DownList(String downloader_name, String image) {
        this.downloader_name = downloader_name;
        this.image = image;
    }

    public String getDownloader_name() {
        return downloader_name;
    }

    public String getImage() {
        return image;
    }
}
