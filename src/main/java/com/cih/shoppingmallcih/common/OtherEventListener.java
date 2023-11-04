package com.cih.shoppingmallcih.common;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.*;
import org.springframework.context.event.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Log4j2
@Component
public class OtherEventListener {

    //서비스 시작 이벤트
    //1. Contentext Loading 완료
    //2. ContextRefreshedEvent
    //3. ApplicationPreparedEvent
    //4. ApplicationStartedEvent
    //5. ApplicationReadyEvent
    //    서비스 중지 이벤트
    //1. ContextClosedEvent

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReadyEvent(ApplicationReadyEvent event){
        log.info("Application Ready Event generated at " + new Date(event.getTimestamp()));
    }

    @EventListener(ContextRefreshedEvent.class)
    public void onContextRefreshedEvent(ContextRefreshedEvent event)
    {
        // ApplicationContext 가 갱신되면 ContextRefreshedEvent를 발행한다.


        log.info("@@@ onContextRefreshedEvent !!!");
    }

    @EventListener(ContextStartedEvent.class)
    public void onContextStartedEvent(ContextStartedEvent event) {
        log.info("@@@ onContextStartedEvent !!!");
    }

    @EventListener(ContextStoppedEvent.class)
    public void onContextStoppedEvent(ContextStoppedEvent event) {
        log.info("@@@ onContextStoppedEvent !!!");
    }

    @EventListener(ContextClosedEvent.class)
    public void onContextClosedEvent(ContextClosedEvent event) {
        log.info("@@@ onContextClosedEvent !!!");
    }

    @EventListener(ApplicationContextInitializedEvent.class)
    public void onApplicationContextInitializedEvent(ApplicationContextInitializedEvent event) {
        log.info("@@@ onApplicationContextInitializedEvent !!!");
    }

    @EventListener(ApplicationEnvironmentPreparedEvent.class)
    public void onApplicationEnvironmentPreparedEvent(ApplicationEnvironmentPreparedEvent event) {
        log.info("@@@ onApplicationEnvironmentPreparedEvent !!!");
    }

    @EventListener(ApplicationPreparedEvent.class)
    public void onApplicationPreparedEvent(ApplicationPreparedEvent event) {
        log.info("@@@ onApplicationPreparedEvent !!!");
    }

    @EventListener(ApplicationStartedEvent.class)
    public void onApplicationStartedEvent(ApplicationStartedEvent event) {
        log.info("@@@ onApplicationStartedEvent !!!");
    }

    @EventListener(ApplicationFailedEvent.class)
    public void onApplicationFailedEvent(ApplicationFailedEvent event) {
        log.info("@@@ onApplicationFailedEvent !!!");
    }

    @EventListener(ApplicationStartingEvent.class)
    public void onApplicationStartingEvent(ApplicationStartingEvent event) {
        log.info("@@@ onApplicationStartingEvent !!!");
    }

}
