package com.cih.shoppingmallcih.repository.test.Product;

import com.cih.shoppingmallcih.domain.test.product.QProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class QueryDSLConfigProductRepositoryTest {

    // Bean 객체로 등록된 JPAQueryFactory를 활용

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Test
    void queryDslTest4(){
        QProduct qProduct = QProduct.product;

        List<String> listResult = jpaQueryFactory.select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("펜2"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (String s : listResult) {
            System.out.println(s);
        }
    }

}
