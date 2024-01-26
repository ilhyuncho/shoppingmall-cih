package com.cih.shoppingmallcih.controller.advice;

import com.cih.shoppingmallcih.controller.customException.CourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// RestControllerAdvice = ControllerAdvice + ResponseBody
// RestControllerAdvice 가 리턴값을 바로 클라이언트에게 전달하는 것과 달리
// ControllerAdvice 는 리턴값을 기준으로 동일한 이름의 view를 찾는다
// ResponseBody 어노테이션을 붙이면 RestControllerAdvice 와 동일하게 작동하지만
@ControllerAdvice

public class CourceRestExceptionAdvice extends ResponseEntityExceptionHandler {
    // ResponseEntityExceptionHandler : @RequestMapping이 붙어 있는 모든 메서드에서 발생한 예외를 
    // @ExceptionHandler가 붙어 있는 메서드에서 처리할 수 있도록 중앙화된 예외 처리 기능을 제공
    
    
    // 실전 스프링 부트 책 version

    // CourceNotFoundException 을 처리하는 예외 핸들러 구현
    @ExceptionHandler(value = {CourceNotFoundException.class})
    public ResponseEntity<?> handleCourceNotFound(CourceNotFoundException courceNotFoundException, WebRequest request){
        return super.handleExceptionInternal(
                courceNotFoundException,
                courceNotFoundException.getMessage(),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                request
        );
    }

}
