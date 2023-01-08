package com.example.businessdiscovery.infra.cassandra.searchResult;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SearchResultRepository extends CrudRepository<SearchResultEntity, UUID> {
    SearchResultEntity findByTitle(String title);
}
