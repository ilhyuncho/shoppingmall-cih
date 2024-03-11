package com.cih.shoppingmallcih.common.test.aspect;

import com.cih.shoppingmallcih.dto.test.product.ProductResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
@Log4j2
public class ReturnValueLoggingAspect {
    // 대상 메서드가 종료 될때 실행
    @AfterReturning(pointcut = "execution(* getProduct(..))", returning = "retVals")
    public void printReturnObject(JoinPoint joinPoint, ProductResponseDTO retVals) throws Throwable{
        log.error( "printReturnObject retVals : " + retVals.toString());
        //retVals : ProductResponseDTO(number=1, name=스프링 부트 jpa12, price=5000, stock=500)
    }

    @AfterThrowing(pointcut = "execution(* getProduct(..))", throwing="th")
    public void printThrowable(JoinPoint joinPoint, Throwable th) throws Throwable {
        log.error("error processing", th);
    }
}
