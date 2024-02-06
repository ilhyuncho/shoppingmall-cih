package com.cih.shoppingmallcih.repository.test;

import com.cih.shoppingmallcih.domain.test.customRepository.Cource;
import com.cih.shoppingmallcih.domain.test.customRepository.CustomizedCourseRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
        // 커스텀 리포지터리 예제
        // save, findall 메서드만 호출 가능, 기능을 제한 했기 때문에
        
        Cource cource = Cource.builder()
                .name("gaagdfgdfg")
                .category("Sp34ring")
                .rating(3)
                .description("gdfg3434htghafsdf").build();

        customizedCourseRepository.save(cource);

        Iterable<Cource> all = customizedCourseRepository.findAll();

        // Iterable -> stream으로 변경
        Stream<Cource> stream = StreamSupport.stream(all.spliterator(), false);
        System.out.println(stream.count());

        // 문법 잘못됨
       // Assertions.assertThat(Arrays.asList(customizedCourseRepository.findAll()).size()).isEqualTo(23);

    }
}
