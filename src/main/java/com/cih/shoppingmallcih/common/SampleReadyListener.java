package com.cih.shoppingmallcih.common;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component      // 빈으로 등록시켜야 정상적으로 호출됨
@Log4j2
public class SampleReadyListener implements ApplicationListener<ApplicationReadyEvent> {
    // ApplicationReadyEvent : 스프링 프레임워크는 애플리케이션이 구동되어 서비스 요청을 받을 준비가 되었을 때
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("==어플리케이션 컨텍스트가 만들어진 후에 발생하는 이벤트,빈으로 등록 해야 함==");
        log.info("Application Ready Event.......");
        log.info("================================================");
    }
}
