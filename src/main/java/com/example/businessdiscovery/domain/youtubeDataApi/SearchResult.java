package com.example.businessdiscovery.domain.youtubeDataApi;

import com.example.businessdiscovery.infra.cassandra.searchResult.SearchResultEntity;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.Thumbnail;
import lombok.Getter;

public class SearchResult {

    public static SearchResult createInstance(com.google.api.services.youtube.model.SearchResult searchResult) {
        return new SearchResult(searchResult);
    }

    private SearchResult(com.google.api.services.youtube.model.SearchResult searchResult) {
        Thumbnail thumbnail = (Thumbnail)searchResult.getSnippet().getThumbnails().get("default");
        ResourceId rId = searchResult.getId();

        this.channelId = searchResult.getSnippet().getChannelId();
        this.videoId = rId.getVideoId();
        this.title = searchResult.getSnippet().getTitle();
        this.thumbnailUrl = thumbnail.getUrl();
    }

    public SearchResult() {
    }

    // チャンネルを一意に識別するID
    @Getter
    private String channelId;

    // 動画を一意に識別するID
    @Getter
    private String videoId;

    // 検索結果のタイトル
    @Getter
    private String title;

    // 画像のURL
    @Getter
    private String thumbnailUrl;

    public SearchResultEntity toEntity() {
        return new SearchResultEntity(channelId, videoId, title, thumbnailUrl);
    }
}
