package me.ninjasul.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Log4j2
public class PerfAspect {

    //@Around("execution(* me.ninjasul..*.EventService.*(..))")
    //@Around("bean(simpleEventService)")
    @Around("@annotation(PerfLogging)")
    public Object logPerf(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        log.info( System.currentTimeMillis() - begin);
        return retVal;
    }

    @Before("bean(simpleEventService)")
    public void hello() {
        log.info("Hello");
    }
}
