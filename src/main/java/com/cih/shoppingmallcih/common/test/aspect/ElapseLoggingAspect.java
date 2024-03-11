package com.cih.shoppingmallcih.common.test.aspect;


import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Log4j2
@Component
@Aspect
@Order(2)
public class ElapseLoggingAspect {

    @Around("@annotation(ElapseLoggable)")
    public Object printElapseTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        // throws Throwable -> 대상 객체의 메서드에서 발생한 예외는 그대로 다시 던지도록 지정

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.error("start time clock");

        Object result;

        try {
            result = proceedingJoinPoint.proceed();
        } finally {
            stopWatch.stop();
            String methodName = proceedingJoinPoint.getSignature().getName();
            long elapsedTime = stopWatch.getLastTaskTimeMillis();
            log.error("{}, elapsed time: {} ms", methodName, elapsedTime);

            //getProduct, elapsed time: 112 ms
        }

        return result;
    }

}
