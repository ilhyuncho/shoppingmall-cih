package com.cih.shoppingmallcih.controller.test;

import com.cih.shoppingmallcih.dto.test.GuestbookDTO;
import com.cih.shoppingmallcih.service.test.mybatisTest.MybatisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/guestbook")
@Log4j2
@RequiredArgsConstructor
public class GuestbookController {
    private final MybatisService mybatisService;

    @GetMapping({"/", "/list"})
    public String list(){
        log.info("list.......");

        return "/test/guestbook/list";
    }

    @GetMapping( "/register")
    public String register(){
        log.info("register.......");

        return "/test/guestbook/register";
    }
    @PostMapping("/register")
    public String registerGuestbook(@Valid GuestbookDTO dto, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){
        log.info("Guestbook POST register......");

        if(bindingResult.hasErrors()){
            log.info("has errors........");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/guestbook/register";
        }

        log.info("GuestbookDTO" + dto);

        // 임시로
        dto.setModDate(LocalDateTime.now());
        dto.setRegDate(LocalDateTime.now());


        mybatisService.insertGuestBook(dto);


        return "redirect:/guestbook/list";
    }

}
