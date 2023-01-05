package com.example.businessdiscovery.infra.cassandra.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoDao {

    private VideoRepository repository;

    @Autowired
    public VideoDao(VideoRepository repository) {
        this.repository = repository;
    }

    public Video findById(String id) {
        return repository.findById(id).get();
    }

    public void save(Video video) {
        repository.save(video);
    }
}
