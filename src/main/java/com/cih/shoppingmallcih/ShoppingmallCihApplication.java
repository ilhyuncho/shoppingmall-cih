package com.cih.shoppingmallcih;

import com.cih.shoppingmallcih.common.SampleListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ShoppingmallCihApplication {

    public static void main(String[] args) {

        //SpringApplication.run(ShoppingmallCihApplication.class, args);

        // 스프링 부트를 실행할 때 구동 되는 단계 마다 여러 이벤트 들이 발생 됨
        // SampleListener( ApplicationStartingEvent ) 같은 경우 스프링 컨테이너가 만들어지기 전에 생성되는
        // 이벤트이기 떄문에 아래와 같이 SpringApplication객체에 해당 리스너를 추가 해야 함

        // 어플리케이션 컨텍스트가 만들어진 후에 발생하는 이벤트들은 빈으로 등록되어진 리스너를 실행 함
        SpringApplication app = new SpringApplication(ShoppingmallCihApplication.class);

        app.addListeners(new SampleListener()); // 커스텀 이벤트 리스너 추가, 가변인자를 받으므로 여러개의 리스너를 한번에 등록 가능
        app.run(args);

    }

}
