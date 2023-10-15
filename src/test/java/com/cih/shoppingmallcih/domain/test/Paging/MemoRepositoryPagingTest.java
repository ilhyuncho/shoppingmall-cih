package com.cih.shoppingmallcih.domain.test.Paging;

import com.cih.shoppingmallcih.domain.test.Memo;
import com.cih.shoppingmallcih.domain.test.MemoRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class MemoRepositoryPagingTest {
    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testPageDefault(){
        Pageable pageable = PageRequest.of(0,10);   // 1페이지부터 데이터 10개씩
        Page<Memo> result = memoRepository.findAll(pageable);

        System.out.println("-----" + result);

        System.out.println("Total pages: " + result.getTotalPages() );  // 총 몇 페이지
        System.out.println("Total Count: " + result.getTotalElements());    // 전체 개수
        System.out.println("Page Number: " + result.getNumber());   // 현재 페이지 번호
        System.out.println("Page Size : " + result.getSize());  // 페이지당 데이터 갯수
        System.out.println("has next page?: " + result.hasNext()); // 다음 페이지 존재 여부

        List<Memo> content = result.getContent();

        for (Memo memo : content) {
            System.out.println(memo);
        }
    }

    @Test
    public void testSort(){
        Sort sort1 = Sort.by("mno").descending();

        // 정렬 조건을 여러개 지정
//        Sort sort2 = Sort.by("memoTest").ascending();
//        Sort sortAll = sort1.and(sort2);

        Pageable pageable = PageRequest.of(0,10, sort1);

        Page<Memo> result = memoRepository.findAll(pageable);

        result.get().forEach(memo -> {
            System.out.println(memo);
                });
    }

    @Test
    public void testQueryMethods(){

        List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(70L, 80L);
        for (Memo memo : list) {
            System.out.println(memo);
        }

    }




}
