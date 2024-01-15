package com.cih.shoppingmallcih.service;


import com.cih.shoppingmallcih.dto.test.BoardDTO;
import com.cih.shoppingmallcih.dto.test.PageRequestDTO;
import com.cih.shoppingmallcih.dto.test.PageResponseDTO;
import com.cih.shoppingmallcih.service.test.BoardService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.UUID;

@SpringBootTest
@Log4j2
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

}
