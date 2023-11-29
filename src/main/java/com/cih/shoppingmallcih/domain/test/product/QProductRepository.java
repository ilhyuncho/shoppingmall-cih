package com.cih.shoppingmallcih.domain.test.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface QProductRepository extends JpaRepository<Product, Long>,
        QuerydslPredicateExecutor<Product>  // QueryDsl을 더욱 편리하게,
// QuerydslPredicateExecutor 인터페이스의 메서드는 대부분 Predicate 타입을 매개변수로 받는다
// Predicate는 표현식을 작성할 수 있게 QueryDSL에서 제공하는 인터페이스
{

}
