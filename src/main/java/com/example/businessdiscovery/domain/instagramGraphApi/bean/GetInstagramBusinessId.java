package com.example.businessdiscovery.domain.instagramGraphApi.bean;

import com.example.businessdiscovery.shared.HttpRequest;

import java.util.Map;

/**
 * ビジネスアカウントIDを取得
 */
public class GetInstagramBusinessId {

    private HttpRequest httpRequest = HttpRequest.createInstance();

    public Map<String, Object> execute(String accessToken) {
        String targetUrl = "https://graph.facebook.com/v15.0/me?"
                + "&fields=instagram_business_account"
                + "&access_token=" + accessToken;

        Map<String, Object> map = httpRequest.execute(targetUrl);
//        PrintUtils.printMap(map);
        return map;
    }
}
