package com.example.businessdiscovery.domain.instagramGraphApi.bean;

import com.example.businessdiscovery.shared.HttpRequest;

import java.util.Map;

/**
 * ユーザーIDを取得
 */
public class GetUserId {

    private HttpRequest httpRequest = HttpRequest.createInstance();

    public Map<String, Object> execute(String accessToken) {
        String targetUrl = "https://graph.facebook.com/me?"
                + "access_token=" + accessToken;

        Map<String, Object> map = httpRequest.execute(targetUrl);
//        PrintUtils.printMap(map);
        return map;
    }
}
