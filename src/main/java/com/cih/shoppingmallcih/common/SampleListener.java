package com.cih.shoppingmallcih.common;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

import java.util.Date;

@Log4j2
public class SampleListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
       log.error("==어플리케이션 컨테스트가 만들어지기 전에 호출 됨==");
       log.info("Application start Event......." + new Date(event.getTimestamp()));
       log.info("================================================");
    }
}
