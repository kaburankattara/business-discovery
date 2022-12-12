package com.example.businessdiscovery.domain.instagramGraphApi.bean;

import com.example.businessdiscovery.shared.HttpRequest;
import com.example.businessdiscovery.shared.InstagramProperties;

import java.util.Map;

/**
 * instagram.Propertiesに設定されたAppId、AppSecretに対する長期アクセストークンを生成
 *
 * 【参考文献】
 * https://developers.facebook.com/docs/pages/access-tokens
 */
public class CreateLongLivedAccessToken {

    private HttpRequest httpRequest = HttpRequest.createInstance();

    public Map<String, Object> execute(String shortLivedUserAccessToken) {
        InstagramProperties properties = InstagramProperties.createInstance();
        String targetUrl = "https://graph.facebook.com/oauth/access_token?"
                + "grant_type=fb_exchange_token"
                + "&client_id=" + properties.getAppId()
                + "&client_secret=" + properties.getAppSecret()
                + "&fb_exchange_token=" + shortLivedUserAccessToken;

        Map<String, Object> map = httpRequest.execute(targetUrl);
//        PrintUtils.printMap(map);
        return map;
    }
}
