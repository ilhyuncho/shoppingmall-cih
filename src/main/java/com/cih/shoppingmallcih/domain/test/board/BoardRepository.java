package com.cih.shoppingmallcih.domain.test.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface BoardRepository extends JpaRepository<Board, Long>,  BoardSearch {

    @Query(value= "select now()" , nativeQuery = true)
    String getTime();

    // 쿼리 메서드
    Page<Board> findByTitleContainingOrderByBnoDesc(String keyword, Pageable pageable);

    // @Query의 JPQL 사용
    @Query("select b from Board b where b.title like concat('%', :keyword, '%')")
    Page<Board> findKeyword(String keyword, Pageable pageable);

    @EntityGraph(attributePaths = {"imageSet"})
    @Query("select b from Board b where b.bno=:bno")
    Optional<Board> findByIdWithImages(@Param(value="bno") Long bno);

}
