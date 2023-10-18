package com.cih.shoppingmallcih;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ShoppingmallCihApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingmallCihApplication.class, args);
    }

}
