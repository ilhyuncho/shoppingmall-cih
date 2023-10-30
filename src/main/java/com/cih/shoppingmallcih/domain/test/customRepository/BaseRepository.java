package com.cih.shoppingmallcih.domain.test.customRepository;


import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean       // 스프링 데이터가 감지해 구현체가 자동으로 만들어 지지 않도록
                        // BaseRepository 인터페이스는 포록시 객체가 생성되지 않는다.
                        // 중간 단계의 Repository 들을 빈으로 등록하지 않으려고 붙여져있다.
public interface BaseRepository<T, ID> extends Repository<T, ID> {

    <S extends T> S save(S entity);
    Iterable<T> findAll();
}
