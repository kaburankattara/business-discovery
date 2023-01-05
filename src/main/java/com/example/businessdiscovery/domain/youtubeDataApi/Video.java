package com.example.businessdiscovery.domain.youtubeDataApi;

import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatistics;
import lombok.Getter;

import java.math.BigInteger;

public class Video {

    public static Video createInstance(com.google.api.services.youtube.model.Video singleVideo) {
        return new Video(singleVideo);
    }

    private Video(com.google.api.services.youtube.model.Video singleVideo) {
        // メイン情報
        this.id = singleVideo.getId();

        // スニペットからの情報
        VideoSnippet snippet = singleVideo.getSnippet();
        this.title = snippet.getTitle();
        this.channelId = snippet.getChannelId();
        this.channelTitle = snippet.getChannelTitle();
        this.defaultAudioLanguage = snippet.getDefaultAudioLanguage();
        this.publishedAt = snippet.getPublishedAt().toString();

        // 統計情報
        VideoStatistics statistics = singleVideo.getStatistics();
        this.commentCount = statistics.getCommentCount();
        this.favoriteCount = statistics.getFavoriteCount();
        this.likeCount = statistics.getLikeCount();
        this.dislikeCount = statistics.getDislikeCount();
        this.viewCount = statistics.getViewCount();
    }

    public Video() {
    }

    // 動画を一意に識別するID
    @Getter
    private String id;

    // 動画のタイトル
    @Getter
    private String title;

    // 動画のチャンネルを一意に識別するID
    @Getter
    private String channelId;

    // 動画が属するチャンネルタイトル
    @Getter
    private String channelTitle;

    // Default Audio Language
    @Getter
    private String defaultAudioLanguage;

    // 動画のアップロード日時
    @Getter
    private String publishedAt;

    // 統計情報
    // コメント数
    @Getter
    private BigInteger commentCount;

    // 動画をお気に入りに登録しているユーザーの数
    @Getter
    private BigInteger favoriteCount;

    // 動画を高く評価したユーザーの数
    @Getter
    private BigInteger likeCount;

    // 動画を低く評価したユーザーの数
    @Getter
    private BigInteger dislikeCount;

    // 動画が再生された回数
    @Getter
    private BigInteger viewCount;

    public com.example.businessdiscovery.infra.cassandra.video.Video toEntity() {
        return new com.example.businessdiscovery.infra.cassandra.video.Video(id, title, channelId, channelTitle,
                defaultAudioLanguage, publishedAt, commentCount, favoriteCount, likeCount, dislikeCount,viewCount);
    }
}
