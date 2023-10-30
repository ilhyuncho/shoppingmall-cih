package com.cih.shoppingmallcih.repository.test;

import com.cih.shoppingmallcih.domain.test.customRepository.Cource;
import com.cih.shoppingmallcih.domain.test.customRepository.CourceRepository;
import com.cih.shoppingmallcih.dto.test.Validation.Course;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@Log4j2
public class CourceRepositoryTests {

    @Autowired
    private CourceRepository courceRepository;



    @Test
    @DisplayName("쿼리 메서드 확인 테스트")
    @Transactional
    @Rollback
    public void test(){

        courceRepository.saveAll(getCourceList());

        Assertions.assertThat(courceRepository.findAllByCategory("Spring")).hasSize(3);
        Assertions.assertThat(courceRepository.existsByName("JavaScript for All")).isTrue();
        Assertions.assertThat(courceRepository.countByCategory("Python")).isEqualTo(2);
        Assertions.assertThat(courceRepository.findByNameOrCategory("Game Development with Python","Python")).hasSize(2);

        Stream<Cource> spring = courceRepository.streamAllByCategory("Spring");
        //spring.forEach(cource -> {log.info(cource);});
        spring.forEach(log::info);

//        List<Cource> collect = spring.collect(Collectors.toList());
//        for (Cource cource : col lect) {
//            log.info(cource);
//        }

    }

    private List<Cource> getCourceList(){

        Cource rapidSpringBootCourse = new Cource("Rapid Spring Boot Application Development", "Spring", 4,"Spring Boot gives all the power of the Spring Framework without all of the complexity");
        Cource springSecurityDslCourse = new Cource("Getting Started with Spring Security DSL", "Spring", 5, "Learn Spring Security DSL in easy steps");
        Cource springCloudKubernetesCourse = new Cource("Getting Started with Spring Cloud Kubernetes", "Spring", 3, "Master Spring Boot application deployment with Kubernetes");
        Cource rapidPythonCourse = new Cource("Getting Started with Python", "Python", 5, "Learn Python concepts in easy steps");
        Cource gameDevelopmentWithPython = new Cource("Game Development with Python", "Python", 3, "Learn Python by developing 10 wonderful games");
        Cource javaScriptForAll = new Cource("JavaScript for All", "JavaScript", 4, "Learn basic JavaScript syntax that can apply to anywhere");
        Cource javaScriptCompleteGuide = new Cource("JavaScript Complete Guide", "JavaScript", 5, "Master JavaScript with Core Concepts and Web Development");

        return Arrays.asList(rapidSpringBootCourse, springSecurityDslCourse, springCloudKubernetesCourse, rapidPythonCourse, gameDevelopmentWithPython, javaScriptForAll, javaScriptCompleteGuide);

    }

}
