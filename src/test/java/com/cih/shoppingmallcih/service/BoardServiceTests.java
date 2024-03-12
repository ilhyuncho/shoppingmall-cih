package com.cih.shoppingmallcih.service;


import com.cih.shoppingmallcih.config.TestThreadPoolConfig;
import com.cih.shoppingmallcih.dto.test.BoardDTO;
import com.cih.shoppingmallcih.dto.test.BoardListAllDTO;
import com.cih.shoppingmallcih.dto.test.PageRequestDTO;
import com.cih.shoppingmallcih.dto.test.PageResponseDTO;
import com.cih.shoppingmallcih.service.test.BoardService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Log4j2

@ContextConfiguration(classes = TestThreadPoolConfig.class)

// 에러 발생 ( @TestPropertySource 지정 해줘야 함 )
//The bean 'threadPoolTaskExecutor', defined in com.cih.shoppingmallcih.config.TestThreadPoolConfig,
// could not be registered. A bean with that name has already been defined in class path resource
// [com/cih/shoppingmallcih/config/ThreadPoolConfig.class] and overriding is disabled.

// 스프링 부트 프레임워크의 기본값은 스프링 빈 재정의를 허용하지 않는다
// 테스트 환경에 맞게 커스터마이징된 프로퍼티 파일을 로딩 할때
@TestPropertySource(locations="classpath:application-test.properties")

public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcw")
                .keyword("1")
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<BoardDTO> result = boardService.list(pageRequestDTO);
        log.info(result);
    }

    @Test
    public void testRegisterWithImages(){

        BoardDTO boardDTO = BoardDTO.builder()
                .title("IFle.fdfs_sdf")
                .content("gfgdfgdhghgh")
                .writer("cihg1")
                .build();

        boardDTO.setFileNames(
                Arrays.asList(UUID.randomUUID()+"_aaa.jpg",
                        UUID.randomUUID()+"_bbb.jpg",
                        UUID.randomUUID()+"_ccc.jpg"
                ));

        Long register = boardService.register(boardDTO);
        log.info(register);
    }

    @Test
    public void testReadAll(){

        Long bno = 518L;

        BoardDTO boardDTO = boardService.readOne(bno);
        log.info(boardDTO.toString());

        boardDTO.getFileNames().forEach(filename -> log.info(filename.toString()));
    }

    @Test
    public void testModify(){
        BoardDTO boardDTO = BoardDTO.builder()
                        .bno(518L)
                        .title("UPdate........")
                        .content("updatdfsdf")
                        .build();
        // 첨부파일을 하나 추가
        boardDTO.setFileNames(Arrays.asList(UUID.randomUUID()+"_zzz.jpg"));

        boardService.modify(boardDTO);
    }
    @Test
    public void testRemoveAll(){
        Long bno = 518L;
        boardService.remove(bno);
    }

    @Test
    public void testListWithAll(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                        .page(1)
                        .size(10)
                        .build();

        PageResponseDTO<BoardListAllDTO> responseDTO
                = boardService.listWithAll(pageRequestDTO);

        // boardService.listWithAll 호출을 테스트 환경에서 실행 시 다른 환경 빈을 사용하도록 테스트

        List<BoardListAllDTO> dtoList = responseDTO.getDtoList();

        dtoList.forEach(dto -> {
            log.info(dto.getBno()+":"+dto.getTitle());

            if(dto.getBoardImages() != null){
                dto.getBoardImages().forEach(log::info);
            }
        });

    }
}
