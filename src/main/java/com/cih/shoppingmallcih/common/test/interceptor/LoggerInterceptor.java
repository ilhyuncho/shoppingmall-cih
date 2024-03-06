package com.cih.shoppingmallcih.common.test.interceptor;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
public class LoggerInterceptor implements HandlerInterceptor {

    //Controller 메서드 호출 전
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.error("===============================================");
        log.error("==================== BEGIN ====================");
        log.error("Request URI ===> " + request.getRequestURI());

        // Object handler -> 컨트롤러 클래스의 핸들러 메서드를 참조하는 HandlerMethod 객체이다.

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    //Controller 메서드 호출 후
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.error("==================== END ======================");
        log.error("===============================================");

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    //View 처리까지 완료 후
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
