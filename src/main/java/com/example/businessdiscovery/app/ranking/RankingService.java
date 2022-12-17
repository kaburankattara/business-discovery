package com.example.businessdiscovery.app.ranking;

import com.example.businessdiscovery.domain.instagramGraphApi.InstagramGraphApiService;
import com.example.businessdiscovery.domain.scraping.ScrapingService;
import com.example.businessdiscovery.domain.youtubeDataApi.YoutubeDataApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RankingService {

    private InstagramGraphApiService instagramGraphApiService;

    private YoutubeDataApiService youtubeDataApiService;

    private ScrapingService scrapingService;

    @Autowired
    RankingService(InstagramGraphApiService instagramGraphApiService, YoutubeDataApiService youtubeDataApiService
            , ScrapingService scrapingService) {
        this.instagramGraphApiService = instagramGraphApiService;
        this.youtubeDataApiService = youtubeDataApiService;
        this.scrapingService = scrapingService;
    }

    public Map init() {
        return instagramGraphApiService.request("moaiskitchen");
    }

    public void init2() {
//        youtubeDataApiService.request("キヨ");
//        youtubeDataApiService.requestVideo("NiCDxS73ewo");
        youtubeDataApiService.requestCommentThreads("");
    }

    public void init3() {
        scrapingService.request("食");
    }
}
