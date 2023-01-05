package com.example.businessdiscovery.domain.youtubeDataApi;

import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatistics;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Videos {

    public static Videos createInstanceBySearchResultIteratorList(List<com.google.api.services.youtube.model.Video> youtubeModelVideoList) {

        Iterator<com.google.api.services.youtube.model.Video> iteratorSearchResults = youtubeModelVideoList.iterator();
        if (!iteratorSearchResults.hasNext()) {
            return createEmptyInstance();
        }

        List<Video> videoList = new ArrayList<>();
        while (iteratorSearchResults.hasNext()) {
            videoList.add(Video.createInstance((com.google.api.services.youtube.model.Video) iteratorSearchResults.next()));
        }
        return createInstance(videoList);
    }

    public static Videos createInstance(List<Video> videoList) {
        return new Videos(videoList);
    }

    public static Videos createEmptyInstance() {
        return new Videos();
    }

    private Videos(List<Video> videoList) {
        this.videoList = videoList;
    }

    public Videos() {
    }

    // 動画を一意に識別するID
    @Getter
    private List<Video> videoList = new ArrayList<>();

    /*
     * Iterator 内のすべての SearchResults を出力します。 印刷された各行には、タイトル、ID、およびサムネイルが含まれます。
     */
    public static void prettyPrint(long numberOfVideosReturned, Iterator<com.google.api.services.youtube.model.Video> iteratorSearchResults, String query) {

        System.out.println("\n=============================================================");
        System.out.println(
                "   First " + numberOfVideosReturned + " videos for search on \"" + query + "\".");
        System.out.println("=============================================================\n");

        if (!iteratorSearchResults.hasNext()) {
            System.out.println(" There aren't any results for your query.");
        }

        while (iteratorSearchResults.hasNext()) {

            com.example.businessdiscovery.domain.youtubeDataApi.Video video = new com.example.businessdiscovery.domain.youtubeDataApi.Video();
            video.getTitle();
            com.google.api.services.youtube.model.Video singleVideo = iteratorSearchResults.next();
            System.out.println("\n-------------------------------------------------------------\n");

            // メイン情報
            System.out.println(" 動画を一意に識別するID:" + singleVideo.getId());

            // スニペットからの情報
            VideoSnippet snippet = singleVideo.getSnippet();
            System.out.println(" 動画のタイトル:" + snippet.getTitle());
            System.out.println(" 動画のチャンネルを一意に識別するID:" + snippet.getChannelId());
            System.out.println(" 動画が属するチャンネルタイトル:" + snippet.getChannelTitle());
            System.out.println(" Default Audio Language:" + snippet.getDefaultAudioLanguage());
            System.out.println(" 動画のアップロード日時:" + snippet.getPublishedAt());

            // 統計情報
            VideoStatistics statistics = singleVideo.getStatistics();
            System.out.println(" コメント数:" + statistics.getCommentCount());
            System.out.println(" 動画をお気に入りに登録しているユーザーの数:" + statistics.getFavoriteCount());
            System.out.println(" 動画を高く評価したユーザーの数:" + statistics.getLikeCount());
            System.out.println(" 動画を低く評価したユーザーの数:" + statistics.getDislikeCount());
            System.out.println(" 動画が再生された回数:" + statistics.getViewCount());
        }
    }

}
