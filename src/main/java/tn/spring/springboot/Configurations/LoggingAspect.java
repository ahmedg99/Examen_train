package tn.spring.springboot.Configurations;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j

public class LoggingAspect {


    @Around("execution(int tn.spring.springboot.Services.Implementation.*.*(..))")
    public Object monitorPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - startTime;
        log.info("Method name: " + joinPoint.getSignature().getName());
        log.info("Execution time: " + elapsedTime + " milliseconds.");
        return result;
    }

}