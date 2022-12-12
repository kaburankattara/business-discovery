package com.example.businessdiscovery.app.ranking;

import com.example.businessdiscovery.utils.PrintUtils;

import java.util.Map;

public class RankingForm {
    Map<String, Object> map;

    public String getMap() {
        return PrintUtils.printMap(map);
    }
}
