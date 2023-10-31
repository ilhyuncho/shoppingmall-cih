package com.cih.shoppingmallcih.repository.test;

import com.cih.shoppingmallcih.domain.test.board.Board;
import com.cih.shoppingmallcih.domain.test.customRepository.Cource;
import com.cih.shoppingmallcih.domain.test.customRepository.CourceRepository;
import com.cih.shoppingmallcih.domain.test.customRepository.QCource;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

@SpringBootTest
public class QueryDSLCourceRepoTests {

    @Autowired
    private CourceRepository courceRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void test1(){
        QCource cource = QCource.cource;

//        1. JPAQuery 클래스를 이용
        JPAQuery query1 = new JPAQuery(entityManager);  // JPAQuery는 QueryDSL에서 JPA를 사용할 수 있게 해주는 JPQLQuery 인터페이스의 기본 구현체
        query1.from(cource).where(cource.category.eq("Spring"));

        Assertions.assertThat(query1.fetch().size()).isEqualTo(3);

        JPAQuery query2 = new JPAQuery(entityManager);
        query2.from(cource).where(cource.category.eq("Spring").and(cource.rating.gt(3)));
        Assertions.assertThat(query2.fetch().size()).isEqualTo(2);


        // OrderSpecifier 사용 예제
        OrderSpecifier<Integer> descOrderSpecifier = cource.rating.desc();
        Assertions.assertThat(Lists.newArrayList( courceRepository.findAll(descOrderSpecifier)).get(0).getName()).isEqualTo("JavaScript for All");


    }
}






















