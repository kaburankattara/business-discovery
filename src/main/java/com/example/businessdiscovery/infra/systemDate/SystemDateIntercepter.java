package com.example.businessdiscovery.infra.systemDate;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Aspect
@Component
public class SystemDateIntercepter {

    private SystemDateService systemDateService;

    @Autowired
    public SystemDateIntercepter(SystemDateService systemDateService) {
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
        systemDateService.setSystemDate();
    }

    @After("controllerMethods()")
    public void after(JoinPoint point) throws Throwable {
        SystemDate.removeSystemDate();
    }
}
