package com.example.businessdiscovery.infra.cassandra.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VideoDao {

    private VideoRepository repository;

    @Autowired
    public VideoDao(VideoRepository repository) {
        this.repository = repository;
    }

    public VideoEntity findById(UUID uuid) {
        return repository.findById(uuid).get();
    }

    public void save(VideoEntity video) {
        repository.save(video);
    }
}
