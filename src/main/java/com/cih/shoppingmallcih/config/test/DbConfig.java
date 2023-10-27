package com.cih.shoppingmallcih.config.test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:dbConfig.properties")
public class DbConfig {

    @Autowired
    private Environment env;

    @Override
    public String toString() {
        return "UserName: " + env.getProperty("user") + ", password: " + env.getProperty("password");
    }
}
