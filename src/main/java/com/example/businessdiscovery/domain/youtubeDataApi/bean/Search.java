package com.example.businessdiscovery.domain.youtubeDataApi.bean;

import com.example.businessdiscovery.shared.YoutubeProperties;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 検索語に基づいてビデオのリストを出力します。
 */
public class Search {

    /** HTTP トランスポートのグローバル インスタンス。 */
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    /** JSON ファクトリのグローバル インスタンス。 */
    private static final JsonFactory JSON_FACTORY = new GsonFactory();

    /** 返される動画の最大数のグローバル インスタンス (50 = ページあたりの上限)。 */
    private static final long NUMBER_OF_VIDEOS_RETURNED = 25;

    /** すべての API リクエストを行うための Youtube オブジェクトのグローバル インスタンス。 */
    private static YouTube youtube;

    /**
     * YouTube オブジェクトを初期化して、YouTube で動画を検索します (Youtube.Search.List)。 プログラム
     * 次に、各ビデオの名前とサムネイルを出力します (最初の 50 個のビデオのみ)。
     *
     * @param searchKeyword command line args.
     */
    public void main(String searchKeyword) {
        // Read the developer key from youtube.properties
        YoutubeProperties properties = YoutubeProperties.createInstance();

        try {
            /*
             * YouTube オブジェクトは、すべての API リクエストを行うために使用されます。 最後の引数は必須ですが、
             * HttpRequest の初期化時に何も初期化する必要がないため、オーバーライドします。
             * インターフェースを変更し、no-op 機能を提供します。
             */
            youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {}
            }).setApplicationName("youtube-cmdline-search-sample").build();

            // ユーザーからクエリ用語を取得します
            String queryTerm = searchKeyword;

            List<String> part = new ArrayList<>();
            part.add("id");
            part.add("snippet");
            YouTube.Search.List search = youtube.search().list(part);
            /*
             * Google Developer Console から API キーを設定することが重要です。
             * 認証されていないリクエスト (次のリンクの [認証情報] タブにあります:
             * console.developers.google.com/)。 これは良い習慣であり、クォータを増やしました。
             */
            String apiKey = properties.getYoutubeApikey();
            search.setKey(apiKey);
            search.setQ(queryTerm);
            /*
             * 動画のみを検索しています (プレイリストやチャンネルは検索していません)。 もし私たちが探していたら
             * さらに、"video,playlist,channel" のような文字列として追加します。
             */
//            List<String> type = new ArrayList<>();
//            type.add("video");
//            type.add("comment");
//            search.setType(type);
            /*
             * このメソッドは、返される情報を必要なフィールドのみに減らし、呼び出しをより効率的にします。
             */
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
            SearchListResponse searchResponse = search.execute();

            List<SearchResult> searchResultList = searchResponse.getItems();

            if (searchResultList != null) {
                prettyPrint(searchResultList.iterator(), queryTerm);
            }
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /*
     * Iterator 内のすべての SearchResults を出力します。 印刷された各行には、タイトル、ID、およびサムネイルが含まれます。
     */
    private static void prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) {

        System.out.println("\n=============================================================");
        System.out.println(
                "   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\".");
        System.out.println("=============================================================\n");

        if (!iteratorSearchResults.hasNext()) {
            System.out.println(" There aren't any results for your query.");
        }

        while (iteratorSearchResults.hasNext()) {

            SearchResult singleVideo = iteratorSearchResults.next();
            ResourceId rId = singleVideo.getId();

            // 種類がビデオであることを再確認します。
            if (rId.getKind().equals("youtube#video")) {
                Thumbnail thumbnail = (Thumbnail)singleVideo.getSnippet().getThumbnails().get("default");

                System.out.println(" channel Id:" + singleVideo.getSnippet().getChannelId());
                System.out.println(" Video Id:" + rId.getVideoId());
                System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
                System.out.println(" Thumbnail: " + thumbnail.getUrl());
                System.out.println("\n-------------------------------------------------------------\n");
            }
        }
    }
}