package com.cih.shoppingmallcih.domain.test.product;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // find..... by
    Optional<Product> findByNumber(Long number);
    List<Product> findAllByName(String name);
    Product queryByNumber(Long number);

    // exists... by
    boolean existsByNumber(Long number);

    // count..by
    long countByName(String name);

    // delete... by, remove...by
    void deleteByNumber(Long number);
    long removeByName(String name);

    // First<number>.., Top<number>
    List<Product> findFirst5ByName(String name);
    List<Product> findTop10ByName(String name);

    List<Product> findByPriceLessThan(Integer price, Sort sort);



}
