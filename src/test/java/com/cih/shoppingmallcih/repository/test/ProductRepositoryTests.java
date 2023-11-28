package com.cih.shoppingmallcih.repository.test;

import com.cih.shoppingmallcih.domain.test.product.Product;
import com.cih.shoppingmallcih.domain.test.product.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest    // 기본값으로 임베디드 데이터베이스를 사용(h2)
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveTest(){
        //given
        Product product = Product.builder()
                .name("펜2")
                .price(100)
                .stock(99).build();
        
        //when
        Product saveProduct = productRepository.save(product);

        //then
        Assertions.assertEquals(product.getName(), saveProduct.getName());
    }

    @Test
    void selectTest(){
        //given
        Product product = Product.builder()
                .name("펜2")
                .price(100)
                .stock(99).build();
        Product saveProduct = productRepository.saveAndFlush(product);
        
        //when
        Product foundProduct = productRepository.findById(saveProduct.getNumber()).get();

        //then
        Assertions.assertEquals(product.getName(), foundProduct.getName());
    }

}
