package com.cih.shoppingmallcih.domain.test.product.querydslCustom;

import com.cih.shoppingmallcih.domain.test.product.Product;
import com.cih.shoppingmallcih.domain.test.product.QProduct;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ProductRepositoryCustomImpl extends QuerydslRepositorySupport
        implements ProductRepositoryCustom {

    public ProductRepositoryCustomImpl(){
        // QuerydslRepositorySupport을 상속 받으면 도메인 클래스를 부모 클래스에 전달 해야 한다
        super(Product.class);
    }


    @Override
    public List<Product> findByName(String name) {
        QProduct product = QProduct.product;

        // QuerydslRepositorySupport 가 제공하는 기능 사용 ( 대표적으로 from )
        List<Product> productList = from(product)
                .where(product.name.eq(name))
                .select(product)
                .fetch();

        return productList;
    }
}
