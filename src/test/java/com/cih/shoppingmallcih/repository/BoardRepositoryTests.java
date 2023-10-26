package com.cih.shoppingmallcih.repository;

import com.cih.shoppingmallcih.domain.test.board.Board;
import com.cih.shoppingmallcih.domain.test.board.BoardRepository;
import com.cih.shoppingmallcih.dto.test.BoardListReplyCountDTO;
import com.sun.xml.bind.v2.schemagen.episode.Package;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testSelect(){
        Long bno = 100L;

        Optional<Board> result = boardRepository.findById(bno);

        Board board = result.orElseThrow();

        log.info(board);
    }

    @Test
    public void testSearch1(){
        // 2 page order by bno desc

        Pageable pageable = PageRequest.of(1,10, Sort.by("bno").descending());

        boardRepository.search1(pageable);
    }

    @Test
    public void testSearchAll(){
        String[] types= {"t", "c","w"};
        
        String keyword= "1";

        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());

        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);

        // total pages
        log.info(result.getTotalPages());

        result.getContent().forEach(board -> log.info(board));
    }

    @Test
    public void testSearchReplyCount(){

        String[] types= {"t", "c","w"};

        String keyword= "99";

        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());

        Page<BoardListReplyCountDTO> result = boardRepository.searchWithReplyCount(types, keyword, pageable);

        log.info(result.getTotalPages());

        log.info(result.getSize());

        log.info(result.getNumber());

        result.getContent().forEach( boardListReplyCountDTO -> log.info(boardListReplyCountDTO));
    }

    @Test
    public void testInsertWithImages(){
        Board board = Board.builder()
                .title("Image test")
                .content("첨부파일 테스트")
                .writer("tester")
                .build();

        for(int i = 0 ; i < 3; i++){
            board.addImage(UUID.randomUUID().toString(), "file" + i + ".jpg");
        }
        boardRepository.save(board);
        // board 테이블에 1번, board_image 테이ㅡㅂ에 3번 insert가 일어남
    }

    @Test
    public void testReadWithImages() {
        // 문제 코드
//        Optional<Board> result = boardRepository.findById(1L);
//
//        Board board = result.orElseThrow();
//
//        log.info(board);
//        log.info("----------");
//        log.info(board.getImageSet());
        // 에러 발생, db연결 끊긴 상태에서 다시 select

        // 개선 버전
        Optional<Board> result1 = boardRepository.findByIdWithImages(1L);

        Board board = result1.orElseThrow();

        log.info(board);
        log.info("----------");
        log.info(board.getImageSet());
        // left join으로 한번에 select

    }

}
