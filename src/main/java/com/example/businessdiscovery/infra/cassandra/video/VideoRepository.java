package com.example.businessdiscovery.infra.cassandra.video;

import org.springframework.data.repository.CrudRepository;

public interface VideoRepository extends CrudRepository<Video, String> {
    Video findByTitle(String title);
}
