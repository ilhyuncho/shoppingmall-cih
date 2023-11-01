package com.cih.shoppingmallcih.config.test.autoConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonApplicationContextConfiguration {

    // 조건부 빈 생성 예제
    
    @Bean
    @Conditional(RelationDatabaseCondition.class)   // RelationDatabaseCondition 에 있는 matches()메서드가 true를 반환할때만 빈을 생성 함
    public RelationDatabaseCondition dataSourceConfiguration(){
        return new RelationDatabaseCondition();
    }
}
