package com.example.businessdiscovery.infra.cassandra.searchResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchResultDao {

    private SearchResultRepository repository;

    @Autowired
    public SearchResultDao(SearchResultRepository repository) {
        this.repository = repository;
    }

//    public SearchResultEntity findById(String id) {
//        return repository.findById(id).get();
//    }

    public void saveAll(List<SearchResultEntity> entities) {
        repository.saveAll(entities);
    }
}
