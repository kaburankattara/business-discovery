package com.example.businessdiscovery.infra.systemDate;

import java.time.LocalDateTime;

public class SystemDate {
    private static ThreadLocal<LocalDateTime> systemDateThreadLocal = new ThreadLocal<>();

    private SystemDate() {
    }

    public static LocalDateTime getSystemDateByUtc() {
        return systemDateThreadLocal.get();
    }

    public static LocalDateTime getSystemDateByJst() {
        final int TIME_DIFFERENCE_TO_JST = 9;
        LocalDateTime systemDate = systemDateThreadLocal.get();
        return systemDate.plusHours(TIME_DIFFERENCE_TO_JST);
    }

    public static void setSystemDate(LocalDateTime sysDate) {
        systemDateThreadLocal.set(sysDate);
    }

    public static void removeSystemDate() {
        systemDateThreadLocal.remove();
    }
}
