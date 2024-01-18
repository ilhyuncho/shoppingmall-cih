package com.cih.shoppingmallcih.config.test.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class Custom403Handler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("==============Access Denied=======================");

        response.setStatus(HttpStatus.FORBIDDEN.value());

        // JSON 요청 이었는지 확인
        String contentType = request.getHeader("Content-Type");
        boolean jsonRequest = contentType.startsWith("application/json");

        log.info("isJSON: " + jsonRequest);

        // <form 태그의 요청이 403 인 경우 로그인 페이지로 이동할떄 'ACCESS_DENIED' 값을 파라미터로 전달
        // Ajax인 경우에는 JSON 데이터를 만들어서 전송
        //일반 request
        if(!jsonRequest){
            response.sendRedirect("/member/login?error=ACCESS_DENIED");
        }
    }
}
