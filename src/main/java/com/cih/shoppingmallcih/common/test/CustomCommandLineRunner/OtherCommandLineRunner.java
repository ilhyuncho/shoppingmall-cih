package com.cih.shoppingmallcih.common.test.CustomCommandLineRunner;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Order(1)       // CommandLineRunner구현체가 여러개 있을때 @Order로 실행 순서를 결정 ( 낮을수록 우선순위가 높다 )
@Component
@Log4j2
public class OtherCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("OtherCommandLineRunner : run~~ ");
        Arrays.stream(args).forEach(String -> System.out.println());
    }
}
