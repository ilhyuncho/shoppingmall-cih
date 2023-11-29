package com.cih.shoppingmallcih.domain.test.product.querydslCustom;

import com.cih.shoppingmallcih.domain.test.product.Product;

import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> findByName(String name);
}
