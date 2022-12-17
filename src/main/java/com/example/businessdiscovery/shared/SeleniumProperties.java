package com.example.businessdiscovery.shared;

import java.util.ResourceBundle;

public class SeleniumProperties {

    public static SeleniumProperties createInstance() {
        return new SeleniumProperties();
    }

    private SeleniumProperties() {
    }

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("selenium");

    private static final String WEBDRIVER_PATH_DEV = "webdriver.path.dev";
    private static final String WEBDRIVER_PATH_PROD = "webdriver.path.prod";

    private String getWebDriverPathDev() {
        return getProperty(WEBDRIVER_PATH_DEV);
    }

    private String getWebDriverPathProd() {
        return getProperty(WEBDRIVER_PATH_PROD);
    }

    public String getWebDriverPath() {

        String prodFlg = System.getenv("PROD_FLG");
        if (prodFlg != null && prodFlg.equals("1")) {
            return getWebDriverPathProd();
        }
        return getWebDriverPathDev();
    }

    private String getProperty(String key) {
        return resourceBundle.getString(key);
    }

}
