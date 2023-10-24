package com.cih.shoppingmallcih.service;


import com.cih.shoppingmallcih.dto.test.ReplyDTO;
import com.cih.shoppingmallcih.service.test.ReplyService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ReplyServiceTests {

    @Autowired
    ReplyService replyService;

    @Test
    public void register(){
        ReplyDTO replyDTO = ReplyDTO.builder()
                        .bno(108L)
                        .replyer("test3323434")
                        .replyText("11댓슬ㅇㄹㄴㅇㄹgㄴㅇㄹ")
                        .build();

        log.info(replyService.register(replyDTO));


    }
}
