package com.cih.shoppingmallcih.repository.test;

import com.cih.shoppingmallcih.domain.test.customRepository.Cource;
import com.cih.shoppingmallcih.domain.test.customRepository.CourceRepository;
import com.cih.shoppingmallcih.domain.test.customRepository.projection.DescriptionOnly;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
@Log4j2
public class CourceRepositoryTests {

    @Autowired
    private CourceRepository courceRepository;



    @Test
    @DisplayName("쿼리 메서드 확인 테스트")
    @Transactional
    //@Rollback
    @Commit
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
    
    @Test
    @DisplayName("PagingAndSortingRepository를 사용하는 단위 테스트")
    void test2(){
        Pageable pageable = PageRequest.of(0,5);
        Assertions.assertThat(courceRepository.findAll(pageable)).hasSize(5);

        // 다음 페이지 데이터 조회
        Pageable nextPageable = pageable.next();
        Assertions.assertThat(courceRepository.findAll(nextPageable)).hasSize(5);
    }

    @Test
    @DisplayName("페이징과 정렬 예제")
    void test3(){
        Pageable pageable = PageRequest.of(0,5, Sort.by("Name").ascending());

        // 목록의 첫번째 요소를 사용해서 정렬이 바르게 동작했는지 판정
        Condition<Cource> sortedFirstCourceCondition = new Condition<Cource>(){
            @Override
            public boolean matches(Cource value) {
                return value.getId() == 10 && value.getName().equals("gaagdfgdfg");
            }
        };

        Assertions.assertThat(courceRepository.findAll(pageable)).first().has(sortedFirstCourceCondition);
    }

    @Test
    void tes4(){
        // 평점 기준 내림차순, 이름 기준 오름차순
        Pageable pageable = PageRequest.of(0,5,Sort.by("Rating").descending().and(Sort.by("Name")));

        Condition<Cource> customSortFirstCourceCondition = new Condition<Cource>(){
            @Override
            public boolean matches(Cource value){
                return value.getId() == 108 && value.getName().equals("Getting Started with Python");
            }
        };

        Assertions.assertThat(courceRepository.findAll(pageable)).first().has(customSortFirstCourceCondition);
    }

    @Test
    @DisplayName("NamedQuery 동작 검증 단위 테스트")
    void test5(){
        Assertions.assertThat(courceRepository.findAllByCategoryAndRating("Spring", 4)).hasSize(1);
    }

    @Test
    void test6(){
        Assertions.assertThat(courceRepository.findAllByRating(5)).hasSize(3);
    }

    @Test
    void test7(){
        courceRepository.updateCourceRatingByName(10, "JavaScript for All");

        Assertions.assertThat(courceRepository.findAllByCategoryAndRatingGreaterThen("JavaScript", 9)).hasSize(1);
    }

    @Test
    void test8(){
        // 프로젝션 테스트
        Iterable<DescriptionOnly> result = courceRepository.getCourceByName("Rapid Spring Boot Application Development");

        Assertions.assertThat(result).extracting("description")
                .contains("Spring Boot gives all the power of the Spring Framework without all of the complexity");
    }


}
