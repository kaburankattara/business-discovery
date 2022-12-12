package com.example.businessdiscovery.shared;

import java.util.ResourceBundle;

public class YoutubeProperties {

    public static YoutubeProperties createInstance() {
        return new YoutubeProperties();
    }

    private YoutubeProperties() {
    }

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("youtube");

    private static final String YOUTUBE_APIKEY = "youtube.apikey";

    public String getYoutubeApikey() {
        return getProperty(YOUTUBE_APIKEY);
    }

    private String getProperty(String key) {
        return resourceBundle.getString(key);
    }

}
