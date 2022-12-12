package com.example.businessdiscovery.domain.instagramGraphApi.bean;

import com.example.businessdiscovery.shared.HttpRequest;

import java.util.Map;

/**
 * 他のビジネスインスタグラムユーザー情報を取得
 */
public class BusinessDiscovery {

    private HttpRequest httpRequest = HttpRequest.createInstance();

    public Map<String, Object> execute(String instagramBusinessId, String accessToken, String searchUserName) {
        String limit = "10"; // 取得する投稿数
        String media = "id,caption,media_url,permalink,media_type,comments_count,like_count,timestamp"; //取得する投稿の情報
        String fields = "business_discovery.username(" + searchUserName + "){profile_picture_url,follows_count,biography,name,username,followers_count,media_count,website,media.limit(" + limit + "){" + media + "}}"; // 取得する情報
        String targetUrl = "https://graph.facebook.com/v15.0/" + instagramBusinessId + "?fields=" + fields + "&access_token=" + accessToken;

        Map<String, Object> map = httpRequest.execute(targetUrl);
//        PrintUtils.printMap(map);
        return map;
    }
}
