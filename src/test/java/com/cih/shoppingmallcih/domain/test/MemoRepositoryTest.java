package com.cih.shoppingmallcih.domain.test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MemoRepositoryTest {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testfindById(){

        Memo memo = Memo.builder().memoText("gdfgfg1").build();
        memoRepository.save(memo);

        Optional<Memo> result = memoRepository.findById(2L);    // 1.실행한 순간에 이미 sql은 처리 가 됨
        if(result.isPresent()){
            Memo memo1 = result.get();
            assertThat(memo1.getMemoText()).isEqualTo(memo.getMemoText());
        }
    }

    @Test
    @Transactional
    public void testGetOne(){

        Memo result = memoRepository.getReferenceById(2L);

        System.out.println("=================");
        System.out.println(result);                     // 2.실제 값이 필요할때 쿼리 실행 (  @Transactional 이 필요 )
    }

    @Test
    public void testException(){

//        Memo memo = memoRepository.findById(100000L).orElseThrow(
//                () -> new IllegalArgumentException("user doesn't exist"));

        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
                memoRepository.findById(100000L).orElseThrow(
                () -> new IllegalArgumentException("user doesn't exist"));
            }
        );
    }

    @Test
    public void test2(){
        IntStream.rangeClosed(1,100).forEach( i -> {
            Memo memo = Memo.builder().memoText("memo_test" + i).build();
            memoRepository.save(memo);
        });
    }

}