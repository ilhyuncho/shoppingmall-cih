package com.cih.shoppingmallcih.domain.test.board;

import com.cih.shoppingmallcih.domain.test.board.SearchQuerydsl.BoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface BoardRepository extends JpaRepository<Board, Long>,
        BoardSearch {   //Querydsl(3)

    @Query(value= "select now()" , nativeQuery = true)
    String getTime();

    // 쿼리 메서드
    Page<Board> findByTitleContainingOrderByBnoDesc(String keyword, Pageable pageable);

    // @Query의 JPQL 사용
    @Query("select b from Board b where b.title like concat('%', :keyword, '%')")
    Page<Board> findKeyword(String keyword, Pageable pageable);

    // 지연(lazy)로딩이라고 해도 한 번에 조인 처리해서 select가 이루어지도록 하는 방법
    @EntityGraph(attributePaths = {"imageSet"}) // attributePaths : 같이 로딩해야 하는 속성을 명시
    @Query("select b from Board b where b.bno=:bno")
    Optional<Board> findByIdWithImages(@Param(value="bno") Long bno);

}
