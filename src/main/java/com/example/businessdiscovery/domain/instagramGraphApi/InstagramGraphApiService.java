package com.example.businessdiscovery.domain.instagramGraphApi;

import com.example.businessdiscovery.domain.instagramGraphApi.bean.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InstagramGraphApiService {
    public Map<String, Object> request(String searchUserName) {
        // ターミナル経由でユーザーからのユーザートークンを返します。
        String shortLivedUserAccessToken = "";

        String longLivedAccessToken = (String) new CreateLongLivedAccessToken().execute(shortLivedUserAccessToken).get("access_token");

        String userId = (String) new GetUserId().execute(longLivedAccessToken).get("id");

        CreateAccessToken createAccessToken = new CreateAccessToken();
        Map<String, Object> accessTokenMap = createAccessToken.execute(userId, longLivedAccessToken);
        List<Map<String, Object>> dataList = (List<Map<String, Object>>) accessTokenMap.get("data");
        Map<String, Object> data = dataList.get(0);
        String accessToken = (String) data.get("access_token");

        Map<String, Object> InstagramBusinessIdMap = new GetInstagramBusinessId().execute(accessToken);
        Map<String, Object> instagramBusinessAccountMap = (Map<String, Object>) InstagramBusinessIdMap.get("instagram_business_account");
        String instagramBusinessId = (String) instagramBusinessAccountMap.get("id");

        // ターミナル経由でユーザーからの検索するインスタグラムユーザーを返します。
        Map<String, Object> businessDiscoveryMap = new BusinessDiscovery().execute(instagramBusinessId, accessToken, searchUserName);

        return businessDiscoveryMap;
    }
}
