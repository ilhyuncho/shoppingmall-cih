package com.cih.shoppingmallcih.repository.test;

import com.cih.shoppingmallcih.domain.test.customRepository.Cource;
import com.cih.shoppingmallcih.domain.test.customRepository.CourceRepository;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@SpringBootTest
@Log4j2
public class CriteriaAPICourceRepoTests {

    @Autowired
    private CourceRepository courceRepository;

    @Autowired
    private EntityManager entityManager;    // 여러 엔티티 인스턴스로 구성되는 퍼시스턴스(persistence) 컨텍스트와 관련되는 인스턴스 이다.

    @Test
    public void test1(){

        // CriteriaBuilder 인스턴스를 사용하면 CriteriaAPi 기반 쿼리, 조회, 정렬 등을 사용 할수 있다
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Cource> courceCriteriaQuery = criteriaBuilder.createQuery(Cource.class);  

        Root<Cource> courceRoot = courceCriteriaQuery.from(Cource.class);   // CritieriaQuery를 사용해서 쿼리문의 Root를 정의
                                                                            // Root는 쿼리에 사용되는 표현식을 만드는데 사용
        Predicate courceCategoryPredicate = criteriaBuilder.equal(courceRoot.get("category"), "Spring");    // 조회 조건은 Predicate 타입으로 정의

        courceCriteriaQuery.where(courceCategoryPredicate);

        TypedQuery<Cource> query = entityManager.createQuery(courceCriteriaQuery);
        log.info("after createQuery-------");
        
        Assertions.assertThat(query.getResultList().size()).isEqualTo(3);


    }
}
