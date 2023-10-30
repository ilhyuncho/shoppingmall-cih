package com.cih.shoppingmallcih.domain.test.customRepository;

import com.cih.shoppingmallcih.dto.test.Validation.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.stream.Stream;

public interface CourceRepository extends CrudRepository<Cource, Long> {

    Iterable<Cource> findAllByCategory(String category);
    Iterable<Cource> findAllByCategoryOrderByName(String category);
    boolean existsByName(String name);
    long countByCategory(String Category);
    Iterable<Cource> findByNameOrCategory(String name, String category);
    Iterable<Cource> findByNameStartsWith(String name);
    Stream<Cource> streamAllByCategory(String category);

}
