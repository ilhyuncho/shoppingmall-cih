package com.cih.shoppingmallcih.domain.test.customRepository;


import com.cih.shoppingmallcih.domain.test.customRepository.projection.DescriptionOnly;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.stream.Stream;

@Repository
//public interface CourceRepository extends CrudRepository<Cource, Long> {
public interface CourceRepository extends PagingAndSortingRepository<Cource, Long>, QuerydslPredicateExecutor<Cource> {

    // 쿼리 메서드 -> @Query 로 변경
    @Query("select c from Cource c where c.category=?1")
    Iterable<Cource> findAllByCategory(String category);

    @Query("select c from Cource c where c.category=:category and c.rating > :rating")
    Iterable<Cource> findAllByCategoryAndRatingGreaterThen(@Param("category") String category, @Param("rating") int rating);

    @Query(value = "select * from Cource where rating=?1", nativeQuery = true)
    Iterable<Cource> findAllByRating(int rating);

    @Modifying      // 수정 작업을 수행한다는 것을 알려줌, 붙이지 않으면 InvalidDataAccessApiUsageException 이 발생
    @Transactional
    @Query("update Cource c set c.rating=:rating where c.name=:name")
    int updateCourceRatingByName(@Param("rating") int rating, @Param("name") String name);



    Iterable<Cource> findAllByCategoryOrderByName(String category);
    boolean existsByName(String name);
    long countByCategory(String Category);
    Iterable<Cource> findByNameOrCategory(String name, String category);
    Iterable<Cource> findByNameStartsWith(String name);
    Stream<Cource> streamAllByCategory(String category);

    //@NamedQuery
    Iterable<Cource> findAllByCategoryAndRating(String category, int rating);

    // 프로젝션 ( projection ) 예제
    Iterable<DescriptionOnly> getCourceByName(String name);



}
