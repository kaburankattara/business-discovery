package com.example.businessdiscovery.domain.instagramGraphApi.bean;

import com.example.businessdiscovery.shared.HttpRequest;

import java.util.Map;

/**
 * 3番目のアクセストークンをを生成
 */
public class CreateAccessToken {

    private HttpRequest httpRequest = HttpRequest.createInstance();

    public Map<String, Object> execute(String userId, String longLivedAccessToken) {
        String target_url = "https://graph.facebook.com/v15.0/" + userId + "/accounts?access_token=" + longLivedAccessToken;

        Map<String, Object> map = httpRequest.execute(target_url);
//        PrintUtils.printMap(map);
        return map;
    }
}
