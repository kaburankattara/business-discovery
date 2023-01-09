package com.example.businessdiscovery.infra.cassandra.base;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseEntity {

    public BaseEntity(LocalDateTime registYmd, LocalDateTime updateYmd) {
        this.registYmd = registYmd;
        this.updateYmd = updateYmd;
    }

    public BaseEntity() {
    }

    public void setSuffixForInsert() {
        DbSuffix dbSuffix = DbSuffix.getDbSuffix();
        if (dbSuffix == null) {
            return;
        }
        this.registYmd = dbSuffix.getRegistYmd();
        this.updateYmd = dbSuffix.getUpdateYmd();
    }

    public void setSuffixForUpdate() {
        DbSuffix dbSuffix = DbSuffix.getDbSuffix();
        if (dbSuffix == null) {
            return;
        }
        this.updateYmd = dbSuffix.getUpdateYmd();
    }

    private LocalDateTime registYmd;

    private LocalDateTime updateYmd;

}
