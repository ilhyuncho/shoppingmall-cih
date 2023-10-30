package com.cih.shoppingmallcih.repository;

import com.cih.shoppingmallcih.domain.test.customRepository.Cource;
import com.cih.shoppingmallcih.domain.test.customRepository.CustomizedCourseRepository;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // EmbeddedDatabase를 찾아 설정하지 않고
                                            // 기존 애플리케이션에서 사용한 DataSource(저의 경우 MySQL)가 등록되게 됩니다.
                                            // https://charliezip.tistory.com/21
@Log4j2
public class CustomRepositoryTests {

    @Autowired
    private CustomizedCourseRepository customizedCourseRepository;

    @Test
    public void test1(){
        Cource cource = Cource.builder()
                .name("gaagdfgdfg")
                .category("Sp34ring")
                .rating(3)
                .description("gdfg3434htghafsdf").build();

        customizedCourseRepository.save(cource);

        Iterable<Cource> all = customizedCourseRepository.findAll();

        Assertions.assertThat(Arrays.asList(customizedCourseRepository.findAll()).size()).isEqualTo(2);

    }
}
