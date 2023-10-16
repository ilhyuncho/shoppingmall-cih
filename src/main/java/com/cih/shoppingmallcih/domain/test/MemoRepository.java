package com.cih.shoppingmallcih.domain.test;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);

    Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable); // 정렬 조건은 Pageable 을 통해 조정

    void deleteMemoByMnoLessThan(Long num);        // 메모의 번호(mno)가 10보다 작은 데이터를 삭제

    @Transactional
    @Modifying
    @Query("update Memo m set m.memoText = :#{#param.memoText} where m.mno = :#{#param.mno} ")
    int updateMemoText(@Param("param") Memo memo);

    @Query(value="select m from Memo m where m.mno > :mno",
            countQuery = "select count(m) from Memo m where m.mno > :mno" )
    Page<Memo> getListWithQuery(@Param("mno")Long mno, Pageable pageable);

    @Query(value= "select m.mno, m.memoText, CURRENT_DATE from Memo m where m.mno > :mno",
                countQuery = "select count(m) from Memo m where m.mno > :mno" )
    Page<Object[]> getListWithQueryObject(@Param("mno")Long mno, Pageable pageable);

}
