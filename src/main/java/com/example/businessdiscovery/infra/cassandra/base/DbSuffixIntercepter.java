package com.example.businessdiscovery.infra.cassandra.base;

import com.example.businessdiscovery.infra.systemDate.SystemDateService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
class DbSuffixIntercepter {

    private SystemDateService systemDateService;

    @Autowired
    public DbSuffixIntercepter(SystemDateService systemDateService) {
        this.systemDateService = systemDateService;
    }

    @Pointcut("execution(* *..*.*Controller.*(..)) " +
            "&& (@annotation(org.springframework.web.bind.annotation.RequestMapping) " +
            "    || @annotation(org.springframework.web.bind.annotation.GetMapping)" +
            "    || @annotation(org.springframework.web.bind.annotation.PostMapping)))")
    private void controllerMethods() {
    }

    @Before("controllerMethods()")
    public void before(JoinPoint point) throws Throwable {
        DbSuffix.setDbSuffix();
    }

    @After("controllerMethods()")
    public void after(JoinPoint point) throws Throwable {
        DbSuffix.removeDbSuffix();
    }

}
