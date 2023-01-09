package com.example.businessdiscovery.infra.cassandra.base;

import com.example.businessdiscovery.infra.systemDate.SystemDate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DbSuffix {

    private static ThreadLocal<DbSuffix> dbSuffixThreadLocal = new ThreadLocal<>();

    public static DbSuffix createInstance() {
        return new DbSuffix();
    }

    private DbSuffix() {
        this.registYmd = SystemDate.getSystemDateByUtc();
        this.updateYmd = SystemDate.getSystemDateByUtc();
    }

    public static DbSuffix getDbSuffix() {
        return dbSuffixThreadLocal.get();
    }

    public static void setDbSuffix() {
        dbSuffixThreadLocal.set(createInstance());
    }

    public static void removeDbSuffix() {
        dbSuffixThreadLocal.remove();
    }

    private LocalDateTime registYmd;

    private LocalDateTime updateYmd;
}
