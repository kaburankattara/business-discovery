package com.example.businessdiscovery.shared;

import java.util.ResourceBundle;

public class InstagramProperties {

    public static InstagramProperties createInstance() {
        return new InstagramProperties();
    }

    private InstagramProperties() {
    }

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("instagram");

    private static final String APP_ID = "app.id";
    private static final String APP_SECRET = "app.secret";

    public String getAppId() {
        return getProperty(APP_ID);
    }

    public String getAppSecret() {
        return getProperty(APP_SECRET);
    }

    private String getProperty(String key) {
        return resourceBundle.getString(key);
    }

}
