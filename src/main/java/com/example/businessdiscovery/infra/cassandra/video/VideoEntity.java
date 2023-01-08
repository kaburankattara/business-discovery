package com.example.businessdiscovery.infra.cassandra.video;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigInteger;
import java.util.UUID;

@Table("video")
@Getter
@Setter
public class VideoEntity {

    @PrimaryKey
    private UUID id;

    // 動画を一意に識別するID
    private String videoId;

    // 動画のタイトル
    private String title;

    // 動画のチャンネルを一意に識別するID
    private String channelId;

    // 動画が属するチャンネルタイトル
    private String channelTitle;

    // Default Audio Language
    private String defaultAudioLanguage;

    // 動画のアップロード日時
    private String publishedAt;

    // 統計情報
    // コメント数
    private BigInteger commentCount;

    // 動画をお気に入りに登録しているユーザーの数
    private BigInteger favoriteCount;

    // 動画を高く評価したユーザーの数
    private BigInteger likeCount;

    // 動画を低く評価したユーザーの数
    private BigInteger dislikeCount;

    // 動画が再生された回数
    private BigInteger viewCount;

    public VideoEntity(String videoId, String title, String channelId, String channelTitle, String defaultAudioLanguage,
                       String publishedAt, BigInteger commentCount, BigInteger favoriteCount, BigInteger likeCount,
                       BigInteger dislikeCount, BigInteger viewCount) {
        this.id = UUID.randomUUID();
        this.videoId = videoId;
        this.title = title;
        this.channelId = channelId;
        this.channelTitle = channelTitle;
        this.defaultAudioLanguage = defaultAudioLanguage;
        this.publishedAt = publishedAt;
        // 統計情報
        this.commentCount = commentCount;
        this.favoriteCount = favoriteCount;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.viewCount = viewCount;
    }

}
