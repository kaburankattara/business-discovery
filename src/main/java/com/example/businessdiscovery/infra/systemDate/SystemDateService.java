package com.example.businessdiscovery.infra.systemDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SystemDateService {

    private SystemDateDao systemDateDao;

    @Autowired
    public SystemDateService(SystemDateDao systemDateDao) {
        this.systemDateDao = systemDateDao;
    }

    public void setSystemDate() {
        LocalDateTime now = systemDateDao.getSystemDate();
        SystemDate.setSystemDate(now);
    }

}