package com.cih.shoppingmallcih.config.test;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:dbConfig.properties")
@RequiredArgsConstructor
public class DbConfig {

    //@Autowired
    private final Environment env;  // application.properties에서 읽은 설정 정보를 가지고 있다

    @Override
    public String toString() {
        return "UserName: " + env.getProperty("user") + ", password: " + env.getProperty("password");
    }
}
