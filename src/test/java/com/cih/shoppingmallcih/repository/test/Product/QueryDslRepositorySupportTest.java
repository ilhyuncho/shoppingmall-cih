package com.cih.shoppingmallcih.repository.test.Product;

import com.cih.shoppingmallcih.domain.test.product.Product;
import com.cih.shoppingmallcih.domain.test.product.querydslCustom.ProductRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class QueryDslRepositorySupportTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void findByNameTest(){
        List<Product> result = productRepository.findByName("펜2");

        for (Product product : result) {
            System.out.println(product.getName());
            System.out.println(product.getNumber());
        }
    }


}
