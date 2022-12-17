package com.example.businessdiscovery.domain.scraping;

import com.example.businessdiscovery.domain.scraping.bean.InstagramLogin;
import org.springframework.stereotype.Service;

@Service
public class ScrapingService {

    InstagramLogin instagramLogin = new InstagramLogin();

    public void request(String keyword) {
        instagramLogin.main(keyword);
    }
}
