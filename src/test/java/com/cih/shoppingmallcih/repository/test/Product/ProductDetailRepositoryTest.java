package com.cih.shoppingmallcih.repository.test.Product;

import com.cih.shoppingmallcih.domain.test.product.Product;
import com.cih.shoppingmallcih.domain.test.product.ProductDetail;
import com.cih.shoppingmallcih.domain.test.product.ProductDetailRepository;
import com.cih.shoppingmallcih.domain.test.product.ProductRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductDetailRepositoryTest {

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName(value="일대일 단방향 테스트")
    public void saveAndReadTest1(){
        Product product = new Product();
        product.setName("스프링 부트 jpa12");
        product.setPrice(5000);
        product.setStock(500);

        productRepository.save(product);

        ProductDetail productDetail = new ProductDetail();
        productDetail.setProduct(product);
        productDetail.setDescription("스프링 부트와 함꼐 볼수 있는 책12");

        productDetailRepository.save(productDetail);

        System.out.println("savedProduct1:" + productRepository.findById(
                product.getNumber()).get());

        System.out.println("savedProduct2:" + productDetailRepository.findById(
                productDetail.getId()).get().getProduct());

        System.out.println("savedProductDetail:" + productDetailRepository.findById(
                productDetail.getId()).get());
    }


}
