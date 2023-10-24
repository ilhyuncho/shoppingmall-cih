package com.cih.shoppingmallcih.domain.test.reply;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Query("select r from Reply r where r.board.bno = :bno")    // JPQL
    Page<Reply> listOfBoard(@Param(value="bno") Long bno, Pageable pageable);

}
