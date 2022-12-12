package com.example.businessdiscovery.domain.youtubeDataApi;

import com.example.businessdiscovery.domain.youtubeDataApi.bean.CommentThreads;
import com.example.businessdiscovery.domain.youtubeDataApi.bean.Search;
import com.example.businessdiscovery.domain.youtubeDataApi.bean.Videos;
import org.springframework.stereotype.Service;

@Service
public class YoutubeDataApiService {
    public void request(String searchUserName) {
        Search search = new Search();
        search.main(null);
    }

    public void requestVideo(String searchUserName) {
        Videos videos = new Videos();
        videos.main(searchUserName);
    }

    public void requestCommentThreads(String searchUserName) {
        CommentThreads commentThreads = new CommentThreads();
        commentThreads.main("", searchUserName);
    }
}
