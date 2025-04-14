package com.estsoft.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
/*
* 타겟 지점 -> /blog/service/**
 */

@Slf4j
@Component
@Aspect
public class TimeLoggingAspect {
    /* Pointcut */
    @Pointcut("execution(* com.estsoft.demo.blog.service..*(..))") // 패키지명 (..) -> 0개 이상의 메소드 매개변수 갯수
    public void pointcut() {}

    /* Advice */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        // 시간측정로직
        long startTimeMs = System.currentTimeMillis();

        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        } finally {
            long endTimeMs = System.currentTimeMillis();
            log.info("메소드 실행 시간: {} ms", endTimeMs - startTimeMs);
        }
    }
}
