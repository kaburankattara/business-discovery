package com.example.businessdiscovery.domain.youtubeDataApi;

import com.example.businessdiscovery.infra.cassandra.searchResult.SearchResultEntity;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.Thumbnail;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SearchResults {

    public static SearchResults createInstanceBySearchResultIteratorList(List<com.google.api.services.youtube.model.SearchResult> youtubeModelSearchResultList) {

        Iterator<com.google.api.services.youtube.model.SearchResult> iteratorSearchResults = youtubeModelSearchResultList.iterator();
        if (!iteratorSearchResults.hasNext()) {
            return createEmptyInstance();
        }

        List<SearchResult> searchResultList = new ArrayList<>();
        while (iteratorSearchResults.hasNext()) {
            searchResultList.add(SearchResult.createInstance((com.google.api.services.youtube.model.SearchResult) iteratorSearchResults.next()));
        }
        return createInstance(searchResultList);
    }

    public static SearchResults createInstance(List<SearchResult> searchResultList) {
        return new SearchResults(searchResultList);
    }

    public static SearchResults createEmptyInstance() {
        return new SearchResults();
    }

    private SearchResults(List<SearchResult> searchResultList) {
        this.searchResultList = searchResultList;
    }

    public SearchResults() {
    }

    @Getter
    private List<SearchResult> searchResultList = new ArrayList<>();

    public List<SearchResultEntity> toEntityList() {
        List<SearchResultEntity> entityList = new ArrayList<>();
        for (SearchResult searchResult : searchResultList) {
            entityList.add(searchResult.toEntity());
        }
        return entityList;
    }

    /*
     * Iterator 内のすべての SearchResults を出力します。 印刷された各行には、タイトル、ID、およびサムネイルが含まれます。
     */
    public static void prettyPrint(long numberOfVideosReturned, Iterator<com.google.api.services.youtube.model.SearchResult> iteratorSearchResults, String query) {

        System.out.println("\n=============================================================");
        System.out.println(
                "   First " + numberOfVideosReturned + " videos for search on \"" + query + "\".");
        System.out.println("=============================================================\n");

        if (!iteratorSearchResults.hasNext()) {
            System.out.println(" There aren't any results for your query.");
        }

        while (iteratorSearchResults.hasNext()) {

            com.google.api.services.youtube.model.SearchResult singleVideo = iteratorSearchResults.next();
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
