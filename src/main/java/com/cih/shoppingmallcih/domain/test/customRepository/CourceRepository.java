package com.cih.shoppingmallcih.domain.test.customRepository;

import com.cih.shoppingmallcih.dto.test.Validation.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
//public interface CourceRepository extends CrudRepository<Cource, Long> {
    public interface CourceRepository extends PagingAndSortingRepository<Cource, Long> {

    Iterable<Cource> findAllByCategory(String category);
    Iterable<Cource> findAllByCategoryOrderByName(String category);
    boolean existsByName(String name);
    long countByCategory(String Category);
    Iterable<Cource> findByNameOrCategory(String name, String category);
    Iterable<Cource> findByNameStartsWith(String name);
    Stream<Cource> streamAllByCategory(String category);

    Iterable<Cource> findAllByCategoryAndRating(String category, int rating);

}
