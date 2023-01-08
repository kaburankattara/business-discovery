package com.example.businessdiscovery.infra.cassandra.video;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface VideoRepository extends CrudRepository<VideoEntity, UUID> {
    VideoEntity findByTitle(String title);
}
