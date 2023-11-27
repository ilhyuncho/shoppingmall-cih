package com.cih.shoppingmallcih.service;


import com.cih.shoppingmallcih.domain.test.guestbook.Guestbook;
import com.cih.shoppingmallcih.dto.test.GuestbookDTO;
import com.cih.shoppingmallcih.service.test.GuestbookService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GuestbookServiceTests {

    @Autowired
    private GuestbookService guestbookService;

    @Test
    public void testRegister(){
        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .title("Samplefg title..")
                .content("Sampldfge Content.....")
                .writer("user11")
                .build();

        System.out.println(guestbookService.register(guestbookDTO));
    }

    @Test
    @Disabled   // 테스트 실행 x
    public void testModify(){
        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .gno(602L)
                .title("Samplefg title..111111")
                .content("Sampldfge Content.....11111")
                .writer("user11")
                .build();

        guestbookService.modify(guestbookDTO);
    }



}
