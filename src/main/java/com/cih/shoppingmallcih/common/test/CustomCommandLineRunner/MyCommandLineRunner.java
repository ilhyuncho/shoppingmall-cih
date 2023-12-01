package com.cih.shoppingmallcih.common.test.CustomCommandLineRunner;

import com.cih.shoppingmallcih.domain.test.board.Board;
import com.cih.shoppingmallcih.domain.test.board.BoardRepository;
import com.cih.shoppingmallcih.dto.test.BoardDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Order(2)       // CommandLineRunner구현체가 여러개 있을때 @Order로 실행 순서를 결정 ( 낮을수록 우선순위가 높다 )
@Component
@Log4j2
@RequiredArgsConstructor
public class MyCommandLineRunner implements CommandLineRunner {

    // 스프링 부트 애플리케이션이 빈 등록을 포함한 초기화 과정 수행을 거의 다 마친 뒤에 실행되므로
    // 어떤 빈이든 주입받아 사용할수 있다
    private final BoardRepository boardRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("MyCommandLineRunner : run~~ ");
        Arrays.stream(args).forEach(String -> System.out.println());

        // 테스트
        //testBoardRepository();
    }

    private void testBoardRepository(){
        Optional<Board> result = boardRepository.findById(1L);

        Board board = result.orElseThrow();

        log.info("MyCommandLineRunner:testBoardRepository= " +board.getContent());
    }


}
