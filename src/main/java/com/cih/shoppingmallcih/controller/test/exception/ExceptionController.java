package com.cih.shoppingmallcih.controller.test.exception;


import com.cih.shoppingmallcih.common.Constants;
import com.cih.shoppingmallcih.controller.customException.CustomException;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/exception")
@Log4j2
public class ExceptionController {

    private MessageSource messageSource;
    ExceptionController(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    @GetMapping
    public void getRuntimeException() {

        Locale locale = LocaleContextHolder.getLocale();

        //Locale locale = Locale.US;    // 영어 테스트
        
        String[] args = {"10"};
        String errorMesage = messageSource.getMessage("main.error.message", args, locale);
        throw new RuntimeException(errorMesage);

       // throw new RuntimeException("getRunTimeException 호출");
    }

    @GetMapping("/custom")
    public void getCustomException() throws CustomException{


        throw new CustomException(Constants.ExceptionClass.PRODUCT, HttpStatus.BAD_REQUEST, "getCusom 호출");
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleException(RuntimeException e, HttpServletRequest request) {

        // 클래스 내에 핸드러 생성하면.. 우선순위가 높다


        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        log.error("클래스 내 Exception호출: , {}, {}", request.getRequestURI(), e.getMessage());

        Map<String, String> map = new HashMap<>();

        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "450");
        map.put("message", e.getMessage());

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }
}
