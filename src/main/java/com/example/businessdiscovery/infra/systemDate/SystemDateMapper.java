package com.example.businessdiscovery.infra.systemDate;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;

@Mapper
public interface SystemDateMapper {

    @Select("select now()")
    LocalDateTime selectSystemTimestamp();

}
