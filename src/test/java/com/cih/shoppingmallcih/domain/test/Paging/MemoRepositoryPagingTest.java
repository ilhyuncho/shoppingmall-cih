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
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
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

    @Test
    public void testQueryMethodWithPageable(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<Memo> result = memoRepository.findByMnoBetween(70L, 80L, pageable);

        result.get().forEach( memo -> System.out.println(memo));

        // 둘이 같음
//        for (Memo memo : result.getContent()) {
//            System.out.println(memo);
//        }
    }

    @Test
    @Commit
    @Transactional
    public void deleteBy(){
        // @Commit
        // @Transactional
        memoRepository.deleteMemoByMnoLessThan(20L);
        // 실제 개발 에서는 deleteby는 많이 사용되지 않음
        // 한번에 삭제 되는 것이 아닌 각 엔티티 객체를 하나씩 삭제 하기 때문
        // 개발 시에는 deleteBy를 이용하는 방식 보다는 @Query를 이용해서 개선 함
    }
    @Test
    public void updateMemoText(){
        Memo memo = new Memo(20L, "gdfgdfg");

        memoRepository.updateMemoText(memo);
    }

    @Test
    public void getListWithQuery(){

        Pageable pageable = PageRequest.of(0,10);

        Page<Memo> result = memoRepository.getListWithQuery(30L, pageable);

        System.out.println("================");
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
    public void getListWithQueryObject(){
        Pageable pageable = PageRequest.of(0,10);

        Page<Object[]> result = memoRepository.getListWithQueryObject(30L, pageable);

        List<Object[]> content = result.getContent();
        for (Object[] objects : content) {
            System.out.println(objects[0] + "," + objects[1] + "," + objects[2]);
        }
    }


}
