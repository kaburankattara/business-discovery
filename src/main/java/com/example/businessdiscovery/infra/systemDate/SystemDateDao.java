package com.example.businessdiscovery.infra.systemDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SystemDateDao {

    private SystemDateMapper systemDateMapper;

    @Autowired
    public SystemDateDao(SystemDateMapper systemDateMapper) {
        this.systemDateMapper = systemDateMapper;
    }

    public LocalDateTime getSystemDate() {
        return systemDateMapper.selectSystemTimestamp();
    }

}