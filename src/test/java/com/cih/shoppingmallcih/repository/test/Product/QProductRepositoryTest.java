package com.cih.shoppingmallcih.repository.test.Product;

import com.cih.shoppingmallcih.domain.test.product.Product;
import com.cih.shoppingmallcih.domain.test.product.QProduct;
import com.cih.shoppingmallcih.domain.test.product.QProductRepository;
import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@SpringBootTest
public class QProductRepositoryTest {
    @Autowired
    QProductRepository qProductRepository;

    @Test
    public void queryDslTest1(){
        // Predicate 는 간단하게 표현식으로 정의하는 쿼리라고 생각하면 된다
        Predicate predicate =  QProduct.product.name.containsIgnoreCase("펜")
                .and(QProduct.product.price.between(99,2500));

        Iterable<Product> all = qProductRepository.findAll(predicate);

        for (Product product : all) {
            System.out.println(product.getName());
            System.out.println(product.getNumber());
        }
    }
    
    @Test
    public void queryDSLTEst2(){
        // Predicate를 명시적으로 사용하지 않고 서술부만 가져다 사용하는 방법
        // QuerydslPredicateExecutor를 활용하면 join 이나 fetch기능은 사용할 수 없다

        QProduct qProduct = QProduct.product;

        Iterable<Product> result = qProductRepository.findAll(
                qProduct.name.contains("펜")
                        .and(qProduct.price.between(99, 2500))
        );

        for (Product product : result) {
            System.out.println(product.getName());
            System.out.println(product.getNumber());
        }
    }


}
