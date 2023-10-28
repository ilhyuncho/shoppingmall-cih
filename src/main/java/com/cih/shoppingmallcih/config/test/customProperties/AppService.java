package com.cih.shoppingmallcih.config.test.customProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppService {
    
    // 프로퍼티를 읽는 클래스
    private final AppProperties appProperties;

    @Autowired
    public AppService(AppProperties appProperties){
        this.appProperties = appProperties;
    }

    public AppProperties getAppProperties(){
        return this.appProperties;
    }
}
