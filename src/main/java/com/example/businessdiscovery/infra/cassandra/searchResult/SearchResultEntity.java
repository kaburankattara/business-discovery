package com.example.businessdiscovery.infra.cassandra.searchResult;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("search_result")
@Getter
@Setter
public class SearchResultEntity {

    @PrimaryKey
    private UUID id;

    // チャンネルを一意に識別するID
    private String channelId;

    // 動画を一意に識別するID
    private String videoId;

    // 検索結果のタイトル
    private String title;

    // 画像のURL
    private String thumbnailUrl;

    public SearchResultEntity(String channelId, String videoId, String title, String thumbnailUrl) {
        this.id = UUID.randomUUID();
        this.channelId = channelId;
        this.videoId = videoId;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
    }

}
