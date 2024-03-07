package com.cih.shoppingmallcih;

import com.cih.shoppingmallcih.common.SampleListener;
import com.cih.shoppingmallcih.common.test.actuator.CustomEndpoint.ReleaseItem;
import com.cih.shoppingmallcih.common.test.actuator.CustomEndpoint.ReleaseNote;
import com.cih.shoppingmallcih.common.test.validator.UserVali;
import com.cih.shoppingmallcih.config.test.DbConfig;
import com.cih.shoppingmallcih.config.test.autoConfig.RelationDatabaseCondition;
import com.cih.shoppingmallcih.config.test.customProperties.AppProperties;
import com.cih.shoppingmallcih.config.test.customProperties.AppService;
import com.cih.shoppingmallcih.dto.test.Validation.Course;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;

@Log4j2
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class) // @ConfigurtationProperties 애너테이션이 붙어 있는 클래스를 스프링 컨테이너에 등록
                                                    // AppProperties.class 를 직접 명시 해야 함
                                                    // @ConfigurationPropertiesScan 을 지정해서 지정된 패키지 하위 클래스를 탐색 할수도 있음
//@ConfigurationPropertiesScan("com.cih.shoppingmallcih.config.test")
public class ShoppingmallCihApplication implements CommandLineRunner {  // 부트 메인 클래스가
                                                                // CommandLineRunner 인터페이스를 구현

    public static void main(String[] args) {

        //SpringApplication.run(ShoppingmallCihApplication.class, args);



        SpringApplication app = new SpringApplication(ShoppingmallCihApplication.class);


        // 스프링 부트를 실행할 때 구동 되는 단계 마다 여러 이벤트 들이 발생 됨
        // SampleListener( ApplicationStartingEvent ) 같은 경우 스프링 컨테이너가 만들어지기 전에 생성되는
        // 이벤트이기 떄문에 아래와 같이 SpringApplication객체에 해당 리스너를 추가 해야 함

        // 어플리케이션 컨텍스트가 만들어진 후에 발생하는 이벤트들은 빈으로 등록되어진 리스너를 실행 함
        app.addListeners(new SampleListener()); // 커스텀 이벤트 리스너 추가, 가변인자를 받으므로 여러개의 리스너를 한번에 등록 가능

        // 설정 정보 지정 ( java.util.Properties 또는 Map<String, Object>를 인수로 받음 )
        // application.properties 대용으로 ( 나중에 바뀌지 않는 경우에 적합 )
        Properties properties = new Properties();
        properties.setProperty("spring.config.on-not-found", "ignore"); // 명시한 파일이 존재하지 않더라도 무시하고 기동 작업 계속 진행

        app.setDefaultProperties(properties);

        // run()~~~~~~~~
        ConfigurableApplicationContext applicationContext = app.run(args);

        // @PropertySource("classpath:dbConfig.properties") 사용 예제
        // 설정 파일 위치를 임의로 지정할 수 있다.
        DbConfig dbConfig = applicationContext.getBean(DbConfig.class); // 설정 정보 빈을 가져옴
        log.warn("dbConfig.toString() : " + dbConfig.toString());  // UserName: sa, password: password!

        // 커스텀 프로퍼티 예제 ( @ConfigurationProperties("app.sbip.ct") 활용
        // 타입 안정성을 보장
        AppService appService = applicationContext.getBean(AppService.class);
        log.warn("appService.getAppProperties() : " + appService.getAppProperties().toString());


        // @Conditional(RelationDatabaseCondition.class) 빈 생성 확인 테스트 코드
        try {   // 예외처리는 테스트용으로 작성 함
            RelationDatabaseCondition rdCondition = applicationContext.getBean(RelationDatabaseCondition.class);
            log.warn("bean RelationDatabaseCondition : " + rdCondition);
        }catch (NoSuchBeanDefinitionException e){
            log.error(e.getMessage());
        }


    }

    @Override
    public void run(String... args) throws Exception {
        // MyCommandLineRunner, OtherCommandLineRunner 보다 우선순위 가 낮은듯.. ( 실행 순서 지정 불가 )
        log.error("메인 클래스의 CommandLineRunner run()~~~");


        Course course = new Course();
        course.setId(1);
        course.setRating(0);

        log.error("-------------validation start------------------------");

        // 유효성을 검증하는 Validator 인스턴스를 획득한다.
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        // cource 객체에 정의된 모든 제약 사항 준수 여부를 검증하고 위반 사항을 모아서 반환한다.
        Set<ConstraintViolation<Course>> violation = validator.validate(course);

        violation.forEach( a -> log.error("Violation details: [{}],", a.getMessage()));


        // Password 테스트--------------------------------------------------------

        UserVali user1 = new UserVali("cih1", "sdfsdf");

        Validator validator1 = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<UserVali>> violations = validator1.validate(user1);

        log.error("Password Violation1===");
        violations.forEach( a -> log.error("user1.violation details: [{}].", a.getMessage()));


        UserVali user2 = new UserVali("cih1", "sdf01$14UDFdgg");

        validator1 = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validator1.validate(user2);

        log.error("Password Violation2===");
        if(violations.isEmpty()){
            log.info("password user2 adher to the policy");
        }
        else{
            violations.forEach( a -> log.error("user2.violation details: [{}].", a.getMessage()));
        }
    }

    @Bean(name="releaseNotes")
    @Profile("production")
    public Collection<ReleaseNote> loadReleaseNotes(){
        Set<ReleaseNote> releaseNotes = new LinkedHashSet<>();

        ReleaseNote releaseNote1 = ReleaseNote.builder()
                .version("v1.2.1")
                .releaseDate(LocalDate.of(2021,12,30))
                .commitTag("a5343d")
                .bugFixes(Set.of(getReleaseItem("SBIP-132", "the name @299d3"),
                        getReleaseItem("SBIP-134", "dfgfgfg #gfgfg")
                        )).build();

        ReleaseNote releaseNote2 = ReleaseNote.builder()
                .version("v1.2.0")
                .releaseDate(LocalDate.of(2021,11,5))
                .commitTag("b5df343d")
                .bugFixes(Set.of(getReleaseItem("SBIP-132", "the name11 @123"),
                        getReleaseItem("SBIP-134", "dfgfgddfg #gfgfg33")
                )).build();

        releaseNotes.addAll(Set.of(releaseNote1, releaseNote2));

        return releaseNotes;
    }

    private ReleaseItem getReleaseItem(String itemId, String itemDescription) {
        return ReleaseItem
                .builder()
                .itemId(itemId)
                .itemDescription(itemDescription)
                .build();
    }

}
