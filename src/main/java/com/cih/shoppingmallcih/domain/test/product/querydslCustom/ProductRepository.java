package com.cih.shoppingmallcih.domain.test.product.querydslCustom;

import com.cih.shoppingmallcih.domain.test.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("productRepositorySupport") // 이미 ProductRepository를 사용하고 있기 떄문에
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {

}
