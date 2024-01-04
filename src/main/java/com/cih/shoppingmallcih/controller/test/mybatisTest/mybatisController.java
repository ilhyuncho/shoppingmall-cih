package com.cih.shoppingmallcih.controller.test.mybatisTest;

import com.cih.shoppingmallcih.dto.test.GuestbookDTO;
import com.cih.shoppingmallcih.service.test.mybatisTest.MybatisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/mybatis")
@RequiredArgsConstructor
@Log4j2
public class mybatisController {

    private final MybatisService mybatisService;

    @GetMapping("/time")
    public String getTime(){
        String now = mybatisService.getNow();
        log.info("now()!!!!!!!!!!!!" + now);
        return now;
    }

    @GetMapping("/guestbook")
    public GuestbookDTO getGuestBook(){
        GuestbookDTO dto = mybatisService.getGuestBook();
        log.info("guestbookDTO!!!!!!!!!!!! : " + dto);
        return dto;
    }
    @PostMapping("/guestbook")
    public String insertGuestBook(GuestbookDTO dto, RedirectAttributes redirectAttributes){
        dto.setModDate(LocalDateTime.now());
        dto.setRegDate(LocalDateTime.now());
        log.error(dto.toString());
        mybatisService.insertGuestBook(dto);
        return "redirect:/guestbook/list";
    }
}
