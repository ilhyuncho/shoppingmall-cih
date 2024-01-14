package com.cih.shoppingmallcih.repository.test;

import com.cih.shoppingmallcih.domain.test.board.Board;
import com.cih.shoppingmallcih.domain.test.board.BoardRepository;
import com.cih.shoppingmallcih.domain.test.reply.ReplyRepository;
import com.cih.shoppingmallcih.dto.test.BoardListReplyCountDTO;
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
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void testInsert(){
        IntStream.rangeClosed(1,100).forEach(i ->{
            Board board = Board.builder()
                    .title("title..." + i)
                    .content("content..." + i)
                    .writer("user"+(i % 10))
                    .build();

            Board result = boardRepository.save(board);
            log.info("bno:" + result.getBno());
        });
    }
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
        // 파라미터로 Pageable을 이용하면 리턴 타입은 Page<T> 타입을 이용할수 있다
        Page<Board> result = boardRepository.search1(pageable);
        // total pages
        log.info(result.getTotalPages());

        result.getContent().forEach(board -> log.info(board));
        // 에러 발생
        // imageSet 연관 관계 추가 이후 발생하는 듯
        // test.board.Board.imageSet, could not initialize proxy - no Session
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

        // 에러 발생
        // imageSet 연관 관계 추가 이후 발생하는 듯
        // test.board.Board.imageSet, could not initialize proxy - no Session
    }

    @Test
    public void testSearchReplyCount(){

        String[] types= {"t", "c","w"};

        String keyword= "92";

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
//        Optional<Board> result = boardRepository.findById(413L);
//
//        Board board = result.orElseThrow();
//
//        log.info(board);
//        log.info("----------");
//        log.info(board.getImageSet());
        // 에러 발생, db연결 끊긴 상태에서 다시 select

        // 개선 버전 ( board, board_image 테이블의 조인 처리 가 된 상태로 select가 실행
        Optional<Board> result1 = boardRepository.findByIdWithImages(1L);

        Board board = result1.orElseThrow();

        log.info(board);
        log.info("----------");
        log.info(board.getImageSet());
        // left join으로 한번에 select

    }

    @Transactional
    @Commit
    @Test

    public void testModifyImages(){
        Optional<Board> result = boardRepository.findByIdWithImages(416L);

        Board board = result.orElseThrow();

        // 기존의 첨부파일들은 삭제
        board.clearImages();

        for(int i = 0; i < 2; i++){
            board.addImage(UUID.randomUUID().toString(), "updateFile44"+i+".jpg");
        }

        boardRepository.save(board);
        // 문제: 기존 image는 삭제 되지 않고.. board_bno 가 null 로 업데이트 됨
        // 기존 image는 삭제 처리 하고 싶음
        // @OneToMany에 orphanRemoval = true로 설정
    }

    @Test
    @Transactional
    @Commit
    public void testRemoveAll(){
        Long bno = 416L;

        replyRepository.deleteByBoard_Bno(bno);
        boardRepository.deleteById(bno);
    }

    @Test
    public void testInsertAll(){
        for(int i= 0; i<=100;i++){
            Board board = Board.builder()
                    .title("TItle....."+i)
                    .content("COntent...." + i)
                    .writer("writer...." + i)
                    .build();

            for(int j = 0; j<3;j++){
                if(i % 5 == 0){
                    continue;
                }
                board.addImage(UUID.randomUUID().toString(),i+"file"+j+".jpg");
            }
            boardRepository.save(board);
        }
    }

    @Transactional
    @Test
    public void testSearchImageReplyCount(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        boardRepository.searchWithAll(null, null, pageable);
        // N+1 문제 발생
        // 해결책 : Board 엔티티에 @BatchSize 지정 : 'n'번에 해당하는 쿼리를 모아서 한번에 실행

    }

}
